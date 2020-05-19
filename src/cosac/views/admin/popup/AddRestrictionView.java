package cosac.views.admin.popup;

import cosac.component.Component;
import cosac.controller.admin.ARestrictionController;
//import cosac.controller.admin.popup.AddRestrictionController;
//import cosac.controller.admin.popup.AddUserController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        cancelButton.setOnAction(controller);
        addButton.setOnAction(controller);
    }

    private VBox createInputFiles() {
        VBox inputFieldWrapper = new VBox();
        inputFieldWrapper.setAlignment(Pos.CENTER);
        inputFieldWrapper.getChildren().addAll(
                createInputPair("start time", startTimeField),
                createInputPair("end time", endTimeField),
                createInputPair("visitor limit", visitorLimitField)
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

    public TextField getStartTimeField() { return startTimeField; }
    public TextField getEndTimeField() { return endTimeField; }
    public TextField getVisitorLimitField() { return visitorLimitField; }
}
