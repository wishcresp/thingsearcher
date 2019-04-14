package tests.model;

import main.model.Attribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTestSetup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttributeTest extends AbstractTestSetup {
    
    private static final String NAME = "Name";
    private static final String MESSAGE = "Message";
    private static final String VALUE1 = "VALUE1";
    private static final String VALUE2 = "VALUE2";
    private static Attribute attribute;
    
    @BeforeEach
    void setup() {
        setupModelNoData();
        attribute = new Attribute(NAME, MESSAGE, model.getDefaultAttributeValue());
    }
    
    @Test
    @DisplayName("Check default value")
    void defaultValueTest() {
        assertEquals(model.getDefaultAttributeValue(), attribute.getValues().get(0));
    }
    
    @Test
    @DisplayName("Add value test")
    void addValueTest() {
        attribute.addValue(VALUE1);
        attribute.addValue(VALUE2);
        assertEquals(model.getDefaultAttributeValue(), attribute.getValues().get(0));
        assertEquals(VALUE1, attribute.getValues().get(1));
        assertEquals(VALUE2, attribute.getValues().get(2));
    }
    
    @Test
    @DisplayName("Ignore already existing value test")
    void addValueAlreadyExistsTest() {
        attribute.addValue(VALUE1);
        attribute.addValue(VALUE1);
        attribute.addValue(model.getDefaultAttributeValue());
        assertEquals(2, attribute.getValues().size());
    }
    
    @Test
    @DisplayName("Get values")
    void getValuesTest() {
        assertEquals(1, attribute.getValues().size());
    }
    
    @Test
    @DisplayName("Get name")
    void getNameTest() {
        assertEquals(NAME, attribute.getName());
    }
    
    @Test
    @DisplayName("Get message test")
    void getMessageTest() {
        assertEquals(MESSAGE, attribute.getMessage());
    }
    
}
