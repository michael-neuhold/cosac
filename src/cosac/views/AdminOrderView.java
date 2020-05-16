package cosac.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class AdminOrderView extends BorderPane {

    ObservableList items = FXCollections.observableArrayList (
            "order 1", "order 2", "order 3", "order 4"
    );

    public AdminOrderView() {
        this.setTop(createHeader());
        this.setCenter(new ListView(items));
    }

    private HBox createHeader() {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        headerWrapper.getChildren().add(new Text("orders"));
        return headerWrapper;
    }

    private HBox createOrderList() {
       return null;
    }

}
