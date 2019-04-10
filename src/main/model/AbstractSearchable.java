package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchable implements Searchable {
    
    String ATTRIBUTE_COUNT_EXCEPTION_MESSAGE = "Error: The number of provided attributes is wrong";
    List<Enum> attributes = new ArrayList<>();
    private final String name;
    
    AbstractSearchable(String name) {
        this.name = name;
    }
    
    AbstractSearchable() {
        this.name = UNKNOWN;
    }
    
    public List<Enum> getAttributes() {
        return this.attributes;
    }
    
    public String getName() {
        return this.name;
    }
    
    public abstract int getAttributeCount();
    
    public abstract int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException;
}
