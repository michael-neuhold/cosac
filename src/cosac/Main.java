package cosac;

import cosac.views.AdminOverview;
import cosac.views.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene1 = new Scene(new Login("login"), 400,400);
        Scene scene2 = new Scene(new AdminOverview("adminPage"),400,400);
        scene1.getStylesheets().add(getClass().getResource("style/test.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("style/test.css").toExternalForm());
        primaryStage.setTitle("CosaC");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
