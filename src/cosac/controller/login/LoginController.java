package cosac.controller.login;

import cosac.views.login.LoginView;
import cosac.SceneController;
import cosac.SceneType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LoginController implements EventHandler<ActionEvent> {

    private LoginView loginView = new LoginView(this);
    private SceneController sceneController = null;

    public LoginController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
    }

    public LoginView getView() {
        return loginView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(loginView.getLoginButton())) {
            String username = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();
            System.out.println("username: " + username + ", password: " + password);

            if(username.equals("admin")) sceneController.mountNewScene(SceneType.ADMIN_VIEW);
            else {
                loginView.showError("you can only login with username: admin password: ...");
            }
        }
    }

}
