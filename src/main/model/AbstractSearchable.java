package main.model;

import main.model.Exceptions.NullAttributeException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractSearchable implements Searchable, Serializable {
    
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
    
    public abstract int getNumberOfMatches(List<Enum> attributes);
    
    public abstract int getAttributeCount();
}
