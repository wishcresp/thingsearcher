package main.model;

import java.io.Serializable;
import java.util.*;

/**
 * Defines all the values and a message for each attribute in the Model
 */
public class Attribute implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String message;
    private List<String> values;
    private final String UNKNOWN = "UNKNOWN";
    
    public Attribute(String name, String message) {
        this.name = name;
        this.message = message;
        this.values = new ArrayList<>();
        values.add(UNKNOWN);
    }
    
    // Adds a value to the attribute if it does not already exist
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
