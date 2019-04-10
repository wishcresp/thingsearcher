package tests.model;

import main.model.AnimalSearchable;
import main.model.Attributes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("AbstractSearchable test")
class AbstractSearchableTest {
    
    private static String name;
    private static AnimalSearchable searchable;
    private static List<Enum> attributes = new ArrayList<>();
    
    @BeforeAll
    static void setup() {
        name = "TestAnimal";
        initializeAttributes();
        searchable = new AnimalSearchable(name, Attributes.Legs.UNKNOWN, Attributes.Feature.UNKNOWN, Attributes.Feature.UNKNOWN, Attributes.Feature.UNKNOWN,
                Attributes.Nature.UNKNOWN, Attributes.Habitat.UNKNOWN, Attributes.Active.UNKNOWN);
    }
    
    private static void initializeAttributes() {
        attributes.add(Attributes.Legs.UNKNOWN);
        attributes.add(Attributes.Feature.UNKNOWN);
        attributes.add(Attributes.Feature.UNKNOWN);
        attributes.add(Attributes.Feature.UNKNOWN);
        attributes.add(Attributes.Nature.UNKNOWN);
        attributes.add(Attributes.Habitat.UNKNOWN);
        attributes.add(Attributes.Active.UNKNOWN);
    }
    
    @Test
    @DisplayName("Names should be equal")
    void getNameTest() {
        assertEquals(searchable.getName(), name);
    }
    
    @Test
    @DisplayName("UNKNOWN name is expected")
    void getUnknownNameTest() {
        AnimalSearchable unknownSearchable = new AnimalSearchable(Attributes.Legs.UNKNOWN, Attributes.Feature.UNKNOWN, Attributes.Feature.UNKNOWN, Attributes.Feature.UNKNOWN,
                Attributes.Nature.UNKNOWN, Attributes.Habitat.UNKNOWN, Attributes.Active.UNKNOWN);
        assertEquals(unknownSearchable.getName(), unknownSearchable.UNKNOWN);
    }
    
    @Test
    @DisplayName("Attributes should be equal")
    void getAttributesTest() {
        assertEquals(searchable.getAttributes(), attributes);
    }
}
