package cosac.controller.admin;

import cosac.client.ClientSocket;
import cosac.client.DataContainer;
import cosac.communication.Protocol;
import cosac.model.Role;
import cosac.model.UserData;
import cosac.views.admin.AUserView;
import cosac.SceneController;
import cosac.views.admin.popup.AddUserView;
import cosac.views.admin.popup.LockUserView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AUserController implements EventHandler {

    private Stage popupStage = null;

    private AUserView adminUserView = new AUserView(this);
    private SceneController sceneController = null;

    private AddUserView popupViewAddUser = null;
    private LockUserView popupLockUser = null;

    public AUserController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        this.adminUserView.getUserTable().setItems(DataContainer.getInstance().getUserDataSets());
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
        } else if (source.equals(adminUserView.getSaveButton())) {
            Thread thread = new Thread( () -> ClientSocket.connect(Protocol.SET_USER_DATA_SETS));
            thread.start();
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
    }

    private void handleLockUserPopup(Object source) {
        if (source.equals(popupLockUser.getLockButton())) {

            String id = popupLockUser.getStudentIdToLock().getText();
            UserData user = DataContainer.getInstance().getUserSetById(id);
            if(user != null) {
                user.setLocked(true);
                adminUserView.getUserTable().refresh();
            }

            closePopup();
        } else if (source.equals(popupLockUser.getCancelButton())) {
            closePopup();
        }
    }

    private void handleAddUserPopup(Object source) {
        if (source.equals(popupViewAddUser.getAddButton())) {
            DataContainer.getInstance().addUser(
                popupViewAddUser.getStudentsIdField().getText(),
                popupViewAddUser.getFirstnameField().getText(),
                popupViewAddUser.getLastnameField().getText(),
                popupViewAddUser.getEmailField().getText(),
                popupViewAddUser.getPasswordField().getText(),
                Role.STUDENT
            );

            closePopup();
        } else if (source.equals(popupViewAddUser.getCancelButton())) {
            closePopup();
        }
    }

}
