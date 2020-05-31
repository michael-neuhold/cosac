package cosac.controller.admin;

import cosac.client.DataContainer;
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
            System.out.println("save menu");
        }
        adminMenuView.resetTextFields();
    }

    private void handleAddFood() {
        DataContainer.getInstance().addFood(
            adminMenuView.getAddFoodIdField().getText(),
            adminMenuView.getAddFoodSectionField().getText(),
            adminMenuView.getAddFoodNameField().getText()
        );
    }

    private void handleRemoveFood() {
        DataContainer.getInstance().removeFood(adminMenuView.getRemoveFoodIdField().getText());
    }

    private void handleAddSection() {
        DataContainer.getInstance().addSection(
            adminMenuView.getAddSectionIdField().getText(),
            adminMenuView.getAddSectionNameField().getText()
        );
    }

    private void handleRemoveSection() {
        DataContainer.getInstance().removeSection(adminMenuView.getRemoveSectionIdField().getText());
    }

}
