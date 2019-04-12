package main.model;

import main.model.Exceptions.AttributeValueCountMismatchException;

import java.util.List;
import java.util.Map;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    int NO_MATCH = 0;
    
    String getName();
    
    Map<String, String> getAttributeValues();
    
    int getNumberOfMatches(List<Attribute> attributes, List<String> searchValues) throws AttributeValueCountMismatchException;
    
}
