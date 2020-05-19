package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.ARestrictionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ListResourceBundle;


public class ARestrictionView extends BorderPane {

    private Button backButton = new Button("back");
    private Button addRestrictionButton = new Button("new restriction");
    private ListView restrictionList = new ListView();

    public ARestrictionView(ARestrictionController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("restrictions"));
        this.setCenter(createContent());
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
        addRestrictionButton.setOnAction(controller);
    }

    private HBox createControlArea() {
        HBox controlWrapper = new HBox();
        controlWrapper.setId("addRestrictionButtonWrapper");
        addRestrictionButton.getStyleClass().add("button");
        controlWrapper.getChildren().add(addRestrictionButton);
        return controlWrapper;
    }

    private VBox createContent() {
        VBox wrapper = new VBox();
        wrapper.getChildren().add(createControlArea());
        wrapper.getChildren().add(restrictionList);
        return wrapper;
    }

    public Button getBackButton() { return backButton; }
    public Button getAddRestrictionButton() { return addRestrictionButton; }
    public ListView getRestrictionList() { return restrictionList; }

}
