package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AUserController;
import cosac.model.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class AUserView extends BorderPane {

    private Button backButton = new Button("back");
    private Button addUserButton = new Button("add");
    private Button lockUserButton = new Button("lock");

    private ObservableList<UserData> data = FXCollections.observableArrayList(
            new UserData("s1","Michael", "Neuhold", "michi.neuhold@gmail.com", 21),
            new UserData("s2","Julian", "Jany", "julian.jany@gmail.com", 21),
            new UserData("s3","Maxi", "Ranger", "maxi.ranger@gmail.com", 24),
            new UserData("s4","Claudia", "Wimmeder", "claudia.wimmeder@gmail.com", 20),
            new UserData("s5","Pia", "Schaenzle", "pia.schaenzle@gmail.com", 20)
    );

    public AUserView(AUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("user"));
        this.setCenter(createTableView());
        this.setRight(createUserControls());
        this.getRight().setId("right");
        this.setBottom(Component.createBackButton(backButton));
        backButton.setOnAction(controller);
        addUserButton.setOnAction(controller);
        lockUserButton.setOnAction(controller);
    }

    private VBox createUserControls() {
        VBox buttonWrapper = new VBox(10);
        addUserButton.getStyleClass().add("userMenuButton");
        lockUserButton.getStyleClass().add("userMenuButton");
        buttonWrapper.getChildren().addAll(addUserButton,lockUserButton);
        return buttonWrapper;
    }

    private TableView createTableView() {
        TableView<UserData> table = new TableView<>();
        table.setId("userTable");
        table.setEditable(true);
        TableColumn studentIDCol = new TableColumn("ID");
        TableColumn firstnameCol = new TableColumn("Vorname");
        TableColumn lastnameCol = new TableColumn("Nachname");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn ageCol = new TableColumn("Alter");

        studentIDCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("studentID"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
        ageCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("age"));

        table.getColumns().addAll(studentIDCol, firstnameCol, lastnameCol, emailCol, ageCol);
        table.setItems(data);

        return table;
    }

    public Button getBackButton() { return backButton; };
    public Button getAddUserButton() { return addUserButton; };
    public Button getLockUserButton() { return lockUserButton; };

}
