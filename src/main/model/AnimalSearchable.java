package main.model;

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
    
}
