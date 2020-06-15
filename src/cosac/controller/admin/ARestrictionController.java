package cosac.controller.admin;

import cosac.SceneController;
import cosac.model.RestrictionData;
import cosac.rmi.RMIClient;
import cosac.views.admin.ARestrictionView;
import cosac.views.admin.popup.AddRestrictionView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import util.Logger;


public class ARestrictionController implements EventHandler {

    private Stage popupStage;

    private ARestrictionView adminRestictionView = new ARestrictionView(this);
    private AddRestrictionView popupView = new AddRestrictionView(this);

    private SceneController sceneController;

    public ARestrictionController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        new Thread( () -> updateRestrictionTable() ).start();
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
        if(event instanceof TableColumn.CellEditEvent){
            handleEditRestrictionEvents(event);
        } else if(event instanceof ActionEvent) {
            handleButtonEvent(event);
        }
    }

    private void handleButtonEvent(Event event) {
        Object source = event.getSource();
        if (source.equals(adminRestictionView.getBackButton()))
            sceneController.mountPreviousScene();
        else if (source.equals(adminRestictionView.getAddRestrictionButton())) {
            popupView = new AddRestrictionView(this);
            showPopUp();
        }

        if (popupView != null) handleAddRestrictionPopup(source);
    }

    private void handleEditRestrictionEvents(Object event) {
        TableColumn.CellEditEvent source = ((TableColumn.CellEditEvent) event);
        int posCol = source.getTablePosition().getColumn();

        // get data set from table
        RestrictionData dataRow = ((RestrictionData) source.getTableView().getItems().get(
                source.getTablePosition().getRow()));

        // update edited column
        switch(posCol) {
            // col 0 is locked (id should not be edited ;)
            case 1: dataRow.setStartTime((String)source.getNewValue()); break;
            case 2: dataRow.setEndTime((String)source.getNewValue()); break;
            case 3: dataRow.setVisitorLimit((Integer)source.getNewValue()); break;
        }

        new Thread( () -> {
            RMIClient.updateRestrictionAtDB(dataRow);
            updateRestrictionTable();
        }).start();

    }

    private void handleAddRestrictionPopup(Object source) {
        if(source.equals(popupView.getAddButton())) {

            RestrictionData newRestriction = new RestrictionData(
                0,
                popupView.getStartTimeField().getText(),
                popupView.getEndTimeField().getText(),
                Integer.parseInt(popupView.getVisitorLimitField().getText())
            );

            if(isValidUserInput(newRestriction.getStartTime(), newRestriction.getEndTime()) && fieldsAreFilled()) {
                new Thread( () -> {
                    RMIClient.insertRestrictionAtDB(newRestriction);
                    updateRestrictionTable();
                }).start();
                closePopup();
            } else {
                Logger.error("wrong userinput");
            }
        }

        if(source.equals(popupView.getCancelButton()))
            closePopup();
    }

    private boolean fieldsAreFilled() {
        return
            !popupView.getStartTimeField().getText().isEmpty() &&
            !popupView.getEndTimeField().getText().isEmpty() &&
            !popupView.getVisitorLimitField().getText().isEmpty();
    }

    private boolean isValidUserInput(String startTime, String endTime) {
        return  RestrictionData.isValidTime(startTime) &&
                RestrictionData.isValidTime(endTime);
    }

    private void updateRestrictionTable() {
        Platform.runLater( () -> {
            adminRestictionView.getRestrictionTable().setItems(
                    FXCollections.observableArrayList(RMIClient.getRestrictionDataFromDB())
            );
            adminRestictionView.getRestrictionTable().refresh();
        });
    }

}
