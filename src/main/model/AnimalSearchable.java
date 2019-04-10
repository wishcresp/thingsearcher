package main.model;

import java.util.List;

public class AnimalSearchable extends AbstractSearchable {
    
    public AnimalSearchable(String name, Types.Legs numOfLegs, Types.Feature hasWings, Types.Feature canFly, Types.Feature hasTail,
                            Types.Nature nature, Types.Habitat habitat, Types.Active active) {
        super(name);
        this.attributes.add(numOfLegs);
        this.attributes.add(hasWings);
        this.attributes.add(canFly);
        this.attributes.add(hasTail);
        this.attributes.add(nature);
        this.attributes.add(habitat);
        this.attributes.add(active);
    }
    
    // Decided to not overload this constructor so UNKNOWN name is handled by the AbstractSearchable constructor instead
    public AnimalSearchable(Types.Legs numOfLegs, Types.Feature hasWings, Types.Feature canFly, Types.Feature hasTail,
                            Types.Nature nature, Types.Habitat habitat, Types.Active active) {
        // this(UNKNOWN, numOfLegs, hasWings, canFly, hasTail, nature, habitat, active);
        this.attributes.add(numOfLegs);
        this.attributes.add(hasWings);
        this.attributes.add(canFly);
        this.attributes.add(hasTail);
        this.attributes.add(nature);
        this.attributes.add(habitat);
        this.attributes.add(active);
    }
    
    public Enum getNumOfLegs() {
        return this.attributes.get(0);
    }
    
    public Enum getHasWings() {
        return this.attributes.get(1);
    }
    
    public Enum getCanFly() {
        return this.attributes.get(2);
    }
    
    public Enum getHasTail() {
        return this.attributes.get(3);
    }
    
    public Enum getNature() {
        return this.attributes.get(4);
    }
    
    public Enum getHabitat() {
        return this.attributes.get(5);
    }
    
    public Enum getActive() {
        return this.attributes.get(6);
    }
    
    /**
     * Returns the number of matching attributes from a search result
     * @param attributes List of Enum attributes
     * @return The number of attribute matches indicating the desirability of the search result
     * @throws AttributeCountException Thrown when the number of provided attributes is not expected
     */
    @Override
    public int getNumberOfMatches(List<Enum> attributes) throws AttributeCountException {
        if (attributes.size() != this.attributes.size()) {
            throw new AttributeCountException(ATTRIBUTE_COUNT_EXCEPTION_MESSAGE);
        }
        int numOfMatches = 0;
        int numOfAttributes = this.attributes.size();
        for (int i = 0; i < numOfAttributes; i++) {
            if (attributes.get(i).equals(this.attributes.get(i))) {
                numOfMatches++;
            }
        }
        return numOfMatches;
    }
    
    @Override
    public int getAttributeCount() {
        return this.attributes.size();
    }
    
}
