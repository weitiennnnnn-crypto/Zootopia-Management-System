package src.zoo;

public class Animal {
    private String name;
    private int ID;
    private String growth;
    private String lastFed;

    public Animal(String name, int ID, String growth)
    {
        this.name = name;
        this.ID = ID;
        this.growth = growth;
    }

    public Animal(String name, int ID, String growth, String lastFed)
    {
        this.name = name;
        this.ID = ID;
        this.growth = growth;
        this.lastFed = lastFed;
    }

    public void setName(String name) { this.name = name; }

    public void setID(int ID) { this.ID = ID; }

    public void setGrowth(String growth) { this.growth = growth; }

    public String getName() { return name; }

    public int getID() { return ID; }

    public String getGrowth() { return growth; }

    public String getLastFed() { return (lastFed == null || lastFed.isEmpty()) ? "Never" : lastFed; }

    public String toString() { return name; }

    public String toStringID() { return String.valueOf(ID); }
}
