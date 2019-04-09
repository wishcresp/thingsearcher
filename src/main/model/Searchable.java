package main.model;

import java.util.List;

public interface Searchable {
    
    String UNKNOWN = "UNKNOWN";
    String ATTRIBUTE_COUNT_EXCEPTION_MESSAGE = "Error: The number of provided attributes is wrong";
    
    String getName();
    
    int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException;
}
