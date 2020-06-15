package cosac.controller.admin;

import cosac.views.admin.AdminView;
import cosac.SceneController;
import cosac.SceneType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AdminController implements EventHandler<ActionEvent> {

    private AdminView adminView = new AdminView(this);
    private SceneController sceneController = null;

    public AdminController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
    }

    public AdminView getView() {
        return adminView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        SceneType type = null;

        if(source.equals(adminView.getMenuButton())) type = SceneType.ADMIN_MENU_VIEW;
        else if(source.equals(adminView.getOrderButton())) type = SceneType.ADMIN_ORDER_VIEW;
        else if(source.equals(adminView.getRestrictionButton())) type = SceneType.ADMIN_RESTRICTION_VIEW;
        else if(source.equals(adminView.getUserButton())) type = SceneType.ADMIN_USER_VIEW;

        if(type != null) sceneController.mountNewScene(type);
    }

}
