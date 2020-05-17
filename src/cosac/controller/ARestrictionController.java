package cosac.controller;

import cosac.views.ARestrictionView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ARestrictionController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private ARestrictionView adminRestictionView = new ARestrictionView(this);
    private SceneController sceneController = null;

    public ARestrictionController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public ARestrictionView getView() {
        return adminRestictionView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminRestictionView.getBackButton())) sceneController.mountPreviousScene();
    }

}
