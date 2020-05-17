package cosac.controller.admin;

import cosac.views.admin.AMenuView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AMenuController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private AMenuView adminMenuView = new AMenuView(this);
    private SceneController sceneController = null;

    public AMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public AMenuView getView() {
        return adminMenuView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminMenuView.getBackButton())) sceneController.mountPreviousScene();
    }

}
