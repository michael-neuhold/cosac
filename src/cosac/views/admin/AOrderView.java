package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AOrderController;
import cosac.model.OrderData;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class AOrderView extends BorderPane {

    private Button backButton = new Button("back");

    private TableView<OrderData> table = new TableView();

    private TableColumn orderIdCol = new TableColumn("order id");
    private TableColumn timeslotCol = new TableColumn("time slot");
    private TableColumn userIdCol = new TableColumn("user id");
    private TableColumn userFirstnameCol = new TableColumn("firstname");
    private TableColumn userLastnameCol = new TableColumn("lastname");
    private TableColumn foodCol = new TableColumn("food");

    public AOrderView(AOrderController controller, int timeSlots) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("orders"));
        this.setCenter(createOrderTables(timeSlots));
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
    }

    private VBox createOrderTables(int timeSlots) {
        VBox tableWrapper = new VBox(10);
        tableWrapper.setId("orderTableWrapper");

        orderIdCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("orderId"));
        timeslotCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("timeslot"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("userId"));
        userFirstnameCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("userFirstname"));
        userLastnameCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("userLastname"));
        foodCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("food"));

        timeslotCol.setSortType(TableColumn.SortType.ASCENDING);
        table.getColumns().addAll(orderIdCol, timeslotCol, userIdCol, userFirstnameCol, userLastnameCol, foodCol);


        table.getStyleClass().add("ordertable");

        tableWrapper.getChildren().addAll(table);

        return tableWrapper;
    }

    public void sortTable() {
        table.getSortOrder().add(timeslotCol);
    }

    public Button getBackButton() { return backButton; }
    public TableView getOrderTable() { return table; }

}
