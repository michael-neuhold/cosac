package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AOrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class AOrderView extends BorderPane {

    private Button backButton = new Button("back");

    ObservableList items = FXCollections.observableArrayList (
            "order 1", "order 2", "order 3", "order 4"
    );

    public AOrderView(AOrderController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("orders"));
        this.setCenter(createOrderTables());
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
    }

    private VBox createOrderTables() {
        VBox tableWrapper = new VBox(10);
        tableWrapper.setId("orderTableWrapper");
        for(int i = 0; i < 4; i++) {
            Text subheader = new Text("Zeitslot " + (i + 1));
            subheader.getStyleClass().add("timeslot");
            ListView list = new ListView(items);
            list.getStyleClass().add("ordertable");
            tableWrapper.getChildren().addAll(subheader, list);
        }
        return tableWrapper;
    }

    public Button getBackButton() { return backButton; }

}
