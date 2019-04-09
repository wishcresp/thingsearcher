package main.model;

class Attributes {
    
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
    
    public enum Domestication {
        UNKNOWN,
        WILD,
        DOMESTICATED,
        BOTH
    }
    
}
