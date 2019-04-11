package main.model;

import main.model.Exceptions.AttributeCountException;
import main.model.Loader.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    
    private Loader loader;
    private final String SAVE_DATA_FILE_NAME = "res/searchables.bin";
    private final String IMPORT_ANIMAL_DATA_FILE_NAME = "res/animals.txt";
    private List<Searchable> searchables;
    
    public Model() {
        loader = new Loader();
        searchables = new ArrayList<>();
        searchables = loader.loadSearchables(SAVE_DATA_FILE_NAME);
        //loadFile();
    }
    
    public void saveSearchables() {
        loader.saveSearchables(SAVE_DATA_FILE_NAME, this.searchables);
    }
    
    // Deprecated. will only be loaded by View menu in the future.
    public void loadFile(File file) {
        searchables.addAll(loader.loadAnimals(file.getPath()));
    }
    
    public List<Searchable> getSearchables() {
        return this.searchables;
    }
    
    public void clearSearchables() {
        this.searchables.clear();
    }
    
    /**
     * Returns a matching searchable
     * @param attributes List of enum attributes of a search query
     * @return Searchable match or null if no match found
     * @throws AttributeCountException The number of the supplied attributes was wrong for the searchable type
     */
    public Searchable search(List<Enum> attributes) throws AttributeCountException {
        // Check for null values in search
        if (attributes.contains(null)) {
            throw new AttributeCountException("A provided attribute was null");
        }
        
        Searchable searchResult = null;
        int maxAttributeMatches = 0;
        // Compare query with each searchable
        for (Searchable searchable : this.searchables) {
            int attributeMatches = searchable.getNumberOfMatches(attributes);
            // Check if more of a match than previous matches (NO_MATCH = 0 so is never considered)
            if (attributeMatches > maxAttributeMatches) {
                searchResult = searchable;
                maxAttributeMatches = attributeMatches;
            }
        }
        // Return a searchable if found
        return searchResult;
    }
}
