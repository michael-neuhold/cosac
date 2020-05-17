package cosac.controller.admin.popup;

import cosac.SceneController;
import cosac.views.admin.popup.AddUserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AddUserController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private AddUserView addUserView = new AddUserView(this);
    private SceneController sceneController = null;

    public AddUserController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneController = new SceneController(primaryStage);
    }

    public AddUserView getView() {
        return addUserView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        System.out.println("ID: " + addUserView.getStudentsIdField().getCharacters());
        System.out.println("Firstname: " + addUserView.getFirstnameField().getCharacters());
        System.out.println("Lastname: " + addUserView.getLastnameField().getCharacters());
        System.out.println("Email: " + addUserView.getEmailField().getCharacters());

        if(source.equals(addUserView.getAddButton())) primaryStage.close();
        if(source.equals(addUserView.getCancelButton())) primaryStage.close();
    }

}
