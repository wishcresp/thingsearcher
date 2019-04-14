package main.model;

/**
 * Container class for an attribute name and value during a search query
 */
public class SearchValue {
    
    private final String name; // Attribute name
    private final String value; // Attribute value being searched for
    
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
    
    /**
     * Called in drop down ComboBoxes in view
     * @return Attribute value
     */
    @Override
    public String toString() {
        return this.value;
    }
    
}
