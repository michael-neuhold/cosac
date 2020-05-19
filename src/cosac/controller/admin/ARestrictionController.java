package cosac.controller.admin;

import cosac.SceneType;
import cosac.model.RestrictionData;
import cosac.views.admin.ARestrictionView;
import cosac.SceneController;
import cosac.views.admin.popup.AddRestrictionView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ARestrictionController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private ARestrictionView adminRestictionView = new ARestrictionView(this);
    private SceneController sceneController = null;

    private Stage popupStage = null;
    private AddRestrictionView popupView = new AddRestrictionView(this);

    // data
    ObservableList<RestrictionData> items = FXCollections.observableArrayList (
    new RestrictionData("08:00", "08:30", 20),
            new RestrictionData("08:30", "09:00", 30),
            new RestrictionData("09:00", "09:30", 10)
    );

    public ARestrictionController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);

        adminRestictionView.getRestrictionList().setItems(items);
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
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminRestictionView.getBackButton()))
            sceneController.mountPreviousScene();
        if(source.equals(adminRestictionView.getAddRestrictionButton()))
            showPopUp();

        if(source.equals(popupView.getAddButton())) {
            closePopup();
            String startTime = popupView.getStartTimeField().getText();
            String endTime = popupView.getEndTimeField().getText();
            String visitorLimit = popupView.getVisitorLimitField().getText();

            items.add(
                new RestrictionData(startTime, endTime, Integer.parseInt(visitorLimit))
            );
        }

        if(source.equals(popupView.getCancelButton()))
            closePopup();

    }

}
