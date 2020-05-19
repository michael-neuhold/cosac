package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.ARestrictionController;
import cosac.model.RestrictionData;
import cosac.model.UserData;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;


public class ARestrictionView extends BorderPane {

    private Button backButton = new Button("back");
    private Button addRestrictionButton = new Button("new restriction");

    private TableColumn startTimeCol = new TableColumn("start");
    private TableColumn endTimeCol = new TableColumn("end");
    private TableColumn visitorLimitCol = new TableColumn("visitor limit");

    private TableView<RestrictionData> restrictionTable = new TableView();

    public ARestrictionView(ARestrictionController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("restrictions"));
        this.setCenter(createContent());
        this.setBottom(Component.createBackButton(backButton));
        setEventForwarding(controller);
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
        wrapper.getChildren().add(createTableView());
        return wrapper;
    }

    private TableView createTableView() {
        restrictionTable.setId("restrictionTable");
        restrictionTable.setEditable(true);

        startTimeCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("endTime"));
        visitorLimitCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("visitorLimit"));

        setColumnsEditable();
        restrictionTable.getColumns().addAll(startTimeCol, endTimeCol, visitorLimitCol);

        return restrictionTable;
    }

    private void setColumnsEditable() {
        startTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        visitorLimitCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void setEventForwarding(ARestrictionController controller) {
        startTimeCol.setOnEditCommit(controller);
        endTimeCol.setOnEditCommit(controller);
        visitorLimitCol.setOnEditCommit(controller);
        backButton.setOnAction(controller);
        addRestrictionButton.setOnAction(controller);
    }

    public Button getBackButton() { return backButton; }
    public Button getAddRestrictionButton() { return addRestrictionButton; }
    public TableView getRestrictionTable() { return restrictionTable; }

}
