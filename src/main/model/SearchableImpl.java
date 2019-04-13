package main.model;

import main.model.Exceptions.AttributeValueCountMismatchException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the Searchable interface representing something that can be searched
 * and has a name and a number of attributes
 */
public class SearchableImpl implements Searchable, Serializable {
    
    private Map<String, String> attributeValues;
    private final String name;
    
    public SearchableImpl(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributeValues = attributes;
    }
    
    @Override
    public Map<String, String> getAttributeValues() {
        return this.attributeValues;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the number of matching attributeValues from a search result
     * @param attributes List of attributes stored in the model (ordered list)
     * @param searchValues List of String attribute values from search query (attribute ordering matches attributes)
     * @return The number of matching attributes to this searchable indicating desirability of search result. Immediately
     *      returns if a non matching attribute (not unknown) is found
     * @throws AttributeValueCountMismatchException Sanity check, the View should never provide more attributes than is
     *      stored in the model
     */
    @Override
    public int getNumberOfMatches(List<Attribute> attributes, List<String> searchValues)
            throws AttributeValueCountMismatchException {
        // Sanity check that attributes and searchValues are equal in size
        int numOfAttributes = attributes.size();
        if (numOfAttributes != searchValues.size()) {
            throw new AttributeValueCountMismatchException(
                    "Error: Loaded Attributes and search values should have an equal count"
            );
        }
        
        int numOfMatches = 0;
        // Compare each of the attributeValues
        for (int i = 0; i < numOfAttributes; i++) {
            // Attribute value searched for from the view
            String searchValue = searchValues.get(i);
            // This searchables attribute value
            String attributeValue = attributeValues.get(attributes.get(i).getName());
            
            // Check if the search value matches the searchable's value
            boolean match = searchValue.equals(attributeValue);
            if (match) {
                numOfMatches++;
            // Return immediately if a non "UNKNOWN" non-match is found
            } else if (!searchValue.equals(UNKNOWN)) {
                return NO_MATCH;
            }
        }
        return numOfMatches;
    }
    
}
