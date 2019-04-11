package main.model;

import main.model.Exceptions.AttributeCountException;

import java.util.List;

public class AnimalSearchable extends AbstractSearchable {
    
    public AnimalSearchable(String name, Attributes.Legs numOfLegs, Attributes.Wings hasWings,
                            Attributes.Fly canFly, Attributes.Tail hasTail, Attributes.Nature nature,
                            Attributes.Habitat habitat, Attributes.Active active) {
        super(name);
        this.attributes.add(numOfLegs);
        this.attributes.add(hasWings);
        this.attributes.add(canFly);
        this.attributes.add(hasTail);
        this.attributes.add(nature);
        this.attributes.add(habitat);
        this.attributes.add(active);
    }
    
    /**
     * Returns the number of matching attributes from a search result
     * @param attributes List of Enum attributes
     * @return The number of attribute matches indicating the desirability of the search result. Returns NO_MATCH if a
     * non UNKNOWN attribute does not match.
     * @throws AttributeCountException Thrown when the number of provided attributes is not expected
     */
    @Override
    public int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException {
        // Check if the supplied number of attributes matches this searchable type
        if (attributes.size() != this.attributes.size()) {
            throw new AttributeCountException(ATTRIBUTE_COUNT_EXCEPTION_MESSAGE);
        }
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
    
    @Override
    public int getAttributeCount() {
        return this.attributes.size();
    }
    
}
