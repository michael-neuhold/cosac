package cosac;

import cosac.controller.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {

    static Scene previousScene = null;
    private Stage primaryStage = null;

    public SceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void mountNewScene(SceneType type) {
        previousScene = primaryStage.getScene();
        primaryStage.setScene(getScene(type));
        primaryStage.show();
    }

    public void mountPreviousScene() {
        Scene tmp = primaryStage.getScene();
        primaryStage.setScene(previousScene);
        primaryStage.show();
        previousScene = tmp;
    }

    private Scene getScene(SceneType type) {
        Pane pane = null;

        switch (type) {
            case ADMIN_VIEW: pane = new AdminController(primaryStage).getView(); break;
            case ADMIN_ORDER_VIEW: pane = new AOrderController(primaryStage).getView(); break;
            case ADMIN_USER_VIEW: pane = new AUserController(primaryStage).getView(); break;
            case ADMIN_RESTRICTION_VIEW: pane = new ARestrictionController(primaryStage).getView(); break;
            case ADMIN_MENU_VIEW: pane = new AMenuController(primaryStage).getView(); break;
            case LOGIN: pane = new LoginController(primaryStage).getView(); break;
        }

        Scene scene = new Scene(pane, 800,800);
        scene.getStylesheets().add(getClass().getResource("../style/style.css").toExternalForm());
        return scene;
    }

}
