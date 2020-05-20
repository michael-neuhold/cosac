package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.AUserController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LockUserView extends BorderPane {

    private Button lockButton = new Button("lock");
    private Button cancelButton = new Button("cancel");

    private TextField studentIdToLock = new TextField();

    public LockUserView(AUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("lock user"));
        this.setCenter(createInput());
        this.setBottom(Component.createPopUpControls(cancelButton, lockButton));
        setEventForwarding(controller);
    }

    private void setEventForwarding(AUserController controller) {
        lockButton.setOnAction(controller);
        cancelButton.setOnAction(controller);
    }

    private HBox createInput() {
        HBox wrapper = Component.createLabeledInput("Student ID", studentIdToLock);
        studentIdToLock.getStyleClass().add("inputField");
        return wrapper;
    }

    public Button getLockButton() { return lockButton; }
    public Button getCancelButton() { return cancelButton; }
    public TextField getStudentIdToLock() { return studentIdToLock; }

}
