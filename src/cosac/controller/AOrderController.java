package cosac.controller;

import cosac.views.AOrderView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AOrderController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private AOrderView adminOrderView = new AOrderView(this);
    private SceneController sceneController = null;

    public AOrderController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public AOrderView getView() {
        return adminOrderView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminOrderView.getBackButton())) sceneController.mountPreviousScene();
    }

}
