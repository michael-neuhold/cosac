package cosac.controller.admin;

import cosac.SceneController;
import cosac.views.admin.AOrderView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AOrderController implements EventHandler<ActionEvent> {

    private AOrderView adminOrderView = new AOrderView(this, 2);
    private SceneController sceneController = null;

    public AOrderController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        
        //ArrayList<TableView> tables = this.adminOrderView.gettabelViews();
        //for(TableView tabel : tables) tabel.setItems(DataContainer.getInstance().getOrderDataSets());
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

}
