package cosac.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Login extends HBox {

    public Login(String id) {
        this.setId(id);
        GridPane loginPane = new GridPane();
        loginPane.setId("loginPane");

        Text loginHeader = new Text("Login");
        loginPane.add(loginHeader,0,0);
        loginPane.add(createInputPane(),0,1);

        this.getChildren().add(loginPane);
    }

    private GridPane createInputPane() {
        GridPane inputPane = new GridPane();

        GridPane username = createInputWithLabel("username");
        GridPane password = createInputWithLabel("password");
        Button loginButton = new Button("login");
        loginButton.setId("loginBtn");

        inputPane.add(username,0,0);
        inputPane.add(password,0,1);
        inputPane.add(loginButton,0,2);

        return inputPane;
    }

    private GridPane createInputWithLabel(String labeText) {
        GridPane input = new GridPane();
        Label inputLabel = new Label(labeText);
        TextField inputText = new TextField();
        input.add(inputLabel,0,0);
        input.add(inputText,0,1);
        return input;
    }

}
