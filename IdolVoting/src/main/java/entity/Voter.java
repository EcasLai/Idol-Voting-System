package entity;


/**
 *
 * @author Lai Chee Sheng
 */
public class Voter extends User implements Comparable <Voter> {
    private String voterID;
    private String voterName;
    private String email;
    
    public Voter(String voterID, String voterName, String email, String username, String password){
        super(username, password);
        this.voterID = voterID;
        this.voterName = voterName;
        this.email = email;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voteeName) {
        this.voterName = voteeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Voter o) {
         int totalVotes = 0;
 

        return voterName.compareTo(o.voterName);
    }

}