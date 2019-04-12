package main.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.model.Attribute;
import main.model.Exceptions.AttributeValueCountMismatchException;
import main.model.Exceptions.NullAttributeException;
import main.model.Model;
import main.model.Searchable;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    private Model model;
    private Stage stage;
    private List<ComboBox<String>> comboBoxes = new ArrayList<>();
    
    public void setModel(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }
    
    private GridPane gridPane;
    
    @FXML // fx:id="menuLoadFile"
    private MenuItem menuLoadFile;
    
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
        gridPane = new GridPane();
//        gridPane.getColumnConstraints().setAll(new ColumnConstraints(30, 200, 200, Priority.SOMETIMES, HPos.CENTER, false));
//        gridPane.getRowConstraints().setAll(new RowConstraints(10, 30, 30));
        borderPane.setCenter(gridPane);
        
        // User selects file to load
        menuLoadFile.setOnAction(event -> showFileSelector());
        menuReset.setOnAction(event -> updateInterface());
        menuDeleteData.setOnAction(event -> deleteAllData());
        searchButton.setOnAction(event -> search());
    }
    
    private void updateInterface() {
        resultField.setText("");
        gridPane.getChildren().clear();
        List<Attribute> attributes = new ArrayList<>(model.getLoadedAttributes().values());
        
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
            
            gridPane.add(label, 0, i + 1);
            gridPane.add(comboBox, 1, i + 1);
        }
    }
    
    // Prompts the user to select a file to load
    private void showFileSelector() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open data file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        // Checks if file was selected
        if (file != null) {
            model.loadFile(file);
            updateInterface();
        }
    }
    
    // Deletes all searchable data from the system
    private void deleteAllData() {
        model.clearSearchables();
        updateInterface();
    }
    
    // Main search function when search button is pressed
    private void search() {
        // Build attribute values search query
        List<String> attributes = new ArrayList<>();
        for (ComboBox<String> comboBox : comboBoxes) {
            attributes.add(comboBox.getSelectionModel().getSelectedItem());
        }
        
        // Show the result or an error message
        try {
            Searchable result = model.search(attributes);
            resultField.setText(result != null ? result.getName() : "No matches were found.");
        } catch (NullAttributeException | AttributeValueCountMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
    
}
