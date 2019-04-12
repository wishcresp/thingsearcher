package main.model;

import java.util.*;

public class Attribute {
    
    private String message;
    private List<String> values;
    
    public Attribute(String message) {
        this.message = message;
        this.values = new ArrayList<>();
    }
    
    // Adds a value to the attribute if it does not already exist
    public void addValue(String value) {
        if (!this.values.contains(value)) {
            this.values.add(value);
        }
    }
    
    public Collection<String> getValues() {
        return this.values;
    }
    
    public String getMessage() {
        return message;
    }
    
}
