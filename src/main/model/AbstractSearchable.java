package main.model;

import main.model.Exceptions.AttributeCountException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractSearchable implements Searchable {
    
    String ATTRIBUTE_COUNT_EXCEPTION_MESSAGE = "Error: The number of provided attributes is wrong";
    Set<Enum> attributes = new HashSet<>();
    private final String name;
    
    AbstractSearchable(String name) {
        this.name = name;
    }
    
    public Set<Enum> getAttributes() {
        return this.attributes;
    }
    
    public String getName() {
        return this.name;
    }
    
    public abstract int getAttributeCount();
    
    public abstract int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException;
}
