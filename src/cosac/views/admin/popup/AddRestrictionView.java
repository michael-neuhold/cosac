package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.ARestrictionController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AddRestrictionView extends BorderPane {

    private Button addButton = new Button("add");
    private Button cancelButton = new Button("cancel");

    private TextField startTimeField = new TextField();
    private TextField endTimeField = new TextField();
    private TextField visitorLimitField = new TextField();

    public AddRestrictionView(ARestrictionController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("add restriction"));
        this.setCenter(createInputFiles());
        this.setBottom(Component.createPopUpControls(cancelButton,addButton));
        setEventForwarding(controller);
    }

    private void setEventForwarding(ARestrictionController controller) {
        cancelButton.setOnAction(controller);
        addButton.setOnAction(controller);
    }

    private VBox createInputFiles() {
        VBox inputFieldWrapper = new VBox(10);
        inputFieldWrapper.setAlignment(Pos.CENTER);
        inputFieldWrapper.getChildren().addAll(
            Component.createLabeledInput("start time", startTimeField),
            Component.createLabeledInput("end time", endTimeField),
            Component.createLabeledInput("visitor limit", visitorLimitField)
        );
        return inputFieldWrapper;
    }

    public Button getAddButton() { return addButton; }
    public Button getCancelButton() { return cancelButton; }

    public TextField getStartTimeField() { return startTimeField; }
    public TextField getEndTimeField() { return endTimeField; }
    public TextField getVisitorLimitField() { return visitorLimitField; }
}
