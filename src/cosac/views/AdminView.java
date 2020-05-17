package cosac.views;

import cosac.controller.AdminController;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class AdminView extends BorderPane {

    private Button menuButton, userButton, restrictionButton, orderButton;

    public AdminView(AdminController controller) {
        initButtons(controller);
        this.setTop(createHeaderTitle());
        this.setCenter(createButtonWrapper());
    }

    private void initButtons(AdminController controller) {
        menuButton = new Button("Speisekarte");
        userButton = new Button("Benutzer");
        restrictionButton = new Button("Beschränkungen");
        orderButton = new Button("Bestellungen");

        menuButton.getStyleClass().add("adminMenuButton");
        userButton.getStyleClass().add("adminMenuButton");
        restrictionButton.getStyleClass().add("adminMenuButton");
        orderButton.getStyleClass().add("adminMenuButton");

        menuButton.setOnAction(controller);
        userButton.setOnAction(controller);
        restrictionButton.setOnAction(controller);
        orderButton.setOnAction(controller);
    }

    private HBox createHeaderTitle() {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        headerWrapper.getChildren().add(new Text("admin overview"));
        return headerWrapper;
    }

    private VBox createButtonWrapper() {
        VBox buttonWrapper = new VBox(20);
        buttonWrapper.getStyleClass().add("adminButtonWrapper");
        buttonWrapper.getChildren().addAll(menuButton, userButton, restrictionButton, orderButton);
        return buttonWrapper;
    }

    public Button getMenuButton() { return menuButton; }
    public Button getUserButton() { return userButton; }
    public Button getOrderButton() { return orderButton; }
    public Button getRestrictionButton() { return restrictionButton; }

}
