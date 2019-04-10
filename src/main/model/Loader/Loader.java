package main.model.Loader;

import main.model.AnimalSearchable;
import main.model.Searchable;
import main.model.Attributes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    
    public static List<Searchable> loadAnimals(String filename) {
        List<Searchable> animalSearchables = new ArrayList<>();
        String line, tokens[];
        
        try {
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(",");
                animalSearchables.add(new AnimalSearchable(tokens[0], Attributes.Legs.valueOf(tokens[1]),
                        Attributes.Feature.valueOf(tokens[2]), Attributes.Feature.valueOf(tokens[3]), Attributes.Feature.valueOf(tokens[4]),
                        Attributes.Nature.valueOf(tokens[5]), Attributes.Habitat.valueOf(tokens[6]), Attributes.Active.valueOf(tokens[7])));
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return animalSearchables;
    }
    
}
