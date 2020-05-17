package cosac.controller.admin.popup;

import cosac.SceneController;
import cosac.views.admin.popup.LockUserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LockUserController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private LockUserView lockUserView = new LockUserView(this);
    private SceneController sceneController = null;

    public LockUserController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public LockUserView getView() {
        return lockUserView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        System.out.println("ID: " + lockUserView.getStudentIdToLock().getCharacters());
        if(source.equals(lockUserView.getLockButton())) primaryStage.close();
        if(source.equals(lockUserView.getCancelButton())) primaryStage.close();
    }

}
