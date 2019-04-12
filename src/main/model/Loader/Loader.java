package main.model.Loader;

import main.model.AnimalSearchable;
import main.model.Attributes;
import main.model.Searchable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Searchable> loadFile(String filename) {
        List<Searchable> searchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                
                // Define the accepted searchables labels (token[0]) to load here with their relevant searchable
                // class. It is assumed files are formatted correctly and attribute values are correct
                switch (tokens[0]) {
                    case "ANIMAL":
                        searchables.add(createAnimal(tokens));
                        break;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
    private Searchable createAnimal(String[] tokens) {
        return new AnimalSearchable(tokens[1], Attributes.Legs.valueOf(tokens[2]),
                Attributes.Wings.valueOf(tokens[3]), Attributes.Fly.valueOf(tokens[4]),
                Attributes.Tail.valueOf(tokens[5]), Attributes.Nature.valueOf(tokens[6]),
                Attributes.Habitat.valueOf(tokens[7]), Attributes.Active.valueOf(tokens[8]));
    }
    
}
