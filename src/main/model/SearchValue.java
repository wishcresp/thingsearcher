package main.model;

/**
 * Container class for an attribute name and value being passed during a search
 */
public class SearchValue {
    
    private String name;
    private String value;
    
    public SearchValue(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
    
}
