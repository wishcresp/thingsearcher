package main.model;

import java.io.Serializable;
import java.util.*;

/**
 * Defines all the values and a message for each attribute in the Model
 */
public class Attribute implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final String name; // Attribute name
    private final String message; // Question/Message to show in the view
    private final List<String> values; // List of possible attribute values
    
    public Attribute(String name, String message, String defaultAttributeValue) {
        this.name = name;
        this.message = message;
        this.values = new ArrayList<>();
        // Add a default value
        values.add(defaultAttributeValue);
    }
    
    /**
     * Add a non existing value to the attribute
     * @param value attribute value
     */
    public void addValue(String value) {
        if (!this.values.contains(value)) {
            this.values.add(value);
        }
    }
    
    public List<String> getValues() {
        return this.values;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getMessage() {
        return this.message;
    }
    
}
