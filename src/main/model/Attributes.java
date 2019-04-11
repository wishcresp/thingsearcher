package main.model;

public class Attributes {
    
    
    public enum Wings {
        UNKNOWN,
        YES,
        NO;
    
        @Override
        public String toString() {
            return "Does it have wings?";
        }
    }
    
    public enum Fly {
        UNKNOWN,
        YES,
        NO;
    
        @Override
        public String toString() {
            return "Can it fly?";
        }
    }
    
    public enum Tail {
        UNKNOWN,
        YES,
        NO;
    
        @Override
        public String toString() {
            return "Does it have a tail?";
        }
    }
    
    public enum Legs {
        UNKNOWN,
        NONE,
        TWO,
        FOUR,
        SIX,
        EIGHT;
    
        @Override
        public String toString() {
            return "Is it domesticated or wild?";
        }
    }
    
    public enum Active {
        UNKNOWN,
        DIURNAL,
        NOCTURNAL,
        CATHEMERAL;
    
        @Override
        public String toString() {
            return "When is it active?";
        }
    }
    
    public enum Habitat {
        UNKNOWN,
        TERRESTRIAL,
        AQUATIC;
    
        @Override
        public String toString() {
            return "Is it terrestrial or aquatic?";
        }
    }
    public enum Nature {
        UNKNOWN,
        WILD,
        DOMESTICATED,
        BOTH;
    
        @Override
        public String toString() {
            return "Is it domesticated or wild?";
        }
    }
    
}
