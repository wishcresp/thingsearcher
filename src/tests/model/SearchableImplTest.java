package tests.model;

import main.model.Attribute;
import main.model.SearchValue;
import main.model.Searchable;
import main.model.SearchableImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTestSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchableImplTest extends AbstractTestSetup {
    
    // Searchable with name and attributeValues
    private static Searchable searchable;
    private static String name;
    private static Map<String, String> attributeValues;
    
    // Search query values from view
    private static List<SearchValue> searchValues;
    
    @BeforeAll
    static void setup() {
        setupModelWithData();
        searchValues = new ArrayList<>();
        constructExampleSearchable();
    }
    
    // Creates a searchable
    private static void constructExampleSearchable() {
        name = "HUMAN";
        attributeValues = new HashMap<>();
        attributeValues.put("LEGS", "TWO");
        attributeValues.put("WINGS", "NO");
        attributeValues.put("FLY", "NO");
        attributeValues.put("TAIL", "NO");
        attributeValues.put("NATURE", "DOMESTICATED");
        attributeValues.put("HABITAT", "TERRESTRIAL");
        attributeValues.put("ACTIVE", "DIURNAL");
        searchable = new SearchableImpl(name, attributeValues);
    }
    
    @Test
    @DisplayName("Get name")
    void getNameTest() {
        assertEquals(searchable.getName(), name);
    }
    
    @Test
    @DisplayName("Get attributeValues")
    void getAttributeValuesTest() {
        assertEquals(searchable.getAttributeValues(), attributeValues);
    }
    
    @Test
    @DisplayName("Single match")
    void getNumberOfMatchesSingleTest() {
        searchValues.clear();
        searchValues.add(new SearchValue("LEGS", "TWO"));
        assertEquals(1, searchable.getNumberOfMatches(searchValues));
    }
    
    @Test
    @DisplayName("Four matches")
    void getNumberOfMatchesMultipleTest () {
        // Construct search query for single matching attribute "LEGS"
        searchValues.clear();
        searchValues.add(new SearchValue("LEGS", "TWO"));
        searchValues.add(new SearchValue("ACTIVE", "DIURNAL"));
        searchValues.add(new SearchValue("HABITAT", "TERRESTRIAL"));
        searchValues.add(new SearchValue("NATURE","DOMESTICATED"));
        assertEquals(4, searchable.getNumberOfMatches(searchValues));
    }
    
    @Test
    @DisplayName("No matches: Empty Query")
    void getNumberOfMatchesNoMatchTest() {
        searchValues.clear();
        assertEquals(0, searchable.getNumberOfMatches(searchValues));
    }
    
    @Test
    @DisplayName("Un-matching value")
    void getNumberOfMatchesNonMatch() {
        searchValues.clear();
        searchValues.add(new SearchValue("HABITAT", "TERRESTRIAL")); // Matches
        searchValues.add(new SearchValue("ACTIVE", "NOCTURNAL")); // Does not match
    
        assertEquals(0, searchable.getNumberOfMatches(searchValues));
    }
    
}
