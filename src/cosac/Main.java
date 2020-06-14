package cosac;

import cosac.rmi.Protocol;
import cosac.rmi.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Thread initializeDataContainer = new Thread( () -> {
            RMIClient.connect(Protocol.GET_FOOD_DATA_SETS, null);
            RMIClient.connect(Protocol.GET_ORDER_DATA_SETS, null);
            RMIClient.connect(Protocol.GET_RESTRICTION_DATA_SETS, null);
            RMIClient.connect(Protocol.GET_SECTION_DATA_SETS, null);
        });
        initializeDataContainer.start();
        SceneController sceneController = new SceneController(primaryStage);
        sceneController.mountNewScene(SceneType.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
