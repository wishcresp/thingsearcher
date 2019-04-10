package tests.model;

import main.model.AnimalSearchable;
import main.model.Exceptions.AttributeCountException;
import main.model.Attributes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;

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
        searchable = new AnimalSearchable(Attributes.Legs.FOUR, Attributes.Feature.NO, Attributes.Feature.NO, Attributes.Feature.YES,
                Attributes.Nature.DOMESTICATED, Attributes.Habitat.TERRESTRIAL, Attributes.Active.DIURNAL);
        
    }
    
    private static void initializeAttributes() {
        attributes.clear();
        attributes.add(Attributes.Legs.FOUR);
        attributes.add(Attributes.Feature.NO);
        attributes.add(Attributes.Feature.NO);
        attributes.add(Attributes.Feature.YES);
        attributes.add(Attributes.Nature.DOMESTICATED);
        attributes.add(Attributes.Habitat.TERRESTRIAL);
        attributes.add(Attributes.Active.DIURNAL);
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
    @DisplayName("Throws EmptySearchablesException")
    void getNumberOfMatchesTestException() {
        initializeAttributes();
        attributes.remove(0);
        assertThrows(AttributeCountException.class, () -> searchable.getNumberOfMatches(attributes));
    }
    
    @Test
    @DisplayName("Fewer matches, expected match")
    void getNumberOfMatchesLessMatchesTest() {
        initializeAttributes();
        attributes.set(0, Attributes.Legs.UNKNOWN);
        attributes.set(1, Attributes.Feature.UNKNOWN);
        attributes.set(2, Attributes.Feature.UNKNOWN);
        try {
            assertEquals(searchable.getNumberOfMatches(attributes), 4);
        } catch (AttributeCountException e) {
            fail("Attribute count should be correct");
        }
    }
    
    @Test
    @DisplayName("Wrong value set, expected no match")
    void getNumberOfMatchesNoMatchTest() {
        initializeAttributes();
        attributes.set(4, Attributes.Nature.WILD);
        try {
            assertEquals(searchable.getNumberOfMatches(attributes), -1);
        } catch (AttributeCountException e) {
            fail("Attribute count should be correct");
        }
    }
    
    @Test
    @DisplayName("Attribute count check")
    void attributeCountTest() {
        assertEquals(searchable.getAttributes().size(), attributes.size());
    }
    
}
