package main.model;

import main.model.Loader.Loader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    
    private Loader loader;
    private final String SAVE_ATTR_FILENAME = "res/attributes.bin";
    private final String SAVE_DATA_FILENAME = "res/searchables.bin";
    private final String UNKNOWN = "UNKNOWN";
    private final String LOAD_ERROR_MESSAGE = "Incorrectly formatted file. Some data was not loaded";
    private boolean LOAD_ERROR_FLAG = false;
    private Map<String, Attribute> loadedAttributes;
    private List<Searchable> loadedSearchables;
    
    
    public Model() {
        loader = new Loader(this);
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
     */
    public Searchable search(List<SearchValue> searchValues) {
        Searchable searchResult = null;
        int maxAttributeMatches = 0;
        // Compare query with each searchable
        for (Searchable searchable : this.loadedSearchables) {
            int attributeMatchCount = searchable.getNumberOfMatches(searchValues);
            // Check if more of a match than previous matches
            if (attributeMatchCount > maxAttributeMatches) {
                searchResult = searchable;
                maxAttributeMatches = attributeMatchCount;
            }
        }
        // Return a searchable if found
        return searchResult;
    }
    
    public String getDefaultValue() {
        return this.UNKNOWN;
    }
    
    public boolean setErrorFlag(boolean flag) {
        return this.LOAD_ERROR_FLAG = flag;
    }
    
    public boolean getErrorFlag() {
        return this.LOAD_ERROR_FLAG;
    }
    
    public String getLoadErrorMessage() {
        return this.LOAD_ERROR_MESSAGE;
    }
}
