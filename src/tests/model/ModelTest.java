package tests.model;

import main.model.Exceptions.AttributeValueCountMismatchException;
import main.model.Exceptions.NullAttributeException;
import main.model.SearchValue;
import main.model.Searchable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTestSetup;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Model Test")
class ModelTest extends AbstractTestSetup {

    private static List<SearchValue> owlSearch;

    @BeforeAll
    static void setup() {
        owlSearch = new ArrayList<>();
    }
    
    @BeforeEach
    void setupEach() {
        setupModelWithData();
    }
    
    
    // Create an OWL search query
    private static void initializeOwlSearchQuery() {
        owlSearch.clear();
        owlSearch.add(new SearchValue("WINGS","YES"));
        owlSearch.add(new SearchValue("FLY", "YES"));
        owlSearch.add(new SearchValue("TAIL", "YES"));
        owlSearch.add(new SearchValue("LEGS", "TWO"));
        owlSearch.add(new SearchValue("ACTIVE", "NOCTURNAL"));
        owlSearch.add(new SearchValue("HABITAT", "TERRESTRIAL"));
        owlSearch.add(new SearchValue("NATURE","WILD"));
    }

    @Test
    @DisplayName("Empty and populated tests for attributes and searchables")
    void dataLoadedTest() {
        assertFalse(model.getLoadedAttributes().isEmpty());
        assertFalse(model.getLoadedSearchables().isEmpty());
    }
    
    @Test
    @DisplayName("Clear data test")
    void clearDataTest() {
        model.clearAttributes();
        model.clearSearchables();
        assertTrue(model.getLoadedAttributes().isEmpty());
        assertTrue(model.getLoadedSearchables().isEmpty());
    }
    
    @Test
    @DisplayName("Query result should be OWL")
    void searchTest() {
        initializeOwlSearchQuery();
        Searchable searchable = model.search(owlSearch);
        assertEquals(searchable.getName(), "OWL");
    }
    
}
