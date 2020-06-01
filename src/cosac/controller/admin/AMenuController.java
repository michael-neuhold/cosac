package cosac.controller.admin;

import cosac.client.ClientSocket;
import cosac.client.DataContainer;
import cosac.communication.Protocol;
import cosac.model.FoodData;
import cosac.model.SectionData;
import cosac.views.admin.AMenuView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AMenuController implements EventHandler<ActionEvent> {

    private Stage popupStage = null;

    private AMenuView adminMenuView = new AMenuView(this);
    private SceneController sceneController = null;

    public AMenuController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        adminMenuView.getSectionTable().setItems(DataContainer.getInstance().getSectionDataSets());
        adminMenuView.getFoodTable().setItems(DataContainer.getInstance().getFoodDataSets());
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
        else if(source.equals(adminMenuView.getSaveButton())) {
            Thread thread = new Thread(() -> {
                ClientSocket.connect(Protocol.SET_FOOD_DATA_SETS);
                ClientSocket.connect(Protocol.SET_SECTION_DATA_SETS);
            });
            thread.start();
        }
        adminMenuView.resetTextFields();
    }

    private void handleAddFood() {
        FoodData newFood = new FoodData(
            Integer.parseInt(adminMenuView.getAddFoodIdField().getText()),
            Integer.parseInt(adminMenuView.getAddFoodSectionField().getText()),
            adminMenuView.getAddFoodNameField().getText()
        );
        DataContainer.getInstance().addFood(newFood);
    }

    private void handleRemoveFood() {
        DataContainer.getInstance().removeFood(adminMenuView.getRemoveFoodIdField().getText());
    }

    private void handleAddSection() {
        SectionData newSection = new SectionData(
            Integer.parseInt(adminMenuView.getAddSectionIdField().getText()),
            adminMenuView.getAddSectionNameField().getText()
        );
        DataContainer.getInstance().addSection(newSection);
    }

    private void handleRemoveSection() {
        DataContainer.getInstance().removeSection(adminMenuView.getRemoveSectionIdField().getText());
    }

}
