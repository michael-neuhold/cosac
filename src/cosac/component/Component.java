package cosac.component;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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

    public static HBox createHeader(String title) {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        headerWrapper.getChildren().add(new Text(title));
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

}
