package main.model.Loader;

import main.model.AnimalSearchable;
import main.model.Searchable;
import main.model.Attributes;

import java.io.*;
import java.util.*;

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
    
    public List<Searchable> loadAnimals(String filename) {
        List<Searchable> animalSearchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                animalSearchables.add(new AnimalSearchable(tokens[0], Attributes.Legs.valueOf(tokens[1]),
                        Attributes.Wings.valueOf(tokens[2]), Attributes.Fly.valueOf(tokens[3]), Attributes.Tail.valueOf(tokens[4]),
                        Attributes.Nature.valueOf(tokens[5]), Attributes.Habitat.valueOf(tokens[6]), Attributes.Active.valueOf(tokens[7])));
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return animalSearchables;
    }
    
}
