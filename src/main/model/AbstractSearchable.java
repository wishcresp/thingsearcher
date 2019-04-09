package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchable implements Searchable {
    
    private final String name;
    List<Enum> attributes = new ArrayList<>();
    
    public AbstractSearchable(String name) {
        this.name = name;
    }
    
    AbstractSearchable() {
        this.name = UNKNOWN;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Enum> getAttributes() {
        return this.attributes;
    }
    
    abstract int getAttributeCount();
    
}
