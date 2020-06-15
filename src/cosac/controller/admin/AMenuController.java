package cosac.controller.admin;

import cosac.SceneController;
import cosac.model.FoodData;
import cosac.model.SectionData;
import cosac.rmi.RMIClient;
import cosac.views.admin.AMenuView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AMenuController implements EventHandler<ActionEvent> {

    private static final int PLACEHOLDER_ID = 0;
    private Stage popupStage = null;

    private AMenuView adminMenuView = new AMenuView(this);
    private SceneController sceneController = null;

    public AMenuController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);

        new Thread( () -> {
            Platform.runLater( () -> {
                adminMenuView.getFoodTable().setItems(FXCollections.observableArrayList(RMIClient.getFoodDataFromDB()));
                adminMenuView.getSectionTable().setItems(FXCollections.observableArrayList(RMIClient.getSectionDataFromDB()));
            });
        }).start();
    }

    public AMenuView getView() {
        return adminMenuView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminMenuView.getBackButton())) sceneController.mountPreviousScene();
        else if(source.equals(adminMenuView.getAddFoodButton())) handleAddFood();
        else if(source.equals(adminMenuView.getRemoveFoodButton())) handleRemoveFood();
        else if(source.equals(adminMenuView.getAddSectionButton())) handleAddSection();
        else if(source.equals(adminMenuView.getRemoveSectionButton())) handleRemoveSection();
        adminMenuView.resetTextFields();
    }

    private void handleAddFood() {
        FoodData newFood = new FoodData(
            PLACEHOLDER_ID,
            Integer.parseInt(adminMenuView.getAddFoodSectionField().getText()),
            adminMenuView.getAddFoodNameField().getText()
        );

        new Thread( () -> {
            RMIClient.insertFoodAtDB(newFood);
            updateFoodTable();
        }).start();
    }

    private void handleRemoveFood() {
        new Thread( () -> {
            RMIClient.deleteFoodAtDB(Integer.parseInt(adminMenuView.getRemoveFoodIdField().getText()));
            updateFoodTable();
        }).start();
    }

    private void handleAddSection() {
        SectionData newSection = new SectionData(
            PLACEHOLDER_ID,
            adminMenuView.getAddSectionNameField().getText()
        );
        new Thread( () -> {
            RMIClient.insertSectionAtDB(newSection);
            updateSectionTable();
        }).start();
    }

    private void handleRemoveSection() {
        new Thread( () -> {
            RMIClient.deleteSectionAtDB(Integer.parseInt(adminMenuView.getRemoveSectionIdField().getText()));
            updateSectionTable();
        }).start();
    }

    private void updateFoodTable() {
        Platform.runLater( () -> {
            adminMenuView.getFoodTable().setItems(FXCollections.observableArrayList(RMIClient.getFoodDataFromDB()));
            adminMenuView.getFoodTable().refresh();
        });
    }

    private void updateSectionTable() {
        Platform.runLater( () -> {
            adminMenuView.getSectionTable().setItems(FXCollections.observableArrayList(RMIClient.getSectionDataFromDB()));
            adminMenuView.getFoodTable().refresh();
        });
    }

}
