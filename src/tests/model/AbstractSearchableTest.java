package tests.model;

import main.model.AnimalSearchable;
import main.model.Types;
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
        searchable = new AnimalSearchable(name, Types.Legs.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN,
                Types.Domestication.UNKNOWN, Types.Habitat.UNKNOWN, Types.Active.UNKNOWN);
        initializeAttributes();
    }
    
    private static void initializeAttributes() {
        attributes.add(Types.Legs.UNKNOWN);
        attributes.add(Types.Feature.UNKNOWN);
        attributes.add(Types.Feature.UNKNOWN);
        attributes.add(Types.Feature.UNKNOWN);
        attributes.add(Types.Domestication.UNKNOWN);
        attributes.add(Types.Habitat.UNKNOWN);
        attributes.add(Types.Active.UNKNOWN);
        
    }
    
    @Test
    @DisplayName("Names should be equal")
    void getNameTest() {
        assertEquals(searchable.getName(), name);
    }
    
    @Test
    @DisplayName("UNKNOWN name is expected")
    void getUnknownNameTest() {
        AnimalSearchable unknownSearchable = new AnimalSearchable(Types.Legs.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN,
                Types.Domestication.UNKNOWN, Types.Habitat.UNKNOWN, Types.Active.UNKNOWN);
        assertEquals(unknownSearchable.getName(), unknownSearchable.UNKNOWN);
    }
    
    @Test
    @DisplayName("Attributes should be equal")
    void getAttributesTest() {
        assertEquals(searchable.getAttributes(), attributes);
    }
}
