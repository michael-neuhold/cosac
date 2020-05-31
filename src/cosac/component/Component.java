package cosac.component;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Component {

    public static HBox createBackButton(Button backButton) {
        HBox buttonWrapper = new HBox();
        buttonWrapper.getStyleClass().add("backButtonWrapper");
        backButton.getStyleClass().add("backButton");
        buttonWrapper.getChildren().add(backButton);
        buttonWrapper.setAlignment(Pos.CENTER_RIGHT);
        return buttonWrapper;
    }

    public static HBox createBackSaveButton(Button backButton, Button saveButton) {
        HBox buttonWrapper = new HBox(10);
        buttonWrapper.getStyleClass().add("backButtonWrapper");
        backButton.getStyleClass().add("backButton");
        saveButton.getStyleClass().add("backButton");
        buttonWrapper.getChildren().addAll(saveButton, backButton);
        buttonWrapper.setAlignment(Pos.CENTER_RIGHT);
        return buttonWrapper;
    }

    public static HBox createUpdateButton(Button updateButton) {
        HBox buttonWrapper = new HBox();
        buttonWrapper.getStyleClass().add("backButtonWrapper");
        updateButton.getStyleClass().add("backButton");
        buttonWrapper.getChildren().add(updateButton);
        buttonWrapper.setAlignment(Pos.CENTER_RIGHT);
        return buttonWrapper;
    }

    public static HBox createHeader(String title) {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        Text headerText = new Text(title);
        headerText.setFill(Color.WHITE);
        headerWrapper.getChildren().add(headerText);
        return headerWrapper;
    }

    public static HBox createPopUpControls(Button cancle, Button submit) {
        HBox buttons = new HBox(20);
        buttons.getStyleClass().add("popupControls");
        cancle.getStyleClass().add("popupButton");
        submit.getStyleClass().add("popupButton");
        buttons.getChildren().addAll(cancle, submit);
        return buttons;
    }

    public static HBox createLabeledInput(String labelName, TextField field) {
        HBox outerWrapper = new HBox();
        outerWrapper.setAlignment(Pos.CENTER);
        VBox wrapper = new VBox();
        wrapper.setAlignment(Pos.CENTER);
        Label label = new Label(labelName);
        label.getStyleClass().add("inputFieldLabel");
        field.getStyleClass().add("inputField");
        field.setPromptText(labelName);
        wrapper.getChildren().addAll(label,field);
        outerWrapper.getChildren().add(wrapper);
        return outerWrapper;
    }

    public static Label createSubHeader(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("subHeader");
        return label;
    }

}
