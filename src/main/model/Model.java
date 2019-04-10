package main.model;

import main.model.Exceptions.AttributeCountException;
import main.model.Loader.Loader;

import java.util.ArrayList;
import java.util.List;

public class Model {
    
    private final String ANIMAL_DATA_FILE_NAME = "res/animals.txt";
    private final String MATCH_NOT_FOUND = "No matches were found.";
    private List<Searchable> searchables;
    
    public Model() {
        searchables = new ArrayList<>();
        loadSearchables();
    }
    
    private void loadSearchables() {
        searchables.addAll(Loader.loadAnimals(ANIMAL_DATA_FILE_NAME));
        // TODO: Load additional types of searchables in the future
    }
    
    public List<Searchable> getSearchables() {
        return this.searchables;
    }
    
    public void clearSearchables() {
        this.searchables.clear();
    }
    
    /**
     * Returns the name of the closest matching searchable for a list of attributes search query
     * @param attributes List of enum attributes of a search query
     * @return The name of the closest matching searchable
     * @throws AttributeCountException The number of the supplied attributes was wrong for the searchable type
     */
    public String search(List<Enum> attributes) throws AttributeCountException {
        Searchable searchResult = null;
        int maxAttributeMatches = 0;
        // Compare query with each searchable
        for (Searchable searchable : this.searchables) {
            int attributeMatches = searchable.getNumberOfMatches(attributes);
            // Check if more of a match than previous matches (NO_MATCH = -1 so is never considered)
            if (attributeMatches > maxAttributeMatches) {
                searchResult = searchable;
                maxAttributeMatches = attributeMatches;
            }
        }
        // Return name of search result of error message if no result found
        return searchResult != null ? searchResult.getName() : MATCH_NOT_FOUND;
    }
}
