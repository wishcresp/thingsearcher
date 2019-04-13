package main.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.model.Attribute;
import main.model.SearchValue;
import main.model.Model;
import main.model.Searchable;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    
    private Model model;
    private Stage stage;
    private List<ComboBox<String>> comboBoxes = new ArrayList<>();
    // Store the names of attributes. Index of name corresponds to that of comboBoxes.
    private List<String> attributeNames = new ArrayList<>();
    
    public void setModel(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }
    
    // Dynamically populated GridPane
    private GridPane gridPane;
    
    @FXML // fx:id="menuLoadModel"
    private MenuItem menuLoadModel;
    
    @FXML // fx:id="menuLoadData"
    private MenuItem menuLoadData;
    
    @FXML // fx:id="menuReset"
    private MenuItem menuReset;
    
    @FXML // fx:id="menuDeleteData"
    private MenuItem menuDeleteData;
    
    @FXML // fx:id="borderPane"
    private BorderPane borderPane;
    
    @FXML // fx:id="resultField"
    private TextField resultField;
    
    @FXML // fx:id="searchButton"
    private Button searchButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup GridPane
        int padding = 5;
        gridPane = new GridPane();
        gridPane.setHgap(padding);
        gridPane.setVgap(padding);
        gridPane.setPadding(new Insets(padding,padding,padding,padding));
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(constraints, constraints);
        borderPane.setCenter(gridPane);
        
        // Actions
        menuLoadModel.setOnAction(event -> loadAttributeModelData());
        menuLoadData.setOnAction(event -> loadDataFile());
        menuReset.setOnAction(event -> refreshInterface());
        menuDeleteData.setOnAction(event -> deleteAllData());
        searchButton.setOnAction(event -> search());
    }
    
    // Clears the GridPane and fields for reconstruction
    public void refreshInterface() {
        // Reset Interface
        comboBoxes.clear();
        attributeNames.clear();
        resultField.clear();
        gridPane.getChildren().clear();
        
        // Only load if attributes exist in the system
        if (!model.getLoadedAttributes().values().isEmpty()) {
            // Maintain attributes in an ordered list for rendering to interface
            List<Attribute> attributes = new ArrayList<>(model.getLoadedAttributes().values());
            // Render attribute message and values
            int length = attributes.size();
            for (int i = 0; i < length; i++) {
                Attribute attribute = attributes.get(i);
    
                Label label = new Label();
                label.setText(attribute.getMessage());
                label.setAlignment(Pos.CENTER_RIGHT);
                GridPane.setHalignment(label, HPos.RIGHT);
    
                ComboBox<String> comboBox = new ComboBox<>();
                comboBox.setPrefWidth(150);
                comboBox.setItems(FXCollections.observableList(attribute.getValues()));
                comboBox.getSelectionModel().select(0);
                comboBoxes.add(comboBox);
                
                attributeNames.add(attribute.getName());
                
                gridPane.add(label, 0, i + 1);
                gridPane.add(comboBox, 1, i + 1);
            }
        }
    }
    
    // Load the SearchValue model from file when user presses menu item
    private void loadAttributeModelData() {
        File file = loadFile();
        if (file != null) {
            model.loadAttributes(file.getPath());
            refreshInterface();
            // Check if an error occurred when loading
            if(model.getErrorFlag()) {
                showErrorMessage(model.getLoadErrorMessage());
                model.setErrorFlag(false);
            }
        }
    }
    
    // Load Searchables from file when user presses menu item
    private void loadDataFile() {
        if (!model.getLoadedAttributes().values().isEmpty()) {
            File file = loadFile();
            if (file != null) {
                model.loadSearchables(file.getPath());
                refreshInterface();
                // Check if an error occurred when loading
                if(model.getErrorFlag()) {
                    showErrorMessage(model.getLoadErrorMessage());
                    model.setErrorFlag(false);
                }
            }
        } else {
            showErrorMessage("Error: Please load SearchValue model file first");
        }
    }
    
    // Prompts the user to select a file to load
    private File loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open data file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        return fileChooser.showOpenDialog(stage);
    }
    
    // Clears all Attributes and Searchables from the system
    private void deleteAllData() {
        model.clearAttributes();
        model.clearSearchables();
        refreshInterface();
    }
    
    // Perform a search using the ComboBox selections and set the TextField
    private void search() {
        // Build attribute values search query
        List<SearchValue> attributeValues = new ArrayList<>();
        int length = comboBoxes.size();
        for (int i = 0; i < length; i++) {
            String value = comboBoxes.get(i).getSelectionModel().getSelectedItem();
            if (!value.equals(model.getDefaultValue())) {
                attributeValues.add(new SearchValue(attributeNames.get(i), value));
            }
        }
        Searchable result = model.search(attributeValues);
        resultField.setText(result != null ? result.getName() : "No matches were found.");
    }
    
    // Show an error message to the user in a dialog
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
        alert.showAndWait();
    }
    
}
