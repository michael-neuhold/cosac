package cosac.views.login;

import cosac.component.Component;
import cosac.controller.login.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginView extends BorderPane {

    private final Button loginButton = createLoginButton();
    private final TextField usernameField = createUserNameField();
    private final PasswordField passwordField = createPasswordField();

    public LoginView(LoginController controller) {
        this.getStyleClass().add("window");
        HBox inputForm = new HBox();
        inputForm.setAlignment(Pos.CENTER);
        inputForm.getChildren().add(createInputPanel());
        this.setCenter(inputForm);
        loginButton.setOnAction(controller);
    }

    private TextField createUserNameField() {
        TextField field = new TextField();
        field.getStyleClass().add("inputField");
        return field;
    }

    private PasswordField createPasswordField() {
        PasswordField field = new PasswordField();
        field.getStyleClass().add("inputField");
        return field;
    }

    private Button createLoginButton() {
        Button button = new Button("Login");
        button.setId("loginButton");
        return button;
    }

    private HBox createHeader() {
        HBox headerWrapper = new HBox();
        headerWrapper.setId("loginHeaderWrapper");
        Text loginHeader = new Text("Login");
        loginHeader.setId("loginHeader");
        headerWrapper.getChildren().add(loginHeader);
        return headerWrapper;
    }

    private VBox createInputPanel() {
        VBox inputWrapper = new VBox(10);
        inputWrapper.setAlignment(Pos.CENTER);
        inputWrapper.getChildren().add(new ImageView("./style/images/campina.png"));
        inputWrapper.getChildren().add(createHeader());
        inputWrapper.getChildren().addAll(new Label("user id"), usernameField);
        inputWrapper.getChildren().addAll(new Label("password"), passwordField);
        usernameField.setPromptText("user id 'admin'");
        passwordField.setPromptText("password '[A-z,1-9]*'");
        inputWrapper.getChildren().add(getButtonPanel());
        return inputWrapper;
    }

    private HBox getButtonPanel() {
        HBox buttonWrapper = new HBox();
        buttonWrapper.setId("loginButtonWrapper");
        buttonWrapper.getChildren().add(loginButton);
        return buttonWrapper;
    }

    public void showError(String msg) {
        this.setBottom(Component.createLoginError(msg));
    }

    public Button getLoginButton() { return loginButton; }
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }

}
