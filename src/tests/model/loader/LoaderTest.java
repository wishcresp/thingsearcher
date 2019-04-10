package tests.model.loader;

import main.model.Loader.Loader;
import main.model.Searchable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoaderTest {
    
    private final static String ANIMAL_DATA_FILE_NAME = "res/animals.txt";
    private static String [] animals = {"BIRD","CAT","DOG","FISH","SNAKE", "SPIDER",
            "FROG","INSECT","HUMAN","CHICKEN", "COW", "PENGUIN", "OWL"};
    
    @Test
    @DisplayName("Load file validation")
    void loadAnimalDataTest() {
        List<Searchable> searchableList = Loader.loadAnimals(ANIMAL_DATA_FILE_NAME);
        for (int i = 0; i < animals.length; i++) {
            assertEquals(searchableList.get(i).getName(), animals[i]);
        }
    }
    
}
