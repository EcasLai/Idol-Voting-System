package entity;

/**
 *
 * @author Joshua Koh Min En
 */
public class Votee implements Comparable <Votee> {
    private String id;
    private String name;
    private String description;
    private int totalVotes;     // Include all votes from different poll
    private static int previousCount = 0;
    
    public Votee(String name, String description) {
        this.id = "V" + String.format("%03d", ++previousCount);
        this.name = name;
        this.description = description;
    }


    // Getter
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    
    // Abstract Methods
    @Override
    public int compareTo(Votee other) {  
        return Integer.compare(this.totalVotes, other.totalVotes);
    }
}
