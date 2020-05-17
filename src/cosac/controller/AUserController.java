package cosac.controller;

import cosac.views.AUserView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AUserController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private AUserView adminUserView = new AUserView(this);
    private SceneController sceneController = null;

    public AUserController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public AUserView getView() {
        return adminUserView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminUserView.getBackButton())) sceneController.mountPreviousScene();
    }

}
