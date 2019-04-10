package tests.model;

import main.model.AnimalSearchable;
import main.model.AttributeCountException;
import main.model.Types;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("AnimalSearchable test")
class AnimalSearchableTest {
    
    private static AnimalSearchable searchable;
    private static List<Enum> attributes = new ArrayList<>();
    
    @BeforeAll
    static void setup() {
        initializeAttributes();
        searchable = new AnimalSearchable(Types.Legs.FOUR, Types.Feature.NO, Types.Feature.NO, Types.Feature.YES,
                Types.Nature.DOMESTICATED, Types.Habitat.TERRESTRIAL, Types.Active.DIURNAL);
        
    }
    
    private static void initializeAttributes() {
        attributes.clear();
        attributes.add(Types.Legs.FOUR);
        attributes.add(Types.Feature.NO);
        attributes.add(Types.Feature.NO);
        attributes.add(Types.Feature.YES);
        attributes.add(Types.Nature.DOMESTICATED);
        attributes.add(Types.Habitat.TERRESTRIAL);
        attributes.add(Types.Active.DIURNAL);
    }
    
    @Test
    @DisplayName("Correctly return the number of matches")
    void getNumberOfMatchesTest() {
        initializeAttributes();
        try {
            assertEquals(searchable.getNumberOfMatches(attributes), 7);
        } catch (AttributeCountException e) {
            fail("Attribute count was not correct");
        }
    }
    
    @Test
    @DisplayName("Throws AttributeCountException")
    void getNumberOfMatchesTestException() {
        initializeAttributes();
        attributes.remove(0);
        assertThrows(AttributeCountException.class, () -> searchable.getNumberOfMatches(attributes));
    }
    
    @Test
    @DisplayName("Attribute count check")
    void attributeCountTest() {
        assertEquals(searchable.getAttributes().size(), attributes.size());
    }
}
