package cosac;

import cosac.controller.admin.*;
//import cosac.controller.admin.popup.AddRestrictionController;
//import cosac.controller.admin.popup.AddRestrictionController;
//import cosac.controller.admin.popup.AddUserController;
//import cosac.controller.admin.popup.LockUserController;
import cosac.controller.login.LoginController;
import cosac.views.admin.popup.LockUserView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
        primaryStage.show();
    }

    public void mountPreviousScene() {
        Scene tmp = primaryStage.getScene();
        primaryStage.setScene(previousScene);
        primaryStage.show();
        previousScene = tmp;
    }

    public void openPopUp(SceneType type) {
        Stage popupStage = new Stage();
        popupStage.setScene(getScene(type, popupStage, 600, 600));
        popupStage.show();
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
            //case ADMIN_ADD_USER_VIEW: pane = new AddUserController(stage).getView(); break;
            //case ADMIN_LOCK_USER_VIEW: pane = new LockUserController(stage).getView(); break;
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
