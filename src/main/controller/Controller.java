package main.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.model.Attributes;
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
    
    public void setModel(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }
    
    @FXML // fx:id="menuLoadFile"
    private MenuItem menuLoadFile;
    
    @FXML // fx:id="menuReset"
    private MenuItem menuReset;
    
    @FXML //fx:id="menuDeleteData"
    private MenuItem menuDeleteData;
    
    @FXML // fx:id="legsCombo"
    private ComboBox<Enum> legsCombo;
    
    @FXML // fx:id="wingsCombo"
    private ComboBox<Enum> wingsCombo;
    
    @FXML // fx:id="flyCombo"
    private ComboBox<Enum> flyCombo;
    
    @FXML// fx:id="tailCombo"
    private ComboBox<Enum> tailCombo;
    
    @FXML // fx:id="natureCombo"
    private ComboBox<Enum> natureCombo;
    
    @FXML // fx:id="habitatCombo"
    private ComboBox<Enum> habitatCombo;
    
    @FXML // fx:id="activeCombo"
    private ComboBox<Enum> activeCombo;
    
    @FXML // fx:id="resultField"
    private TextField resultField;
    
    @FXML // fx:id="searchButton"
    private Button searchButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate Combo boxes
        legsCombo.setItems(FXCollections.observableArrayList(Attributes.Legs.values()));
        wingsCombo.setItems(FXCollections.observableArrayList(Attributes.Wings.values()));
        flyCombo.setItems(FXCollections.observableArrayList(Attributes.Fly.values()));
        tailCombo.setItems(FXCollections.observableArrayList(Attributes.Tail.values()));
        natureCombo.setItems(FXCollections.observableArrayList(Attributes.Nature.values()));
        habitatCombo.setItems(FXCollections.observableArrayList(Attributes.Habitat.values()));
        activeCombo.setItems(FXCollections.observableArrayList(Attributes.Active.values()));
        
        selectDefaultComboBoxItems();
    
        // User selects file to load
        menuLoadFile.setOnAction(event -> {
            showFileSelector();
        });
        
        menuReset.setOnAction(event -> {
            resetMenu();
        });
        
        menuDeleteData.setOnAction(event -> {
            deleteAllData();
        });
        
        searchButton.setOnAction(event -> {
           search();
        });
    }
    
    private void selectDefaultComboBoxItems() {
        legsCombo.getSelectionModel().select(0);
        wingsCombo.getSelectionModel().select(0);
        flyCombo.getSelectionModel().select(0);
        tailCombo.getSelectionModel().select(0);
        natureCombo.getSelectionModel().select(0);
        habitatCombo.getSelectionModel().select(0);
        activeCombo.getSelectionModel().select(0);
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
            resetMenu();
        }
    }
    
    // Clears the menu to default values
    private void resetMenu() {
        selectDefaultComboBoxItems();
        resultField.setText("");
    }
    
    // Deletes all searchable data from the system
    private void deleteAllData() {
        resetMenu();
        model.clearSearchables();
    }
    
    // Main search function when search button is pressed
    private void search() {
        // Build attributes search query
        List<Enum> attributes = new ArrayList<>();
        attributes.add(legsCombo.getSelectionModel().getSelectedItem());
        attributes.add(wingsCombo.getSelectionModel().getSelectedItem());
        attributes.add(flyCombo.getSelectionModel().getSelectedItem());
        attributes.add(tailCombo.getSelectionModel().getSelectedItem());
        attributes.add(natureCombo.getSelectionModel().getSelectedItem());
        attributes.add(habitatCombo.getSelectionModel().getSelectedItem());
        attributes.add(activeCombo.getSelectionModel().getSelectedItem());
    
        try {
            final String MATCH_NOT_FOUND_MESSAGE = "No matches were found.";
            Searchable result = model.search(attributes);
            resultField.setText(result != null ? result.getName() : MATCH_NOT_FOUND_MESSAGE);
        } catch (NullAttributeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
    
}
