package entity;

import adt.HashMap;
import adt.ListInterface;
import adt.SortedListInterface;
import adt.MapInterface;
import adt.ArrayList;
import adt.LinkedList;
import adt.SortedList;
import java.util.Set;

/**
 *
 * @author Shia Chai Fen
 * @author Joshua Koh Min En
 * @author Lai Chee Sheng
 */

public class Poll implements Comparable <Poll> {
    // Data Attribute
    private String name;
    private PollStatus pollStatus;
    private MapInterface<Votee, SortedListInterface<Voter>> votedList; //Add new
    private boolean isOpen;
    
    // Constructor    
     public Poll(String name) {
         this(name, new PollStatus(), new HashMap<>(),false);
    }
     
    public Poll(String name, ListInterface<Votee> voteeList) {
        this(name,new PollStatus(),new HashMap<>(),false);
        
        for(int i=0; i<voteeList.size(); i++) {
            MapInterface<Votee, Integer> voteCount = new HashMap<>();
            
            // Initialize the voteCount map 
            voteCount.put(voteeList.get(i), 0);
            
            pollStatus.setVoteCount(voteCount);
        }
    }

    public Poll(String name, PollStatus pollStatus, MapInterface<Votee, SortedListInterface<Voter>> votedList, boolean isOpen) {
        this.name = name;
        this.pollStatus = pollStatus;
        this.votedList = votedList;
        this.isOpen = isOpen;
    }

    
    // Getter
    public String getName() {
        return name;
    }

    public PollStatus getPollStatus() {
        return pollStatus;
    }

    public boolean isOpen() {
        return isOpen;
    }
    
    public MapInterface<Votee, SortedListInterface<Voter>> getVotedList() {
        return votedList;
    }
    

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPollStatus(PollStatus pollStatus) {
        this.pollStatus = pollStatus;
    }

    public void open() {
        this.isOpen = true;
    }
    
    public void end() {
        this.isOpen = false;
    }

    public void setVotedList(MapInterface<Votee, SortedListInterface<Voter>> votedList) {
        this.votedList = votedList;
    }
    


    // Operation Methods
    public void addVote(Votee votee) {
        MapInterface<Votee, Integer> newVote = new HashMap<>();
        
        if (isOpen) { 
            for (MapInterface.Entry<Votee, Integer> entry : pollStatus.getVoteCount().entrySet()) {
                if(votee.equals(entry.getKey())) {
                    if(entry.getValue() != null) {
                        entry.setValue(entry.getValue()+1);
                    }        
                    else 
                        entry.setValue(1);
                } else {
                    pollStatus.getVoteCount().put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            throw new IllegalStateException("Poll is closed.");
        }
    }
   
    public void addVoter(Votee votee, Voter voter){
        if(!votedList.containsKey(votee)) {
            SortedListInterface<Voter> voterList = new SortedList<>();
            voterList.add(voter);
            
            votedList.put(votee, voterList);
        } else {
            votedList.get(votee).add(voter);
        }
    }
    
    public int getTotalVotes() {
        int totalVoteCount = 0;
        
        for (MapInterface.Entry<Votee, Integer> entry : pollStatus.getVoteCount().entrySet()) {
            totalVoteCount += entry.getValue();
        }
        
        return totalVoteCount;
    }
    

    // Votee CRUD
    public ListInterface<Votee> getVoteeList() {
        // Getter (LinkedList votee) for VoterMenu
        ListInterface<Votee> voteeList = new LinkedList<>();
        
        for (MapInterface.Entry<Votee, Integer> entry : pollStatus.getVoteCount().entrySet()) {
            voteeList.add(entry.getKey());
        }
        return voteeList;
    }
     
    public void addVotee(Votee votee) {
        pollStatus.getVoteCount().put(votee, 0);
    }
    
    public void updateVotee(Votee oldVotee, Votee newVotee) {
        MapInterface<Votee, Integer> updatedMap = new HashMap<>();
        
        oldVotee.setName(newVotee.getName());
        oldVotee.setDescription(newVotee.getDescription());
        
        
        for (MapInterface.Entry<Votee, Integer> entry : pollStatus.getVoteCount().entrySet()) {
            if (entry.getKey().equals(oldVotee)) {
                // Copy the old value and replace by new key
                updatedMap.put(oldVotee, entry.getValue());
            } else {
                updatedMap.put(entry.getKey(), entry.getValue());
            }
        }
        
        pollStatus.setVoteCount(updatedMap);
    }
    
    public Votee removeVotee(Votee votee) {
        pollStatus.getVoteCount().remove(votee);
        
        return votee;
    }
    
    public ListInterface<Votee> searchVotee(String voteeName) {
        if (voteeName == null || voteeName.isEmpty()) {
            return null;
        }
        
        ListInterface<Votee> matchedVoteeList = new ArrayList<>();

        for (MapInterface.Entry<Votee, Integer> pairEntry : pollStatus.getVoteCount().entrySet()) {
            if (pairEntry.getKey().getName().toUpperCase().contains(voteeName.toUpperCase())) {
                matchedVoteeList.add(pairEntry.getKey());
            }
        }

        return matchedVoteeList;
    }
    
 
    //Ranking Methods
    public ArrayList<Votee> defaultRanking() {
       MapInterface<Votee, Integer> voteCountMap = pollStatus.getVoteCount();
       ArrayList<Votee> voteeArrList = new ArrayList<>(voteCountMap.size());
       
        for (MapInterface.Entry<Votee, Integer> entry : voteCountMap.entrySet()) {
            voteeArrList.add(entry.getKey());
        }
        return voteeArrList;
    }

    public ArrayList<Votee> ascRanking(){
        MapInterface<Votee, Integer> voteCountMap = pollStatus.getVoteCount();
        ArrayList<Votee> voteeArrList = new ArrayList<>(voteCountMap.size());
        ArrayList<Integer> voteCountArrList = new ArrayList<>(voteCountMap.size());
       
        
        for (MapInterface.Entry<Votee, Integer> entry : voteCountMap.entrySet()) {
             voteeArrList.add(entry.getKey());
             voteCountArrList.add(entry.getValue());
        }

        //sorting algorithm
        //Split the number in 2 group : Sorted & Unsorted 
        for(int i = 1; i<voteCountArrList.size(); i++){     // initialize the i = 1 
            Votee tempVotee = voteeArrList.get(i);          //tempVotee is one of the member of unsorted (used to compare with Sorted                           
            int temp = voteCountArrList.get(i);             
            int j = i-1;                                    //j is the position
            
            while(j>=0 && voteCountArrList.get(j)> temp){       //if sorted > Unsorted, it will copy to the right
                 voteeArrList.set(j+1, voteeArrList.get(j));
                 voteCountArrList.set(j+1, voteCountArrList.get(j));
                 j--;                                           
            }
             voteeArrList.set(j+1, tempVotee);
             voteCountArrList.set(j+1, temp);
        }
        return voteeArrList;
    }  
      
    public ArrayList<Votee> descRanking(){
        MapInterface<Votee, Integer> voteCountMap = pollStatus.getVoteCount();
        ArrayList<Votee> voteeArrList = new ArrayList<>(voteCountMap.size());
        ArrayList<Integer> voteCountArrList = new ArrayList<>(voteCountMap.size());
        
        for (MapInterface.Entry<Votee, Integer> entry : voteCountMap.entrySet()) {
             voteeArrList.add(entry.getKey());
             voteCountArrList.add(entry.getValue());
        }

        
        for(int i = 1; i<voteCountArrList.size(); i++){
            Votee tempVotee = voteeArrList.get(i);          
            int temp = voteCountArrList.get(i);
            int j = i-1;            //j is the position
            
            while(j>=0 && voteCountArrList.get(j)< temp){       //if sorted < Unsorted, it will copy to the right
                 voteeArrList.set(j+1, voteeArrList.get(j));
                 voteCountArrList.set(j+1, voteCountArrList.get(j));
                 j--;
            }
             voteeArrList.set(j+1, tempVotee);
             voteCountArrList.set(j+1, temp);
        }
        return voteeArrList;
    }   

    
    
 
    
    // Comparable Methods
    @Override
    public int compareTo(Poll otherPoll) {
        // compare based on total number of votes
        int totalVotes = 0;
        for (int voteCount : pollStatus.getVoteCount().values()) {
            totalVotes += voteCount;
        }

        int otherTotalVotes = 0;
        for (int voteCount : otherPoll.pollStatus.getVoteCount().values()) {
            otherTotalVotes += voteCount;
        }

        return Integer.compare(otherTotalVotes, totalVotes);
    }
}