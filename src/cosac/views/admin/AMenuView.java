package cosac.views.admin;

import cosac.component.Component;
import cosac.controller.admin.AMenuController;
import cosac.model.FoodData;
import cosac.model.SectionData;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;


public class AMenuView extends BorderPane {

    private Button backButton = new Button("back");

    private Button addSectionButton = new Button("add section");
    private Button removeSectionButton = new Button("delete section");
    private Button addFoodButton = new Button("add food");
    private Button removeFoodButton = new Button("delete food");

    private TextField addSectionIdField = new TextField();
    private TextField addSectionNameField = new TextField();
    private TextField removeSectionIdField = new TextField();

    private TextField addFoodIdField = new TextField();
    private TextField addFoodSectionField = new TextField();
    private TextField addFoodNameField = new TextField();
    private TextField removeFoodIdField = new TextField();

    private TableColumn sectionTableID = new TableColumn("ID");
    private TableColumn sectionTableName = new TableColumn("Name");

    private TableColumn foodTableID = new TableColumn("ID");
    private TableColumn foodTableSectionID = new TableColumn("Section");
    private TableColumn foodTableName = new TableColumn("Name");

    private TableView<FoodData> foodTable = new TableView<>();
    private TableView<SectionData> sectionTable = new TableView<>();

    public AMenuView(AMenuController controller) {
        this.getStyleClass().add("window");
        this.setTop(Component.createHeader("menu"));
        this.setCenter(createTableViews());
        this.setRight(createUserControls());
        this.getRight().setId("right");
        this.setBottom(Component.createBackButton(backButton));
        setEventForwarding(controller);
    }

    private VBox createTableViews() {
        VBox tableWrapper = new VBox(10);
        tableWrapper.setId("menuTableWrapper");
        tableWrapper.getChildren().add(Component.createSubHeader("Sections"));
        tableWrapper.getChildren().add(createSectionTable());
        tableWrapper.getChildren().add(Component.createSubHeader("Food"));
        tableWrapper.getChildren().add(createFoodTable());
        return tableWrapper;
    }

    private TableView createSectionTable() {
        sectionTable.setEditable(true);
        sectionTableID.setCellValueFactory(new PropertyValueFactory<FoodData, String>("id"));
        sectionTableName.setCellValueFactory(new PropertyValueFactory<FoodData, String>("name"));

        sectionTable.getColumns().addAll(sectionTableID, sectionTableName);

        return sectionTable;
    }

    private TableView createFoodTable() {
        foodTable.setEditable(true);
        foodTableID.setCellValueFactory(new PropertyValueFactory<FoodData, String>("id"));
        foodTableSectionID.setCellValueFactory(new PropertyValueFactory<FoodData, String>("sectionId"));
        foodTableName.setCellValueFactory(new PropertyValueFactory<FoodData, String>("name"));

        foodTable.getColumns().addAll(foodTableID, foodTableSectionID, foodTableName);

        return foodTable;
    }

    private VBox createUserControls() {
        VBox buttonWrapper = new VBox(10);
        setStyleClassForButtons();
        setInputFieldProps();
        buttonWrapper.getChildren().addAll(addFoodIdField, addFoodSectionField);
        buttonWrapper.getChildren().addAll(addFoodNameField, addFoodButton, new Separator());
        buttonWrapper.getChildren().addAll(removeFoodIdField,removeFoodButton, new Separator());
        buttonWrapper.getChildren().addAll(addSectionIdField, addSectionNameField);
        buttonWrapper.getChildren().addAll(addSectionButton, new Separator());
        buttonWrapper.getChildren().addAll(removeSectionIdField, removeSectionButton);
        return buttonWrapper;
    }

    private void setStyleClassForButtons() {
        addFoodButton.getStyleClass().add("userMenuButton");
        removeFoodButton.getStyleClass().add("userMenuButton");
        addSectionButton.getStyleClass().add("userMenuButton");
        removeSectionButton.getStyleClass().add("userMenuButton");
    }

    private void setInputFieldProps() {
        addFoodIdField.getStyleClass().add("smallInputField");
        addFoodIdField.setPromptText("food id");

        addFoodSectionField.getStyleClass().add("smallInputField");
        addFoodSectionField.setPromptText("food section id");

        addFoodNameField.getStyleClass().add("smallInputField");
        addFoodNameField.setPromptText("food name");

        removeFoodIdField.getStyleClass().add("smallInputField");
        removeFoodIdField.setPromptText("food id");

        addSectionIdField.getStyleClass().add("smallInputField");
        addSectionIdField.setPromptText("section id");

        addSectionNameField.getStyleClass().add("smallInputField");
        addSectionNameField.setPromptText("section name");

        removeSectionIdField.getStyleClass().add("smallInputField");
        removeSectionIdField.setPromptText("section id");
    }

    private void setEventForwarding(AMenuController controller) {
        backButton.setOnAction(controller);
        addSectionButton.setOnAction(controller);
        removeSectionButton.setOnAction(controller);
        addFoodButton.setOnAction(controller);
        removeFoodButton.setOnAction(controller);
    }

    public void resetTextFields() {
        addSectionIdField.clear();
        addSectionNameField.clear();
        removeSectionIdField.clear();
        addFoodIdField.clear();
        addFoodSectionField.clear();
        addFoodNameField.clear();
        removeFoodIdField.clear();
    }

    public Button getBackButton() { return backButton; }

    public Button getAddSectionButton() {
        return addSectionButton;
    }

    public Button getRemoveSectionButton() {
        return removeSectionButton;
    }

    public Button getAddFoodButton() {
        return addFoodButton;
    }

    public Button getRemoveFoodButton() {
        return removeFoodButton;
    }

    public TextField getAddSectionIdField() {
        return addSectionIdField;
    }

    public TextField getAddSectionNameField() {
        return addSectionNameField;
    }

    public TextField getRemoveSectionIdField() {
        return removeSectionIdField;
    }

    public TextField getAddFoodIdField() {
        return addFoodIdField;
    }

    public TextField getAddFoodNameField() {
        return addFoodNameField;
    }

    public TextField getRemoveFoodIdField() {
        return removeFoodIdField;
    }

    public TextField getAddFoodSectionField() {
        return addFoodSectionField;
    }

    public TableView<FoodData> getFoodTable() {
        return foodTable;
    }

    public TableView<SectionData> getSectionTable() {
        return sectionTable;
    }
}
