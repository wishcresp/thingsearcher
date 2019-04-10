package main.model;

import main.model.Loader.Loader;

import java.util.ArrayList;
import java.util.List;

public class Model {
    
    private final String ANIMAL_DATA_FILE_NAME = "res/animals.txt";
    private List<Searchable> searchables;
    
    public Model() {
        searchables = new ArrayList<>();
        loadSearchables();
    }
    
    public void loadSearchables() {
        searchables.addAll(Loader.loadAnimals(ANIMAL_DATA_FILE_NAME));
        // TODO: Load additional types of searchables in the future
    }
    
    public List<Searchable> getSearchables() {
        return this.searchables;
    }
    
    public void clearSearchables() {
        searchables.clear();
    }
}
