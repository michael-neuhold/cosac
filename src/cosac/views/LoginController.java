package cosac.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private LoginView loginView = new LoginView(this);

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

            if(username.equals("admin")) {
                Scene scene = new Scene(new AdminController(primaryStage).getView(), 800,800);
                scene.getStylesheets().add(getClass().getResource("../style/test.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        }
    }

}
