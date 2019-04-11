package tests.model;

import main.model.Attributes;
import main.model.Exceptions.NullAttributeException;
import main.model.Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Model Test")
class ModelTest {
    
    private static Model model;
    private static List<Enum> attributes;
    
    @BeforeAll
    static void setup() {
        attributes = new ArrayList<>();
        model = new Model();
    }
    
    private static void initializeSearchQuery() {
        attributes.add(Attributes.Legs.UNKNOWN);
        attributes.add(Attributes.Wings.UNKNOWN);
        attributes.add(Attributes.Fly.UNKNOWN);
        attributes.add(Attributes.Tail.YES);
        attributes.add(Attributes.Nature.WILD);
        attributes.add(Attributes.Habitat.AQUATIC);
        attributes.add(Attributes.Active.UNKNOWN);
    }
    
    @Test
    @DisplayName("Model searchables should not be empty")
    void dataLoadedTest() {
        assertTrue(!model.getSearchables().isEmpty());
    }
    
    @Test
    @DisplayName("Query result should be PENGUIN")
    void searchTest() {
        initializeSearchQuery();
        try {
            assertEquals(model.search(attributes), "PENGUIN");
        } catch (NullAttributeException e) {
            fail("Exception should not be thrown");
        }
    }
}
