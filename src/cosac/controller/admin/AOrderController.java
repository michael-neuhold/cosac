package cosac.controller.admin;

import cosac.SceneController;
import cosac.rmi.RMIClient;
import cosac.views.admin.AOrderView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AOrderController implements EventHandler<ActionEvent> {

    private AOrderView adminOrderView = new AOrderView(this, 2);
    private SceneController sceneController = null;

    public AOrderController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        new Thread( () -> updateTable() ).start();
    }

    public AOrderView getView() {
        return adminOrderView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source.equals(adminOrderView.getBackButton()))
            sceneController.mountPreviousScene();
    }

    private void updateTable() {
        Platform.runLater( () -> {
            this.adminOrderView.getOrderTable().setItems(
                    FXCollections.observableArrayList(
                            RMIClient.getOrderData()
                    )
            );
            this.adminOrderView.sortTable();
        });
    }

}
