package cosac.views;

import cosac.data.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserSettings extends BorderPane {

    private ObservableList<UserData> data = FXCollections.observableArrayList(
        new UserData("Michael", "Neuhold", "michi.neuhold@gmail.com", 21),
        new UserData("Julian", "Jany", "julian.jany@gmail.com", 21),
        new UserData("Maxi", "Ranger", "maxi.ranger@gmail.com", 24),
        new UserData("Claudia", "Wimmeder", "claudia.wimmeder@gmail.com", 20),
        new UserData("Pia", "Schaenzle", "pia.schaenzle@gmail.com", 20)
    );

    public UserSettings() {
        this.setTop(createHeader());
        this.setCenter(createTableView());
        this.setRight(createUserControls());
        this.getRight().setId("right");
    }

    private HBox createHeader() {
        HBox headerWrapper = new HBox();
        headerWrapper.getStyleClass().add("header");
        headerWrapper.getChildren().add(new Text("user settings"));
        return headerWrapper;
    }

    private TableView createTableView() {

        TableView<UserData> table = new TableView<>();
        table.setId("userTable");
        TableColumn firstnameCol = new TableColumn("Vorname");
        TableColumn lastnameCol = new TableColumn("Nachname");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn ageCol = new TableColumn("Alter");

        firstnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
        ageCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("age"));

        table.getColumns().addAll(firstnameCol, lastnameCol, emailCol, ageCol);
        table.setItems(data);

        return table;
    }

    private VBox createUserControls() {
        VBox buttonWrapper = new VBox(10);
        Button btn1 = new Button("add");
        Button btn2 = new Button("update");
        Button btn3 = new Button("lock");

        btn1.getStyleClass().add("userMenuButton");
        btn2.getStyleClass().add("userMenuButton");
        btn3.getStyleClass().add("userMenuButton");
        buttonWrapper.getChildren().addAll(btn1,btn2,btn3);
        return buttonWrapper;
    }

}
