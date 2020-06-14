package cosac.controller.admin;

import cosac.SceneController;
import cosac.client.DataContainer;
import cosac.model.Role;
import cosac.model.UserData;
import cosac.rmi.Protocol;
import cosac.rmi.RMIClient;
import cosac.views.admin.AUserView;
import cosac.views.admin.popup.AddUserView;
import cosac.views.admin.popup.LockUserView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Crypt;
import util.Logger;

import java.security.NoSuchAlgorithmException;


public class AUserController implements EventHandler {

    private Stage popupStage = null;

    private AUserView adminUserView = new AUserView(this);
    private SceneController sceneController = null;

    private AddUserView popupViewAddUser = null;
    private LockUserView popupLockUser = null;

    public AUserController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        new Thread( () -> {
            Platform.runLater(() ->
                adminUserView.getUserTable().setItems(
                    FXCollections.observableArrayList(RMIClient.getUserDataFromDB())
                ));
        }).start();
    }

    public AUserView getView() {
        return adminUserView;
    }

    private void showPopUp(Pane pane) {
        popupStage = sceneController.showPopUpStage(pane);
    }

    private void closePopup() {
        popupStage.close();
    }

    @Override
    public void handle(Event event) {

        if(event instanceof TableColumn.CellEditEvent)
            handleEditUserEvents(event);
        else if(event instanceof ActionEvent)
            handleButtonEvents(event);

    }

    private void handleButtonEvents(Event event) {
        Object source = event.getSource();

        if (source.equals(adminUserView.getBackButton())) sceneController.mountPreviousScene();
        else if (source.equals(adminUserView.getAddUserButton())) {
            popupViewAddUser = new AddUserView(this);
            showPopUp(popupViewAddUser);
        } else if (source.equals(adminUserView.getLockUserButton())) {
            popupLockUser = new LockUserView(this);
            showPopUp(popupLockUser);
        }

        if (popupViewAddUser != null) handleAddUserPopup(source);

        if (popupLockUser != null) handleLockUserPopup(source);
    }

    private void handleEditUserEvents(Event event) {
        TableColumn.CellEditEvent source = ((TableColumn.CellEditEvent) event);
        int posCol = source.getTablePosition().getColumn();

        // get edited row from table
        UserData dataRow = (
            (UserData)
                source.getTableView().getItems().get(
                source.getTablePosition().getRow())
        );

        // update edited cell
        switch(posCol) {
            case 0: dataRow.setStudentID((String)source.getNewValue()); break;
            case 1: dataRow.setFirstname((String)source.getNewValue()); break;
            case 2: dataRow.setLastname((String) source.getNewValue()); break;
            case 3: dataRow.setEmail((String) source.getNewValue()); break;
            case 4: dataRow.setLocked((Boolean) source.getNewValue()); break;
        }

        new Thread( () -> {
            RMIClient.connect(Protocol.UPDATE_USER_DATA_SET, dataRow);
            Platform.runLater(() -> adminUserView.getUserTable().setItems(
                FXCollections.observableArrayList(RMIClient.getUserDataFromDB())
            ));
        }).start();

    }

    private void handleLockUserPopup(Object source) {
        if (source.equals(popupLockUser.getLockButton())) {

            String id = popupLockUser.getStudentIdToLock().getText();
            UserData user = DataContainer.getInstance().getUserSetById(id);
            if(user != null) {
                user.setLocked(true);
                adminUserView.getUserTable().refresh();
            } else {
                Logger.error("id does not exist");
            }

            closePopup();
        } else if (source.equals(popupLockUser.getCancelButton())) {
            closePopup();
        }
    }

    private void handleAddUserPopup(Object source) {
        if (source.equals(popupViewAddUser.getAddButton())) {
            try {
                Crypt crypt = new Crypt();
                UserData newUser = new UserData(
                    popupViewAddUser.getStudentsIdField().getText(),
                    popupViewAddUser.getFirstnameField().getText(),
                    popupViewAddUser.getLastnameField().getText(),
                    popupViewAddUser.getEmailField().getText(),
                    crypt.getHash(popupViewAddUser.getPasswordField().getText()),
                    Role.STUDENT,
                    false
                );
                if(fieldsAreFilled() && isValidUserInput(newUser.getStudentID(),newUser.getEmail())) {
                    closePopup();

                    new Thread( () -> {
                        RMIClient.connect(Protocol.INSERT_USER_DATA_SET, newUser);
                        Platform.runLater(() -> adminUserView.getUserTable().setItems(FXCollections.observableArrayList(
                            RMIClient.getUserDataFromDB())));
                    }).start();

                } else {
                    Logger.error("wrong userinput");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (source.equals(popupViewAddUser.getCancelButton())) {
            closePopup();
        }
    }

    private boolean fieldsAreFilled() {
        return
            !popupViewAddUser.getStudentsIdField().getText().isEmpty() &&
            !popupViewAddUser.getFirstnameField().getText().isEmpty() &&
            !popupViewAddUser.getLastnameField().getText().isEmpty() &&
            !popupViewAddUser.getEmailField().getText().isEmpty() &&
            !popupViewAddUser.getPasswordField().getText().isEmpty();
    }

    private boolean isValidUserInput(String userId, String email) {
        return  UserData.isValidId(userId) && UserData.isValidEmail(email);
    }

}
