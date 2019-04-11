package main.model;

import main.model.Exceptions.NullAttributeException;
import main.model.Loader.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    
    private Loader loader;
    private final String SAVE_DATA_FILE_NAME = "res/searchables.bin";
    private List<Searchable> searchables;
    
    public Model() {
        loader = new Loader();
        searchables = loader.loadSearchables(SAVE_DATA_FILE_NAME);
        // Check if searchables were successfully loaded
        if (searchables == null) {
            searchables = new ArrayList<>();
        }
    }
    
    public void saveSearchables() {
        loader.saveSearchables(SAVE_DATA_FILE_NAME, this.searchables);
    }
    
    public void loadFile(File file) {
        searchables.addAll(loader.loadFile(file.getPath()));
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
     * @throws NullAttributeException A supplied attribute was null
     */
    public Searchable search(List<Enum> attributes) throws NullAttributeException {
        // Check for null values in search
        if (attributes.contains(null)) {
            throw new NullAttributeException("Error: A provided attribute was null");
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
