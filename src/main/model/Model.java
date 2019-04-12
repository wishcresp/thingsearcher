package main.model;

import main.model.Exceptions.AttributeValueCountMismatchException;
import main.model.Exceptions.NullAttributeException;
import main.model.Loader.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {
    
    private Loader loader;
    private final String SAVE_DATA_FILENAME = "res/searchables.bin";
    private final String ATTRIBUTE_DEF_FILENAME = "res/attributes.txt";
    private Map<String, Attribute> loadedAttributes;
    private List<Searchable> loadedSearchables;
    
    public Model() {
        loader = new Loader();
        this.loadedAttributes = loader.loadAttributesFile(ATTRIBUTE_DEF_FILENAME);
        this.loadedSearchables = loader.loadSearchables(SAVE_DATA_FILENAME);
        // Check if loadedSearchables were successfully loaded
        if (this.loadedSearchables == null) {
            this.loadedSearchables = new ArrayList<>();
        }
    }
    
    public void saveSearchables() {
        loader.saveSearchables(SAVE_DATA_FILENAME, this.loadedSearchables);
    }
    
    public void loadFile(File file) {
        loadedSearchables.addAll(loader.loadFile(loadedAttributes, file.getPath()));
    }
    
    public List<Searchable> getLoadedSearchables() {
        return this.loadedSearchables;
    }
    
    public Map<String,Attribute> getLoadedAttributes() {
        return this.loadedAttributes;
    }
    
    public void clearSearchables() {
        this.loadedSearchables.clear();
    }
    
    /**
     * Returns a matching searchable
     * @param searchValues Map of attribute values of a search query
     * @return Searchable match or null if no match found
     * @throws NullAttributeException A supplied attribute was null
     */
    public Searchable search(List<String> searchValues) throws NullAttributeException, AttributeValueCountMismatchException {
        // Check for null values in search
        if (searchValues.contains(null)) {
            throw new NullAttributeException("Error: A provided attribute was null");
        }
        
        Searchable searchResult = null;
        int maxAttributeMatches = 0;
        // Compare query with each searchable
        for (Searchable searchable : this.loadedSearchables) {
            int attributeMatchCount = searchable.getNumberOfMatches(new ArrayList<>(this.loadedAttributes.values()), searchValues);
            // Check if more of a match than previous matches (NO_MATCH = 0 so is never considered)
            if (attributeMatchCount > maxAttributeMatches) {
                searchResult = searchable;
                maxAttributeMatches = attributeMatchCount;
            }
        }
        // Return a searchable if found
        return searchResult;
    }
}
