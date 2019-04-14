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

/**
 * Controller links to IntegradevSearcher.fxml
 * Handles interaction with the view
 */
public class Controller implements Initializable {
    
    private static final String LOAD_ERROR_MESSAGE = "Incorrectly formatted file. Some data was not loaded";

    private List<ComboBox<SearchValue>> comboBoxes = new ArrayList<>();
    
    private Model model;
    private Stage stage;
    
    // GridPane populated with Attributes and Searchables
    private GridPane attributePane;
    
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
        attributePane = new GridPane();
        attributePane.setHgap(padding);
        attributePane.setVgap(padding);
        attributePane.setPadding(new Insets(padding,padding,padding,padding));
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(50);
        attributePane.getColumnConstraints().addAll(constraints, constraints);
        borderPane.setCenter(attributePane);
        
        // Actions
        menuLoadModel.setOnAction(event -> loadAttributeModelData());
        menuLoadData.setOnAction(event -> loadDataFile());
        menuReset.setOnAction(event -> refreshInterface());
        menuDeleteData.setOnAction(event -> deleteAllData());
        searchButton.setOnAction(event -> search());
    }
    
    /**
     * Set the references to the model and stage
     * @param model Model containing Searchable and Attribute data
     * @param stage Main JavaFX stage. Used for showing dialog error messages
     */
    public void setModel(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }
    
    /**
     * Clears the GridPane and fields for reconstruction. Called when files are loaded and data model changes.
     */
    public void refreshInterface() {
        // Reset Interface
        comboBoxes.clear();
        resultField.clear();
        attributePane.getChildren().clear();
        
        // Only load if attributes exist in the system
        if (!model.getLoadedAttributes().values().isEmpty()) {
            // Maintain attributes in an ordered list for rendering to interface
            Collection<Attribute> attributes = model.getLoadedAttributes().values();
            // Render attribute message and values
            int count = 0;
            for (Attribute attribute : attributes) {
                Label label = new Label();
                label.setText(attribute.getMessage());
                label.setAlignment(Pos.CENTER_RIGHT);
                GridPane.setHalignment(label, HPos.RIGHT);
                
                ComboBox<SearchValue> comboBox = new ComboBox<>();
                comboBox.setPrefWidth(150);
                
                // Create SearchValues for the attribute
                final List<SearchValue> values = new ArrayList<>();
                for (String value : attribute.getValues()) {
                    values.add(new SearchValue(attribute.getName(), value));
                }
                
                comboBox.setItems(FXCollections.observableList(values));
                comboBox.getSelectionModel().select(0);
                comboBoxes.add(comboBox);
                
                attributePane.add(label, 0, count + 1);
                attributePane.add(comboBox, 1, count + 1);
                count++;
            }
        }
    }
    
    /**
     * Load the SearchValue model from file when user selects menu item
     */
    private void loadAttributeModelData() {
        File file = loadFile("Open attribute model file");
        if (file != null) {
            model.loadAttributes(file.getPath());
            refreshInterface();
            // Check if an error occurred when loading
            if (model.getErrorFlag()) {
                showErrorMessage(LOAD_ERROR_MESSAGE);
                model.setErrorFlag(false);
            }
        }
    }
    
    /**
     * Load Searchables from file when user selects menu item
     */
    private void loadDataFile() {
        if (!model.getLoadedAttributes().values().isEmpty()) {
            File file = loadFile("Open searchable data file");
            if (file != null) {
                model.loadSearchables(file.getPath());
                refreshInterface();
                // Check if an error occurred when loading
                if (model.getErrorFlag()) {
                    showErrorMessage(LOAD_ERROR_MESSAGE);
                    model.setErrorFlag(false);
                }
            }
        } else {
            showErrorMessage("Error: Please load attribute model file first");
        }
    }
    
    /**
     * Prompts the user to select a file to load
     * @param message Instructional string to display in window
     * @return The selected File
     */
    private File loadFile(String message) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(message);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        return fileChooser.showOpenDialog(stage);
    }
    
    /**
     * Clears all Attributes and Searchables from the model
     */
    private void deleteAllData() {
        model.clearAttributes();
        model.clearSearchables();
        refreshInterface();
    }
    
    /**
     * Get the SearchValues from the ComboBoxes and perform a search.
     * Set the TextField with the result.
     */
    private void search() {
        // Build attribute values search query
        final List<SearchValue> attributeValues = new ArrayList<>();
        // Get SearchValue from each ComboBox
        for (ComboBox<SearchValue> comboBox : comboBoxes) {
            final SearchValue searchValue = comboBox.getSelectionModel().getSelectedItem();
            // Ignore SearchValue if value is the default attribute value
            if (!searchValue.getValue().equals(model.getDefaultAttributeValue())) {
                attributeValues.add(searchValue);
            }
        }
        final Searchable result = model.search(attributeValues);
        resultField.setText(result != null ? result.getName() : "No matches were found.");
    }
    
    /**
     * Show an error message to the user in a dialog
     * @param message Message to display
     */
    private void showErrorMessage(String message) {
        final Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
        alert.showAndWait();
    }
    
}
