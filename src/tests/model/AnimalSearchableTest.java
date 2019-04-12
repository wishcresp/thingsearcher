package tests.model;

import main.model.AnimalSearchable;
import main.model.Attributes;
import main.model.Exceptions.NullAttributeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AnimalSearchable test")
class AnimalSearchableTest {
    
    private static AnimalSearchable searchable;
    private static List<Enum> attributes;
    
    @BeforeAll
    static void setup() {
        attributes = new ArrayList<>();
        initializeAttributes();
        searchable = new AnimalSearchable("Animal", Attributes.Legs.FOUR, Attributes.Wings.NO, Attributes.Fly.NO, Attributes.Tail.YES,
                Attributes.Nature.DOMESTICATED, Attributes.Habitat.TERRESTRIAL, Attributes.Active.DIURNAL);
        
    }
    
    private static void initializeAttributes() {
        attributes.clear();
        attributes.add(Attributes.Legs.FOUR);
        attributes.add(Attributes.Wings.NO);
        attributes.add(Attributes.Fly.NO);
        attributes.add(Attributes.Tail.YES);
        attributes.add(Attributes.Nature.DOMESTICATED);
        attributes.add(Attributes.Habitat.TERRESTRIAL);
        attributes.add(Attributes.Active.DIURNAL);
    }
    
    @Test
    @DisplayName("Correctly return the number of matches")
    void getNumberOfMatchesTest() {
        initializeAttributes();
        assertEquals(searchable.getNumberOfMatches(attributes), 7);
    }
    
    @Test
    @DisplayName("Throws EmptySearchablesException")
    void getNumberOfMatchesTestException() {
        initializeAttributes();
        attributes.remove(0);
        assertThrows(NullAttributeException.class, () -> searchable.getNumberOfMatches(attributes));
    }
    
    @Test
    @DisplayName("Fewer matches, expected match")
    void getNumberOfMatchesLessMatchesTest() {
        initializeAttributes();
        attributes.set(0, Attributes.Legs.UNKNOWN);
        attributes.set(1, Attributes.Wings.UNKNOWN);
        attributes.set(2, Attributes.Fly.UNKNOWN);
        assertEquals(searchable.getNumberOfMatches(attributes), 4);
    }
    
    @Test
    @DisplayName("Wrong value set, expected no match")
    void getNumberOfMatchesNoMatchTest() {
        initializeAttributes();
        attributes.set(4, Attributes.Nature.WILD);
        assertEquals(searchable.getNumberOfMatches(attributes), 0);
    }
    
    @Test
    @DisplayName("Attributes count check")
    void attributeCountTest() {
        assertEquals(searchable.getAttributes().size(), attributes.size());
    }
    
}
