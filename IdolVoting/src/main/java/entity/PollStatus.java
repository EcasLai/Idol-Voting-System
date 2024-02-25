package entity;

import adt.HashMap;
import adt.MapInterface;

/**
 *
 * @author Joshua Koh Min En
 */
public class PollStatus {
    private MapInterface<Votee, Integer> voteCount;
    private long timeRemaining;
    
    public PollStatus() {
        this.voteCount = new HashMap<>();
        this.timeRemaining = 10000;
    }
    
    public MapInterface<Votee, Integer> getVoteCount() {
        return voteCount;
    }
    
    public void setVoteCount(MapInterface<Votee, Integer> voteCount) {
        this.voteCount = voteCount;
    }
    
    public long getTimeRemaining() {
        return timeRemaining;
    }
    
    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}
