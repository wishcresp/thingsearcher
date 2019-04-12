package main.model;

import main.model.Exceptions.AttributeValueCountMismatchException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
     * @param attributes List of Enum attributeValues
     * @return The number of attribute matches indicating the desirability of the search result. Returns NO_MATCH if a
     * non UNKNOWN attribute does not match.
     */
    @Override
    public int getNumberOfMatches(List<Attribute> attributes, List<String> searchValues) throws AttributeValueCountMismatchException {
        int numOfAttributes = attributes.size();
        if (numOfAttributes != searchValues.size()) {
            System.out.println(attributes.size() + " " + searchValues.size());
            throw new AttributeValueCountMismatchException("Error: This should not have happened. " +
                    "Loaded Attributes and search values should have an equal count");
        }
        
        int numOfMatches = 0;
        // Compare each of the attributeValues
        for (int i = 0; i < numOfAttributes; i++) {
            // Attribute value searched for from the view
            String searchValue = searchValues.get(i);
            // This searchables attribute value
            String attributeValue = attributeValues.get(attributes.get(i).getKey());
            
            // Check if the search value matches the searchable's value
            boolean match = searchValue.equals(attributeValue);
            if (match) {
                numOfMatches++;
            } else if (!searchValue.equals(UNKNOWN)) {
                return NO_MATCH;
            }
        }
        return numOfMatches;
    }
    
}
