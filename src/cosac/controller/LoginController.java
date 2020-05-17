package cosac.controller;

import cosac.views.LoginView;
import cosac.SceneController;
import cosac.SceneType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LoginController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private LoginView loginView = new LoginView(this);
    private SceneController sceneController = null;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
            System.out.println("username: " + username);
            System.out.println("password: " + password);

            if(username.equals("admin")) sceneController.mountNewScene(SceneType.ADMIN_VIEW);
        }
    }

}
