package main.model;

import java.util.*;

public abstract class Attribute {
    
    private String message;
    private List<String> values;
    
    public Attribute(String message) {
        this.message = message;
        this.values = new ArrayList<>();
    }
    
    public Collection<String> values() {
        return this.values;
    }
    
    public boolean contains(String value) {
        return values.contains(value);
    }
    
    public String getMessage() {
        return message;
    }
    
}
