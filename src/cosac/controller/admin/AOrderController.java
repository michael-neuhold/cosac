package cosac.controller.admin;

import cosac.model.DataContainer;
import cosac.views.admin.AOrderView;
import cosac.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AOrderController implements EventHandler<ActionEvent> {

    private AOrderView adminOrderView = new AOrderView(this, DataContainer.getRestrictionDataSets().size());
    private SceneController sceneController = null;

    public AOrderController(Stage primaryStage) {
        this.sceneController = new SceneController(primaryStage);
        var lists = this.adminOrderView.getListViews();
        for(var list : lists) list.setItems(DataContainer.getOrderDataSets());
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
