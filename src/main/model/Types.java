package main.model;

public class Types {
    
    public enum Feature {
        UNKNOWN,
        YES,
        NO
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
