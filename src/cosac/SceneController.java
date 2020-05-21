package cosac;

import cosac.controller.admin.*;
import cosac.controller.login.LoginController;
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
        primaryStage.setScene(getScene(type, primaryStage, 800,800));
        primaryStage.setTitle("CosaC");
        primaryStage.show();
    }

    public void mountPreviousScene() {
        Scene tmp = primaryStage.getScene();
        primaryStage.setScene(previousScene);
        primaryStage.show();
        previousScene = tmp;
    }

    private Scene getScene(SceneType type, Stage stage,  int width, int height) {
        Pane pane = null;

        switch (type) {
            case ADMIN_VIEW: pane = new AdminController(stage).getView(); break;
            case ADMIN_ORDER_VIEW: pane = new AOrderController(stage).getView(); break;
            case ADMIN_USER_VIEW: pane = new AUserController(stage).getView(); break;
            case ADMIN_RESTRICTION_VIEW: pane = new ARestrictionController(stage).getView(); break;
            case ADMIN_MENU_VIEW: pane = new AMenuController(stage).getView(); break;
            case LOGIN: pane = new LoginController(stage).getView(); break;
        }

        Scene scene = new Scene(pane, width,height);
        scene.getStylesheets().add(getClass().getResource("../style/style.css").toExternalForm());
        return scene;
    }

    public Stage showPopUpStage(Pane pane) {
        Stage stage = new Stage();
        Scene scene = new Scene(pane, 600,600);
        scene.getStylesheets().add(getClass().getResource("../style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        return stage;
    }

}
