package cosac.controller.admin;

import cosac.SceneType;
import cosac.views.admin.AUserView;
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
        else if(source.equals(adminUserView.getAddUserButton())) {
            System.out.println("add user");
            sceneController.openPopUp(SceneType.ADMIN_ADD_USER_VIEW);
        }
        else if(source.equals(adminUserView.getLockUserButton())) {
            System.out.println("lock user");
            sceneController.openPopUp(SceneType.ADMIN_LOCK_USER_VIEW);
        }
    }

}
