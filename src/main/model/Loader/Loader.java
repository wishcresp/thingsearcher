package main.model.Loader;

import main.model.Attribute;
import main.model.Model;
import main.model.Searchable;
import main.model.SearchableImpl;
import java.io.*;
import java.util.*;

/**
 * Load and save data to files
 */
public class Loader {
    
    private final Model model;
    
    public Loader(Model model) {
        this.model = model;
    }
    
    // Saves serialized objects to file
    public <T> void saveFile(String filename, T write) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(write);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked") // Unchecked cast, no way of checking if file contains Map<String, SearchValue>
    public Map<String, Attribute> loadAttributes(String filename) {
        Map<String, Attribute> attributes = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            attributes = (Map<String, Attribute>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return attributes;
    }
    
    @SuppressWarnings("unchecked") // Unchecked cast, no way of checking if file contains List<Searchable>
    public List<Searchable> loadSearchables(String filename) {
        List<Searchable> searchables = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            searchables = (List<Searchable>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
    /**
     * Load attribute model from file into a HashMap
     * @param filename Filename of attributes
     * @return Map of attribute types
     */
    public Map<String, Attribute> loadAttributeFile(String filename) {
        Map<String, Attribute> attributes = new HashMap<>();
        String line, tokens[];
    
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                
                // Skip line if there is not two tokens
                if (tokens.length != 2) {
                    model.setErrorFlag(true);
                    continue;
                }
                
                Attribute attribute = new Attribute(tokens[0], tokens[1], model.getDefaultAttributeValue());
                attributes.put(tokens[0], attribute);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attributes;
    }
    
    /**
     * Reads a file and returns a list of searchables
     * @param attributes Map of attributes in the system
     * @param filename File path of the searchable data to load
     * @return A list of searchables
     */
    public List<Searchable> loadSearchableFile(Map<String, Attribute> attributes, String filename) {
        List<Searchable> searchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                Map<String, String> attributeValues = new HashMap<>();
                tokens = line.split(",");
                // For each pair, add the value to the relevant attribute
                for (int i = 1 ; i < tokens.length; i++) {
                    String[] attributePair = tokens[i].split(":");
                    Attribute attribute = attributes.get(attributePair[0]);
                    
                    // Skip the current attribute if attribute name cannot be found
                    // or the length of the current attributePair is not two
                    if (attribute == null || attributePair.length != 2) {
                        model.setErrorFlag(true);
                        continue;
                    }
                    
                    attributes.get(attributePair[0]).addValue(attributePair[1]);
                    attributeValues.put(attributePair[0], attributePair[1]);
                }
                searchables.add(new SearchableImpl(tokens[0], attributeValues));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
}
