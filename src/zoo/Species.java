package src.zoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Species {
    private String typeName;
    private String description;
    private String habitat;
    private String diet;

    public Species (String typeName) { this.typeName = typeName; }

    public Species(String typeName, String description, String habitat, String diet)
    {
        this.typeName = typeName;
        this.description = description;
        this.habitat = habitat;   
        this.diet = diet;
    }

    public void setTypeName(String typeName) { this.typeName = typeName; }

    public void setDescription(String description) { this.description = description; }

    public void setHabitat(String importArea) { this.habitat = importArea; }

    public void setDiet(String diet) { this.diet = diet; }

    public String getTypeName() { return typeName; }

    public String getDescription() { return description; }

    public String getHabitat() { return habitat; }

    public String getDiet() { return diet; }

    public String toString() { return typeName; }

    public static ArrayList<Species> loadSpeciesFromFile() {
        ArrayList<Species> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("species.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4) {
                    // Name, Description, Habitat, Diet
                    list.add(new Species(data[0], data[3], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No species found yet.");
        }
        return list;
    }
}
