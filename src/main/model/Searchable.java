package main.model;

import java.util.List;
import java.util.Map;

/**
 * Something that can be searched for in the Model
 */
public interface Searchable {
    
    // Returns the name of the searchable
    String getName();
    
    // Returns a list of a Searchable's attribute values
    Map<String,String> getAttributeValues();
    
    // For a given search query, return the number of matching attribute values
    int getNumberOfMatches(List<SearchValue> searchValues);
    
}
