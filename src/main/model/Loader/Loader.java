package main.model.Loader;

import main.model.AnimalSearchable;
import main.model.Attribute;
import main.model.Attributes;
import main.model.Searchable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {
    
    @SuppressWarnings("unchecked")
    public List<Searchable> loadSearchables(String filename) {
        List<Searchable> searchables = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            searchables = (List<Searchable>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
    public void saveSearchables(String filename, List<Searchable> searchables) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(searchables);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load attribute model from file
     * @param filename Filename of attributes
     * @return Map of attribute types
     */
    public Map<String, Attribute> loadAttributesFiles(String filename) {
        Map<String, Attribute> attributes = new HashMap<>();
        String line, tokens[];
    
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                attributes.put(tokens[0], new Attribute(tokens[1]));
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
     * @param filename File name of the searchable data to load
     * @return A list of searchables
     */
    public List<Searchable> loadFile(Map<String, Attribute> attributes, String filename) {
        List<Searchable> searchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                // For each pair, add the value to the relevant attribute
                for (int i = 1 ; i < tokens.length; i++) {
                    String[] attributePair = tokens[i].split(":");
                    attributes.get(attributePair[0]).addValue(attributePair[1]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
}
