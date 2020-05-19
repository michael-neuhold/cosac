package cosac.controller.admin;

import cosac.SceneType;
import cosac.model.DataContainer;
import cosac.model.RestrictionData;
import cosac.views.admin.ARestrictionView;
import cosac.SceneController;
import cosac.views.admin.popup.AddRestrictionView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;


public class ARestrictionController implements EventHandler {

    private Stage popupStage;

    private ARestrictionView adminRestictionView = new ARestrictionView(this);
    private AddRestrictionView popupView = new AddRestrictionView(this);

    private SceneController sceneController;

    public ARestrictionController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        adminRestictionView.getRestrictionTable().setItems(DataContainer.restrictionDataSets);
    }

    public ARestrictionView getView() {
        return adminRestictionView;
    }

    private void showPopUp() {
        popupStage = sceneController.showPopUpStage(popupView);
    }

    private void closePopup() {
        popupStage.close();
    }

    @Override
    public void handle(Event event) {

        if(event instanceof TableColumn.CellEditEvent) handleEditRestriction(event);
        else {
            Object source = event.getSource();
            if (source.equals(adminRestictionView.getBackButton()))
                sceneController.mountPreviousScene();
            if (source.equals(adminRestictionView.getAddRestrictionButton())) {
                popupView = new AddRestrictionView(this);
                showPopUp();
            }

            if (popupView != null) handleAddRestrictionPopup(source);
        }
    }

    private void handleAddRestrictionPopup(Object source) {
        if(source.equals(popupView.getAddButton())) {
            closePopup();
            String startTime = popupView.getStartTimeField().getText();
            String endTime = popupView.getEndTimeField().getText();
            String visitorLimit = popupView.getVisitorLimitField().getText();

            DataContainer.restrictionDataSets.add(
                new RestrictionData(startTime, endTime, Integer.parseInt(visitorLimit))
            );
        }

        if(source.equals(popupView.getCancelButton()))
            closePopup();
    }

    private void handleEditRestriction(Object event) {
        TableColumn.CellEditEvent source = ((TableColumn.CellEditEvent) event);
        int posCol = source.getTablePosition().getColumn();

        // get data set from table
        RestrictionData dataRow = ((RestrictionData) source.getTableView().getItems().get(
                source.getTablePosition().getRow()));

        // update edited column
        switch(posCol) {
            case 0: dataRow.setStartTime((String)source.getNewValue()); break;
            case 1: dataRow.setEndTime((String)source.getNewValue()); break;
            case 2: dataRow.setVisitorLimit((Integer)source.getNewValue()); break;
        }
    }

}
