package main.model;

import main.model.Exceptions.NullAttributeException;

import java.util.List;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    int NO_MATCH = 0;
    
    String getName();
    
    int getAttributeCount();
    
    int getNumberOfMatches(List<Enum> attributes) throws NullAttributeException;
}
