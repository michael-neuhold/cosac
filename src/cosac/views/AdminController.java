package cosac.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AdminController implements EventHandler<ActionEvent> {

    private Stage primaryStage = null;
    private AdminView adminView = new AdminView(this);

    public AdminController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public AdminView getView() {
        return adminView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(adminView.getMenuButton())) System.out.println("Menu");
        else if(source.equals(adminView.getOrderButton())) System.out.println("Order");
        else if(source.equals(adminView.getRestrictionButton())) System.out.println("Restrictions");
        else if(source.equals(adminView.getUserButton())) System.out.println("User");

    }

}
