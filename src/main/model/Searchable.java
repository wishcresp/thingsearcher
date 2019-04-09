package main.model;

import java.util.List;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    
    String getName();
    
    int getAttributeCount();
    
    int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException;
}
