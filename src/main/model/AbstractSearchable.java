package main.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractSearchable implements Searchable, Serializable {
    
    Set<Enum> attributes = new HashSet<>();
    private final String name;
    
    AbstractSearchable(String name) {
        this.name = name;
    }
    
    public Set<Enum> getAttributes() {
        return this.attributes;
    }
    
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the number of matching attributes from a search result
     * @param attributes List of Enum attributes
     * @return The number of attribute matches indicating the desirability of the search result. Returns NO_MATCH if a
     * non UNKNOWN attribute does not match.
     */
    public int getNumberOfMatches(List<Enum> attributes) {
        int numOfMatches = 0;
        // Compare each of the attributes
        for (Enum attribute : attributes) {
            boolean match = this.attributes.contains(attribute);
            if (match) {
                numOfMatches++;
            } else if (!attribute.name().equals(UNKNOWN)) {
                return NO_MATCH;
            }
        }
        return numOfMatches;
    }
    
}
