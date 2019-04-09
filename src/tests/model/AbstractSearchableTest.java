package tests.model;

import main.model.AnimalSearchable;
import main.model.Searchable;
import main.model.Types;
import org.junit.jupiter.api.Test;

public class AbstractSearchableTest {
    
    Searchable searchable = new AnimalSearchable(Types.Legs.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN, Types.Feature.UNKNOWN,
        Types.Domestication.UNKNOWN, Types.Habitat.UNKNOWN, Types.Active.UNKNOWN);
    
    @Test
    public void getAttributesTest() {
    
    }
}
