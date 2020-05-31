package cosac;

import cosac.client.DataContainer;
import cosac.server.Server;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        DataContainer.getInstance().initialize();
        SceneController sceneController = new SceneController(primaryStage);
        sceneController.mountNewScene(SceneType.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
