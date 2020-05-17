package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.popup.AddUserController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddUserView extends BorderPane {

    private Button addButton = new Button("add");
    private Button cancelButton = new Button("cancel");

    private TextField studentsIdField = new TextField();
    private TextField firstnameField = new TextField();
    private TextField lastnameField = new TextField();
    private TextField emailField = new TextField();

    public AddUserView(AddUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("add user"));
        this.setCenter(createInputFiles());
        this.setBottom(Component.createPopUpControls(cancelButton,addButton));
        cancelButton.setOnAction(controller);
        addButton.setOnAction(controller);
    }

    private VBox createInputFiles() {
        VBox inputFieldWrapper = new VBox();
        inputFieldWrapper.setAlignment(Pos.CENTER);
        inputFieldWrapper.getChildren().addAll(
                createInputPair("id", studentsIdField),
                createInputPair("firstname", firstnameField),
                createInputPair("lastname", lastnameField),
                createInputPair("email", emailField)
        );
        return inputFieldWrapper;
    }

    private HBox createInputPair(String labelName, TextField field) {
        HBox outerWrapper = new HBox();
        outerWrapper.setAlignment(Pos.CENTER);
        VBox wrapper = new VBox();
        Label label = new Label(labelName);
        label.getStyleClass().add("inputFieldLabel");
        field.getStyleClass().add("inputField");
        wrapper.getChildren().addAll(label,field);
        outerWrapper.getChildren().add(wrapper);
        return outerWrapper;
    }

    public Button getAddButton() { return addButton; }
    public Button getCancelButton() { return cancelButton; }

    public TextField getStudentsIdField() { return studentsIdField; }
    public TextField getFirstnameField() { return firstnameField; }
    public TextField getLastnameField() { return lastnameField; }
    public TextField getEmailField() { return emailField; }
}
