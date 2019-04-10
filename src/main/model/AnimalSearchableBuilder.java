package main.model;

public class AnimalSearchableBuilder {
    
    private Types.Legs numOfLegs;
    private Types.Feature hasWings;
    private Types.Feature canFly;
    private Types.Feature hasTail;
    private Types.Nature nature;
    private Types.Habitat habitat;
    private Types.Active active;
    
    public AnimalSearchableBuilder() {
        this.numOfLegs = Types.Legs.UNKNOWN;
        this.hasWings = Types.Feature.UNKNOWN;
        this.canFly = Types.Feature.UNKNOWN;
        this.hasTail = Types.Feature.UNKNOWN;
        this.nature = Types.Nature.UNKNOWN;
        this.habitat = Types.Habitat.UNKNOWN;
        this.active = Types.Active.UNKNOWN;
    }
    
    public AnimalSearchable build() {
        return new AnimalSearchable(this.numOfLegs, this.hasWings, this.canFly, this.hasTail, this.nature,
                this.habitat, this.active);
    }
    
    public AnimalSearchableBuilder numOfLegs(Types.Legs numOfLegs) {
        this.numOfLegs = numOfLegs;
        return this;
    }

    public AnimalSearchableBuilder hasWings(Types.Feature hasWings) {
        this.hasWings = hasWings;
        return this;
    }
    
    public AnimalSearchableBuilder canFly(Types.Feature canFly) {
        this.canFly = canFly;
        return this;
    }
    
    public AnimalSearchableBuilder hasTail(Types.Feature hasTail) {
        this.hasTail = hasTail;
        return this;
    }
    
    public AnimalSearchableBuilder nature(Types.Nature nature) {
        this.nature = nature;
        return this;
    }
    
    public AnimalSearchableBuilder habitat(Types.Habitat habitat) {
        this.habitat = habitat;
        return this;
    }
    
    public AnimalSearchableBuilder active(Types.Active active) {
        this.active = active;
        return this;
    }
    
}
