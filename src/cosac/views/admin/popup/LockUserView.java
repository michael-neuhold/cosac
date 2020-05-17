package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.popup.LockUserController;
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

    public LockUserView(LockUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("lock user"));
        this.setCenter(createInput());
        this.setBottom(Component.createPopUpControls(cancelButton, lockButton));
        lockButton.setOnAction(controller);
        cancelButton.setOnAction(controller);
    }

    private HBox createInput() {
        HBox innerWrapper = new HBox(10);
        innerWrapper.setAlignment(Pos.CENTER);
        VBox wrapper = new VBox(10);
        wrapper.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("Student ID");
        inputLabel.getStyleClass().add("inputFieldLabel");
        studentIdToLock.getStyleClass().add("inputField");
        wrapper.getChildren().addAll(inputLabel,studentIdToLock);
        innerWrapper.getChildren().add(wrapper);
        return innerWrapper;
    }

    public Button getLockButton() { return lockButton; }
    public Button getCancelButton() { return cancelButton; }
    public TextField getStudentIdToLock() { return studentIdToLock; }

}
