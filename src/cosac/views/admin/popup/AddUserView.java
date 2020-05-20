package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.AUserController;
//import cosac.controller.admin.popup.AddUserController;
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

    public AddUserView(AUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("add user"));
        this.setCenter(createInputFiles());
        this.setBottom(Component.createPopUpControls(cancelButton,addButton));
        setEventForwarding(controller);
    }

    private VBox createInputFiles() {
        VBox inputFieldWrapper = new VBox();
        inputFieldWrapper.setAlignment(Pos.CENTER);
        inputFieldWrapper.getChildren().addAll(
            Component.createLabeledInput("id", studentsIdField),
            Component.createLabeledInput("firstname", firstnameField),
            Component.createLabeledInput("lastname", lastnameField),
            Component.createLabeledInput("email", emailField)
        );
        return inputFieldWrapper;
    }

    private void setEventForwarding(AUserController controller) {
        cancelButton.setOnAction(controller);
        addButton.setOnAction(controller);
    }

    public Button getAddButton() { return addButton; }
    public Button getCancelButton() { return cancelButton; }

    public TextField getStudentsIdField() { return studentsIdField; }
    public TextField getFirstnameField() { return firstnameField; }
    public TextField getLastnameField() { return lastnameField; }
    public TextField getEmailField() { return emailField; }
}
