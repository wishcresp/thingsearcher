package main.model;

import main.model.Exceptions.AttributeCountException;

import java.util.List;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    int NO_MATCH = -1;
    
    String getName();
    
    int getAttributeCount();
    
    int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException;
}
