package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AUserController;
import cosac.model.UserData;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.BooleanStringConverter;


public class AUserView extends BorderPane {

    private Button backButton = new Button("back");
    private Button addUserButton = new Button("add");
    private Button lockUserButton = new Button("lock");
    private Button updateButton = new Button("update");

    private TableColumn studentIDCol = new TableColumn("ID");
    private TableColumn firstnameCol = new TableColumn("Vorname");
    private TableColumn lastnameCol = new TableColumn("Nachname");
    private TableColumn emailCol = new TableColumn("Email");
    private TableColumn lockCol = new TableColumn("Locked");

    private TableView<UserData> userTable = new TableView<>();

    public AUserView(AUserController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("user"));
        this.setCenter(createTableView());
        this.setRight(createUserControls());
        this.getRight().setId("right");
        this.setBottom(Component.createBackUpdateButton(backButton, updateButton));
        this.setEventForwarding(controller);
    }

    private TableView createTableView() {
        userTable.setId("userTable");
        userTable.setEditable(true);

        studentIDCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("studentID"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
        lockCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("locked"));

        setColumnsEditable();
        userTable.getColumns().addAll(studentIDCol, firstnameCol, lastnameCol, emailCol, lockCol);

        return userTable;
    }

    private void setColumnsEditable() {
        studentIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lockCol.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
    }

    private VBox createUserControls() {
        VBox buttonWrapper = new VBox(10);
        setStyleClassForButtons();
        buttonWrapper.getChildren().addAll(addUserButton,lockUserButton);
        return buttonWrapper;
    }

    private void setStyleClassForButtons() {
        addUserButton.getStyleClass().add("userMenuButton");
        lockUserButton.getStyleClass().add("userMenuButton");
    }

    private void setEventForwarding(AUserController controller) {
        backButton.setOnAction(controller);
        addUserButton.setOnAction(controller);
        lockUserButton.setOnAction(controller);
        updateButton.setOnAction(controller);

        studentIDCol.setOnEditCommit(controller);
        firstnameCol.setOnEditCommit(controller);
        lastnameCol.setOnEditCommit(controller);
        emailCol.setOnEditCommit(controller);
        lockCol.setOnEditCommit(controller);
    }

    public Button getBackButton() { return backButton; }
    public Button getAddUserButton() { return addUserButton; }
    public Button getLockUserButton() { return lockUserButton; }
    public Button getUpdateButton() { return updateButton; }

    public TableView getUserTable() { return userTable; }

}
