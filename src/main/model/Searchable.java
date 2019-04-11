package main.model;

import main.model.Exceptions.NullAttributeException;

import java.util.List;
import java.util.Set;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    int NO_MATCH = 0;
    
    String getName();
    
    Set<Enum> getAttributes();
    
    int getNumberOfMatches(List<Enum> attributes) throws NullAttributeException;
    
}
