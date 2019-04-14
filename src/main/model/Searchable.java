package main.model;

import java.util.List;
import java.util.Map;

/**
 * Represents Something that can be searched for in the Model
 */
public interface Searchable {
    
    /**
     * Return the name of the searchable
     * @return searchable name
     */
    String getName();
    
    /**
     * Returns a map of a Searchables attribute values
     * (key = attribute name)
     * @return map of attribute values
     */
    Map<String,String> getAttributeValues();
    
    /**
     * Returns the number of matching attribute values for a search query
     * @param searchValues List of attribute values from search query
     * @return The number of matching attributes to this searchable indicating desirability of search result.
     * Immediately returns 0 if a non matching attribute is found
     */
    int getNumberOfMatches(List<SearchValue> searchValues);
    
}
