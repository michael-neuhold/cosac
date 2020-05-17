package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AOrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class AOrderView extends BorderPane {

    private Button backButton = new Button("back");

    ObservableList items = FXCollections.observableArrayList (
            "order 1", "order 2", "order 3", "order 4"
    );

    public AOrderView(AOrderController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("orders"));
        this.setCenter(new ListView(items));
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
    }

    public Button getBackButton() { return backButton; }

}
