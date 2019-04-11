package main.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.model.Attributes;
import main.model.Exceptions.AttributeCountException;
import main.model.Model;
import main.model.Searchable;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    private Model model;
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    @FXML // fx:id="menuReset"
    private MenuItem menuReset;
    
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
        
        // Add listener to menu item
        menuReset.setOnAction(event -> {
            selectDefaultComboBoxItems();
            resultField.setText("");
        });
        
        // Add listener to button
        searchButton.setOnAction(event -> {
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
                final String RESULT_MESSAGE = "Closest Match: ";
                final String MATCH_NOT_FOUND_MESSAGE = "No matches were found.";
                Searchable result = model.search(attributes);
                resultField.setText(result != null ? RESULT_MESSAGE + result.getName() : MATCH_NOT_FOUND_MESSAGE);
            } catch (AttributeCountException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
                alert.showAndWait();
            }
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
}
