package cosac.controller.admin;

import cosac.model.DataContainer;
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
        adminMenuView.getSectionTable().setItems(DataContainer.getSectionDataSet());
        adminMenuView.getFoodTable().setItems(DataContainer.getFoodDataSets());
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
        DataContainer.addFood(
            adminMenuView.getAddFoodIdField().getText(),
            adminMenuView.getAddFoodSectionField().getText(),
            adminMenuView.getAddFoodNameField().getText()
        );
    }

    private void handleRemoveFood() {
        DataContainer.removeFood(adminMenuView.getRemoveFoodIdField().getText());
    }

    private void handleAddSection() {
        DataContainer.addSection(
            adminMenuView.getAddSectionIdField().getText(),
            adminMenuView.getAddSectionNameField().getText()
        );
    }

    private void handleRemoveSection() {
        DataContainer.removeSection(adminMenuView.getRemoveSectionIdField().getText());
    }

}
