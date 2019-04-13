package main.model.Loader;

import main.model.Attribute;
import main.model.Searchable;
import main.model.SearchableImpl;
import java.io.*;
import java.util.*;

public class Loader {
    
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
     * Load attribute model from file into a LinkedHashMap
     * @param filename Filename of attributes
     * @return Map of attribute types
     */
    public Map<String, Attribute> loadAttributeFile(String filename) {
        Map<String, Attribute> attributes = new LinkedHashMap<>();
        String line, tokens[];
    
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                Attribute attribute = new Attribute(tokens[0], tokens[1]);
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
