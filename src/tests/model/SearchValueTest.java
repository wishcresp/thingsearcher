package tests.model;

import main.model.SearchValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchValueTest {
    
    private static SearchValue searchValue;
    private static final String NAME = "Name";
    private static final String VALUE = "Value";
    
    @BeforeAll
    static void setup() {
        searchValue = new SearchValue(NAME, VALUE);
    }
    
    @Test
    @DisplayName("Get attribute name")
    void getNameTest() {
        assertEquals(NAME, searchValue.getName());
    }
    
    @Test
    @DisplayName("Get attribute value")
    void getValueTest() {
        assertEquals(VALUE, searchValue.getValue());
    }
    
    @Test
    @DisplayName("To string should get value")
    void toStringTest() {
        assertEquals(VALUE, searchValue.toString());
    }
    
}
