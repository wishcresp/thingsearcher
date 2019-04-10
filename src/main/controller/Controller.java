package main.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.model.Attributes;
import main.model.Exceptions.AttributeCountException;
import main.model.Exceptions.NoMatchException;
import main.model.Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    private Model model;
    
    public void setModel(Model model) {
        this.model = model;
    }
    
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
        wingsCombo.setItems(FXCollections.observableArrayList(Attributes.Feature.values()));
        flyCombo.setItems(FXCollections.observableArrayList(Attributes.Feature.values()));
        tailCombo.setItems(FXCollections.observableArrayList(Attributes.Feature.values()));
        natureCombo.setItems(FXCollections.observableArrayList(Attributes.Nature.values()));
        habitatCombo.setItems(FXCollections.observableArrayList(Attributes.Habitat.values()));
        activeCombo.setItems(FXCollections.observableArrayList(Attributes.Active.values()));
    
        // Default selected items
        legsCombo.getSelectionModel().select(0);
        wingsCombo.getSelectionModel().select(0);
        flyCombo.getSelectionModel().select(0);
        tailCombo.getSelectionModel().select(0);
        natureCombo.getSelectionModel().select(0);
        habitatCombo.getSelectionModel().select(0);
        activeCombo.getSelectionModel().select(0);
        
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
            
            String result = "No matches";
            try {
                result = model.search(attributes);
            } catch (AttributeCountException | NoMatchException e) {
                e.printStackTrace();
            }
            resultField.setText(result);
        });
    }
}
