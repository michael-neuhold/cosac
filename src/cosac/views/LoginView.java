package cosac.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginView extends BorderPane {

    private final Button loginButton = createLoginButton();
    private final TextField usernameField = createUserNameField();
    private final PasswordField passwordField = createPasswordField();

    public LoginView(LoginController controller) {
        this.setId("loginPage");
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
        inputWrapper.getChildren().add(createHeader());
        inputWrapper.getChildren().addAll(new Label("username"), usernameField);
        inputWrapper.getChildren().addAll(new Label("password"), passwordField);
        inputWrapper.getChildren().add(getButtonPanel());
        return inputWrapper;
    }

    private HBox getButtonPanel() {
        HBox buttonWrapper = new HBox();
        buttonWrapper.setId("loginButtonWrapper");
        buttonWrapper.getChildren().add(loginButton);
        return buttonWrapper;
    }

    public Button getLoginButton() { return loginButton; }
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }

}
