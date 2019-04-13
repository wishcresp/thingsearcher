package main.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the Searchable interface representing something that can be searched
 * and has a name and a number of attributes
 */
public class SearchableImpl implements Searchable, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Map<String,String> attributeValues;
    private final String name;
    
    public SearchableImpl(String name, Map<String, String> attributeValues) {
        this.name = name;
        this.attributeValues = attributeValues;
    }
    
    @Override
    public Map<String,String> getAttributeValues() {
        return this.attributeValues;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the number of matching attribute values for a search result
     * @param searchValues List of AttributeValues from search query
     * @return The number of matching attributes to this searchable indicating desirability of search result. Immediately
     *      returns if a non matching attribute is found
     */
    @Override
    public int getNumberOfMatches(List<SearchValue> searchValues) {
        int numOfMatches = 0;
        // Compare each of the attributes searched for
        for (SearchValue searchValue : searchValues) {
            String key = searchValue.getName();
            String value = searchValue.getValue();
            boolean match = this.attributeValues.get(key).equals(value);
            if (match) {
                numOfMatches++;
            // Not a match
            } else {
                return 0;
            }
        }
        return numOfMatches;
    }
    
}
