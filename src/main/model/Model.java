package main.model;

import main.model.Exceptions.AttributeValueCountMismatchException;
import main.model.Exceptions.NullAttributeException;
import main.model.Loader.Loader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    
    private Loader loader;
    private final String SAVE_ATTR_FILENAME = "res/attributes.bin";
    private final String SAVE_DATA_FILENAME = "res/searchables.bin";
    private Map<String, Attribute> loadedAttributes;
    private List<Searchable> loadedSearchables;
    
    public Model() {
        loader = new Loader();
        this.loadedSearchables = loader.loadSearchables(SAVE_DATA_FILENAME);
        this.loadedAttributes = loader.loadAttributes(SAVE_ATTR_FILENAME);
        // Check if searchables and attributes were successfully loaded
        if (this.loadedAttributes == null) {
            this.loadedAttributes = new LinkedHashMap<>();
        }
        if (this.loadedSearchables == null) {
            this.loadedSearchables = new ArrayList<>();
        }
    }
    
    public void saveAttributes() {
        loader.saveFile(SAVE_ATTR_FILENAME, this.loadedAttributes);
    }
    
    public void saveSearchables() {
        loader.saveFile(SAVE_DATA_FILENAME, this.loadedSearchables);
    }
    
    public void loadAttributes(String path) {
        this.loadedAttributes = loader.loadAttributeFile(path);
    }
    
    public void loadSearchables(String path) {
        this.loadedSearchables.addAll(loader.loadSearchableFile(loadedAttributes, path));
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
    
    public void clearAttributes() {
        this.loadedAttributes.clear();
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
