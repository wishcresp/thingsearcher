package main.model;

public class Attributes {
    
    public enum Feature {
        UNKNOWN,
        YES,
        NO,
        BOTH
    }
    
    public enum Legs {
        UNKNOWN,
        NONE,
        TWO,
        FOUR,
        SIX,
        EIGHT
    }
    
    public enum Active {
        UNKNOWN,
        DIURNAL,
        NOCTURNAL,
        CATHEMERAL
    }
    
    public enum Habitat {
        UNKNOWN,
        TERRESTRIAL,
        AQUATIC
    }
    
    public enum Nature {
        UNKNOWN,
        WILD,
        DOMESTICATED,
        BOTH
    }
    
}
