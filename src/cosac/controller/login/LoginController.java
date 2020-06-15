package cosac.controller.login;

import cosac.SceneController;
import cosac.SceneType;
import cosac.model.UserData;
import cosac.rmi.RMIClient;
import cosac.views.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import util.Crypt;

import java.security.NoSuchAlgorithmException;

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

            try {
                Crypt crypt = new Crypt();

                // username equals id
                UserData user = RMIClient.getUserData().stream()
                    .filter(u -> u.getStudentID().equals(username))
                    .findFirst()
                    .orElse(null);

                if(user != null && user.getPassword().equals(crypt.getHash(loginView.getPasswordField().getText()))) {
                    // forwarding to correct students views or admin views
                    // at the moment only campina admin views available
                    sceneController.mountNewScene(SceneType.ADMIN_VIEW);
                } else {
                   loginView.showError("wrong user id or password");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

}
