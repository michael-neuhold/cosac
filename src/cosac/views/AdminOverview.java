package cosac.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminOverview extends HBox {

    public AdminOverview(String id) {
        this.setId(id);
        GridPane loginPane = new GridPane();
        loginPane.setId("loginPane");
        GridPane buttonPane = createButtons();
        loginPane.add(buttonPane,0,0);
        this.getChildren().addAll(loginPane);

    }

    private GridPane createButtons() {
        GridPane buttons = new GridPane();
        buttons.setId("adminPane");

        Button test = new Button("Speisekarte");
        test.addEventHandler(ActionEvent.ACTION, event -> this.getScene().setRoot(new Login("login")));

        buttons.add(test,0,0);
        buttons.add(new Button("Benutzer"),1,0);
        buttons.add(new Button("Beschränkungen"),2,0);
        buttons.add(new Button("Bestellungen"),3,0);
        
        return buttons;
    }

}
