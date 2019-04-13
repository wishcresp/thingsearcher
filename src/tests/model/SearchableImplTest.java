package tests.model;

import main.model.Attribute;
import main.model.Exceptions.AttributeValueCountMismatchException;
import main.model.Searchable;
import main.model.SearchableImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTestSetup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SearchableImplTest extends AbstractTestSetup {
    
    // Searchable with name and attributeValues
    private static Searchable searchable;
    private static String name;
    private static Map<String, String> attributeValues;
    
    // Attributes stored in Model
    private static List<Attribute> attributeList;
    
    // Search query values from view
    private static List<String> searchValues;
    
    @BeforeAll
    static void setup() {
        setupModelWithData();
        attributeList = new ArrayList<>(model.getLoadedAttributes().values());
        searchValues = new ArrayList<>();
        constructExampleSearchable();
    }
    
    // Creates a searchable
    private static void constructExampleSearchable() {
        name = "HUMAN";
        attributeValues = new LinkedHashMap<>();
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
        // Construct search query for single matching attribute "LEGS"
        searchValues.clear();
        listAdder(searchValues, UNKNOWN, 3);
        searchValues.add("TWO");
        listAdder(searchValues, UNKNOWN, 3);
    
        checkNumberOfMatches(1);
    }
    
    @Test
    @DisplayName("Three matches")
    void getNumberOfMatchesMultipleTest () {
        // Construct search query for single matching attribute "LEGS"
        searchValues.clear();
        listAdder(searchValues, UNKNOWN, 3);
        searchValues.add("TWO");
        searchValues.add("DIURNAL");
        searchValues.add("TERRESTRIAL");
        searchValues.add("DOMESTICATED");
        
        checkNumberOfMatches(4);
    }
    
    @Test
    @DisplayName("No matches all unknown")
    void getNumberOfMatchesNoMatchTest() {
        searchValues.clear();
        listAdder(searchValues, UNKNOWN, 7);
        
        checkNumberOfMatches(0);
    }
    
    @Test
    @DisplayName("Matching value but un-matching non-unknown value.")
    void getNumberOfMatchesNoMatchNonUnknownTest() {
        searchValues.clear();
        listAdder(searchValues, UNKNOWN, 5);
        searchValues.add("TERRESTRIAL"); // Matches
        searchValues.add("DIURNAL"); // Does not match
        
        checkNumberOfMatches(0);
    }
    
    
    @Test
    @DisplayName("Throws AttributeValueCountMismatchException")
    void getNumberOfMatchesException () {
    
    }
    
    // Helper method for checking for the expected number of matches
    private void checkNumberOfMatches(int expectedMatches) {
        try {
            assertEquals(searchable.getNumberOfMatches(attributeList, searchValues), expectedMatches);
        } catch (AttributeValueCountMismatchException e) {
            fail("Should not throw exception here");
        }
    }
    
    // Helper method to add values to a list
    private <T> void listAdder(List<T> list, T value, int times) {
        for (int i = 0; i < times; i++) {
            list.add(value);
        }
    }
    
}
