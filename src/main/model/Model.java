package main.model;

import main.model.Loader.Loader;

import java.io.File;
import java.util.*;

public class Model {
    
    // Constants
    private static final String RES_PATH = "res";
    private static final String SAVE_ATTRIBUTE_FILE_PATH = RES_PATH + "/attributes.bin";
    private static final String SAVE_DATA_FILE_PATH = RES_PATH + "/searchables.bin";
    private static final String DEFAULT_ATTRIBUTE_VALUE = "UNKNOWN"; // Used by Loader and Controller
    
    // Separate file loader class
    private final Loader loader;
    
    // Map of Attributes loaded from attribute file. Key is the name of the Attribute
    private Map<String, Attribute> loadedAttributes;
    // List of searchables loaded from searchables file
    private List<Searchable> loadedSearchables;
    // Flag indicating some data wasn't loaded due to an incorrectly formatted file
    private boolean loadErrorFlag = false;
    
    public Model() {
        loader = new Loader(this);
        // Load existing binary data if it exists
        this.loadedSearchables = loader.loadSearchables(SAVE_DATA_FILE_PATH);
        this.loadedAttributes = loader.loadAttributes(SAVE_ATTRIBUTE_FILE_PATH);
        // Check if searchables and attributes were successfully loaded
        if (this.loadedAttributes == null) {
            this.loadedAttributes = new HashMap<>();
        }
        if (this.loadedSearchables == null) {
            this.loadedSearchables = new ArrayList<>();
        }
    }
    
    /**
     * Save binary attribute file
     */
    public void saveAttributes() {
        loader.saveFile(RES_PATH, SAVE_ATTRIBUTE_FILE_PATH, this.loadedAttributes);
    }
    
    /**
     * Save binary searchable file
      */
    public void saveSearchables() {
        loader.saveFile(RES_PATH, SAVE_DATA_FILE_PATH, this.loadedSearchables);
    }
    
    /**
     * Load attribute text file
     * @param path file path
     */
    public void loadAttributes(String path) {
        this.loadedAttributes = loader.loadAttributeFile(path);
    }
    
    /**
     * Load searchable text file
     * @param path file path
     */
    public void loadSearchables(String path) {
        this.loadedSearchables.addAll(loader.loadSearchableFile(loadedAttributes, path));
    }
    
    /**
     * Return all searchables in model
     * @return List of searchables
     */
    public List<Searchable> getLoadedSearchables() {
        return this.loadedSearchables;
    }
    
    /**
     * Return map of all attributes in model
     * @return Map of Attributes (key = attribute name)
     */
    public Map<String,Attribute> getLoadedAttributes() {
        return this.loadedAttributes;
    }
    
    /**
     * Clear all searchables from the model
     */
    public void clearSearchables() {
        this.loadedSearchables.clear();
    }
    
    /**
     * Clear all attributes from the model
     */
    public void clearAttributes() {
        this.loadedAttributes.clear();
    }
    
    /**
     * Return a searchable for a search query
     * @param searchValues Search query - list of attribute values
     * @return Searchable match or null if no match is found
     */
    public Searchable search(List<SearchValue> searchValues) {
        Searchable searchResult = null;
        int maxAttributeMatches = 0;
        // Compare query with each searchable
        for (Searchable searchable : this.loadedSearchables) {
            int attributeMatchCount = searchable.getNumberOfMatches(searchValues);
            // Check if more attribute values match than previous matches
            if (attributeMatchCount > maxAttributeMatches) {
                searchResult = searchable;
                maxAttributeMatches = attributeMatchCount;
            }
        }
        // Return a searchable if found
        return searchResult;
    }
    
    /**
     * Return the default
     * @return Default attribute value to display in the view
     */
    public String getDefaultAttributeValue() {
        return DEFAULT_ATTRIBUTE_VALUE;
    }
    
    /**
     * Sets a flag indicating if an error occurred during file loading causing some data to be ignored
     * @param flag Error flag
     */
    public void setErrorFlag(boolean flag) {
        this.loadErrorFlag = flag;
    }
    
    /**
     * Check the file load error flag
     * @return True if there was an error loading a file
     */
    public boolean getErrorFlag() {
        return this.loadErrorFlag;
    }
    
}
