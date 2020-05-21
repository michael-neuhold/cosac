package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AOrderController;
import cosac.model.DataContainer;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class AOrderView extends BorderPane {

    private Button backButton = new Button("back");

    private ArrayList<ListView> listViews = new ArrayList<>();

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
        for(int i = 0; i < DataContainer.getRestrictionDataSets().size(); i++) {
            ListView list = new ListView();
            String timeslot = DataContainer.getRestrictionDataSets().get(i).getTimeSlot();
            list.getStyleClass().add("ordertable");
            listViews.add(list);
            Text listHeader = new Text(timeslot);
            listHeader.getStyleClass().add("timeslot");
            tableWrapper.getChildren().addAll(listHeader, list);
        }
        return tableWrapper;
    }

    public Button getBackButton() { return backButton; }
    public ArrayList<ListView> getListViews() { return listViews; }

}
