package main.model.Loader;

import main.model.AnimalSearchable;
import main.model.AnimalSearchableBuilder;
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
    
    public List<Searchable> loadFile(String filename) {
        List<Searchable> searchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                switch (tokens[0]) {
                    case "ANIMAL":
                        searchables.add(buildAnimal(tokens));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchables;
    }
    
    private Searchable buildAnimal(String[] tokens) {
        AnimalSearchableBuilder builder = new AnimalSearchableBuilder();
        if (!tokens[2].equals(""))
            builder.numOfLegs(Attributes.Legs.valueOf(tokens[2]));
        if (!tokens[3].equals(""))
            builder.hasWings(Attributes.Wings.valueOf(tokens[3]));
        if (!tokens[4].equals(""))
            builder.canFly(Attributes.Fly.valueOf(tokens[4]));
        if (!tokens[5].equals(""))
            builder.hasTail(Attributes.Tail.valueOf(tokens[5]));
        if (!tokens[6].equals(""))
            builder.nature(Attributes.Nature.valueOf(tokens[6]));
        if (!tokens[7].equals(""))
            builder.habitat(Attributes.Habitat.valueOf(tokens[7]));
        if (!tokens[8].equals(""))
            builder.active(Attributes.Active.valueOf(tokens[8]));
        return builder.build(tokens[1]);
    }
    
}
