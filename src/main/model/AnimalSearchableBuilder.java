package main.model;

public class AnimalSearchableBuilder {
    
    private Attributes.Legs numOfLegs;
    private Attributes.Wings hasWings;
    private Attributes.Fly canFly;
    private Attributes.Tail hasTail;
    private Attributes.Nature nature;
    private Attributes.Habitat habitat;
    private Attributes.Active active;
    
    public AnimalSearchableBuilder() {
        this.numOfLegs = Attributes.Legs.UNKNOWN;
        this.hasWings = Attributes.Wings.UNKNOWN;
        this.canFly = Attributes.Fly.UNKNOWN;
        this.hasTail = Attributes.Tail.UNKNOWN;
        this.nature = Attributes.Nature.UNKNOWN;
        this.habitat = Attributes.Habitat.UNKNOWN;
        this.active = Attributes.Active.UNKNOWN;
    }
    
    public AnimalSearchable build() {
        return new AnimalSearchable(this.numOfLegs, this.hasWings, this.canFly, this.hasTail, this.nature,
                this.habitat, this.active);
    }
    
    public AnimalSearchableBuilder numOfLegs(Attributes.Legs numOfLegs) {
        this.numOfLegs = numOfLegs;
        return this;
    }

    public AnimalSearchableBuilder hasWings(Attributes.Wings hasWings) {
        this.hasWings = hasWings;
        return this;
    }
    
    public AnimalSearchableBuilder canFly(Attributes.Fly canFly) {
        this.canFly = canFly;
        return this;
    }
    
    public AnimalSearchableBuilder hasTail(Attributes.Tail hasTail) {
        this.hasTail = hasTail;
        return this;
    }
    
    public AnimalSearchableBuilder nature(Attributes.Nature nature) {
        this.nature = nature;
        return this;
    }
    
    public AnimalSearchableBuilder habitat(Attributes.Habitat habitat) {
        this.habitat = habitat;
        return this;
    }
    
    public AnimalSearchableBuilder active(Attributes.Active active) {
        this.active = active;
        return this;
    }
    
}
