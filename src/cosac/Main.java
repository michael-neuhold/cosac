package cosac;

import cosac.views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Scene scene1 = new Scene(new AdminOrderView(), 800,1000);
        Scene scene2 = new Scene(new Admin(),400,600);
        scene1.getStylesheets().add(getClass().getResource("style/test.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("style/test.css").toExternalForm());
        primaryStage.setTitle("CosaC");
        primaryStage.setScene(scene1);
        primaryStage.show();*/


        Scene scene = new Scene(new LoginController(primaryStage).getView(), 800,800);
        scene.getStylesheets().add(getClass().getResource("style/test.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
