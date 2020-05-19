package cosac.controller.admin;

import cosac.SceneType;
import cosac.model.UserData;
import cosac.views.admin.AUserView;
import cosac.SceneController;
import cosac.views.admin.popup.AddUserView;
import cosac.views.admin.popup.LockUserView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AUserController implements EventHandler<ActionEvent> {

    private Stage popupStage = null;

    private AUserView adminUserView = new AUserView(this);
    private SceneController sceneController = null;

    private AddUserView popupViewAddUser = null;
    private LockUserView popupLockUser = null;

    // data
    private ObservableList<UserData> data = FXCollections.observableArrayList(
            new UserData("s1","Michael", "Neuhold", "michi.neuhold@gmail.com", false),
            new UserData("s2","Julian", "Jany", "julian.jany@gmail.com", false),
            new UserData("s3","Maxi", "Ranger", "maxi.ranger@gmail.com", false),
            new UserData("s4","Claudia", "Wimmeder", "claudia.wimmeder@gmail.com", false),
            new UserData("s5","Pia", "Schaenzle", "pia.schaenzle@gmail.com", false)
    );

    public AUserController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        this.adminUserView.getUserTable().setItems(data);
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
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminUserView.getBackButton())) sceneController.mountPreviousScene();
        else if(source.equals(adminUserView.getAddUserButton())) {
            System.out.println("add user");
            //sceneController.openPopUp(SceneType.ADMIN_ADD_USER_VIEW);
            popupViewAddUser = new AddUserView(this);
            showPopUp(popupViewAddUser);
        }
        else if(source.equals(adminUserView.getLockUserButton())) {
            System.out.println("lock user");
            //sceneController.openPopUp(SceneType.ADMIN_LOCK_USER_VIEW);
            popupLockUser = new LockUserView(this);
            showPopUp(popupLockUser);
        }

        if(popupViewAddUser != null) handleAddUserPopup(source);

        if(popupLockUser != null) handleLockUserPopup(source);
    }

    private void handleLockUserPopup(Object source) {
        if (source.equals(popupLockUser.getLockButton())) {

            String id = popupLockUser.getStudentIdToLock().getText();
            for(UserData user : data) {
                if(user.getStudentID().equals(id)) {
                    user.setLock(true);
                }
            }
            adminUserView.getUserTable().refresh();
            closePopup();
        }
        if (source.equals(popupLockUser.getCancelButton())) {
            closePopup();
        }
    }

    private void handleAddUserPopup(Object source) {
        if (source.equals(popupViewAddUser.getAddButton())) {
            data.add(
                    new UserData(
                            popupViewAddUser.getStudentsIdField().getText(),
                            popupViewAddUser.getFirstnameField().getText(),
                            popupViewAddUser.getLastnameField().getText(),
                            popupViewAddUser.getEmailField().getText(),
                            true
                    )
            );
            closePopup();
        }
        if (source.equals(popupViewAddUser.getCancelButton())) {
            closePopup();
        }
    }

}
