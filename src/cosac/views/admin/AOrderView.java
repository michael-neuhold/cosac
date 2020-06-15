package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AOrderController;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class AOrderView extends BorderPane {

    private Button backButton = new Button("back");

    private ArrayList<TableView> tabelViews = new ArrayList<>();

    private TableColumn userIdCol = new TableColumn("User");
    private TableColumn foodCol = new TableColumn("Bestellung");
    private TableColumn timeslotCol = new TableColumn("Timeslot ID");

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
      /*  for(int i = 0; i < DataContainer.getInstance().getRestrictionDataSets().size(); i++) {
            TableView table = new TableView();

            userIdCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("userId"));
            foodCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("food"));
            timeslotCol.setCellValueFactory(new PropertyValueFactory<OrderData, String>("timeSlotId"));

            table.getColumns().addAll(userIdCol, foodCol, timeslotCol);

            String timeslot = DataContainer.getInstance().getRestrictionDataSets().get(i).getTimeSlot();
            table.getStyleClass().add("ordertable");
            tabelViews.add(table);
            Text listHeader = new Text(timeslot);
            listHeader.getStyleClass().add("timeslot");
            tableWrapper.getChildren().addAll(listHeader, table);
        }*/
        return tableWrapper;
    }

    public Button getBackButton() { return backButton; }
    public ArrayList<TableView> gettabelViews() { return tabelViews; }

}
