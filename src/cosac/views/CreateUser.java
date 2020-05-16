package cosac.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreateUser extends BorderPane {

    public CreateUser() {
        this.setTop(createHeader());
        this.setCenter(createInputFiles());
        this.setBottom(createControlButtons());
        this.getBottom().setId("createUserBottom");
    }

    private HBox createHeader() {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        headerWrapper.getChildren().add(new Text("create user"));
        return headerWrapper;
    }

    private VBox createInputFiles() {
        VBox inputFieldWrapper = new VBox();
        inputFieldWrapper.setAlignment(Pos.CENTER);
        inputFieldWrapper.getChildren().addAll(
                createInputPair("firstname"),
                createInputPair("lastname"),
                createInputPair("email"),
                createInputPair("age")
        );
        return inputFieldWrapper;
    }

    private HBox createInputPair(String labelName) {
        HBox outerWrapper = new HBox();
        outerWrapper.setAlignment(Pos.CENTER);
        VBox wrapper = new VBox();
        Label label = new Label(labelName);
        TextField input = new TextField();
        label.getStyleClass().add("inputFieldLabel");
        input.getStyleClass().add("inputField");
        wrapper.getChildren().addAll(label,input);
        outerWrapper.getChildren().add(wrapper);
        return outerWrapper;
    }

    public HBox createControlButtons() {
        HBox buttons = new HBox(20);
        buttons.getStyleClass().add("adminButtonWrapper");
        Button btn1 = new Button("cancle");
        Button btn2 = new Button("add");

        btn1.getStyleClass().add("adminMenuButton");
        btn2.getStyleClass().add("adminMenuButton");

        buttons.getChildren().addAll(btn1,btn2);
        return buttons;
    }


}
