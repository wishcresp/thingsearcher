package tests.model.loader;

import main.model.Loader.Loader;
import main.model.Searchable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTestSetup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoaderTest extends AbstractTestSetup {
    
    private static Loader loader;
    
    @BeforeAll
    static void setup() {
        setupModelNoData();
        loader = new Loader(model);
    }
    
    private static String [] attributes = {"WINGS", "FLY", "TAIL", "LEGS", "ACTIVE", "HABITAT", "NATURE"};
    private static String [] searchables = {"BIRD","CAT","DOG","FISH","SNAKE", "SPIDER",
            "FROG","INSECT","HUMAN","CHICKEN", "COW", "PENGUIN", "OWL"};
    
    @Test
    @DisplayName("Load attribute from .txt")
    void loadAttributeTextTest() {
        loader.loadAttributeFile(LOAD_ATTRIBUTE_TEXT_PATH);
        // Maintain ordering and convert to ArrayList for comparison
        List<String> loadedAttributes = new ArrayList<>(model.getLoadedAttributes().keySet());
        int length = loadedAttributes.size();
        for (int i = 0; i < length; i++) {
            assertEquals(attributes[i], loadedAttributes.get(i));
        }
    }
    
    @Test
    @DisplayName("Load searchables from .txt")
    void loadSearchableTextTest() {
        loader.loadAttributeFile(LOAD_ATTRIBUTE_TEXT_PATH);
        loader.loadSearchableFile(model.getLoadedAttributes(), LOAD_SEARCHABLE_TEXT_PATH);
        // Maintain ordering and convert to ArrayList for comparison
        List<Searchable> loadedSearchables = model.getLoadedSearchables();
        int length = model.getLoadedSearchables().size();
        for (int i = 0; i < length; i++) {
            assertEquals(searchables[i], loadedSearchables.get(i).getName());
        }
    }
    
    @Test
    @DisplayName("Test saving bin files")
    void saveLoadBinTest() {
        String save = "testvalue";
        loader.saveFile(RES_PATH, SAVE_FILE_BIN_PATH, save);
        File file = new File(SAVE_FILE_BIN_PATH);
        assertTrue(file.exists());
        assertTrue(file.delete());
    }
    
}
