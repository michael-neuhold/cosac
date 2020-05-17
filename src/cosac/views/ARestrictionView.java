package cosac.views;

import cosac.component.Component;
import cosac.controller.ARestrictionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class ARestrictionView extends BorderPane {

    private Button backButton = new Button("back");

    ObservableList items = FXCollections.observableArrayList (
            "order 1", "order 2", "order 3", "order 4"
    );

    public ARestrictionView(ARestrictionController controller) {
        this.setTop(Component.createHeader("restrictions"));
        this.setCenter(new ListView(items));
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
    }

    public Button getBackButton() { return backButton; }

}
