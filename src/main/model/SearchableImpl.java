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
    
    private final String name;  // Name of the searchable
    private final Map<String,String> attributeValues; // Map of attribute values (key = attribute name)
    
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
    
    @Override
    public int getNumberOfMatches(List<SearchValue> searchValues) {
        int numOfMatches = 0;
        // Compare each of the attributes searched for
        for (SearchValue searchValue : searchValues) {
            final String key = searchValue.getName();
            final String value = searchValue.getValue();
            final boolean match = this.attributeValues.get(key).equals(value);
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
