package tests.model.loader;

import main.model.Model;
import main.model.Searchable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoaderTest {
    
    private static String [] animals = {"BIRD","CAT","DOG","FISH","SNAKE", "SPIDER",
            "FROG","INSECT","HUMAN","CHICKEN", "COW", "PENGUIN", "OWL"};
    
    @Test
    @DisplayName("Load file validation")
    void loadAnimalDataTest() {
        Model model = new Model();
        model.loadData("res/animals.txt");
        List<Searchable> searchableList = new Model().getLoadedSearchables();
        for (int i = 0; i < animals.length; i++) {
            assertEquals(searchableList.get(i).getName(), animals[i]);
        }
    }
    
}
