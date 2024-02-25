
package adt;

/**
 *
 * @author limky
 */
public interface ReportInterface <T extends Comparable<T>, Y> {
    public boolean addADT(T newEntryName, Y newEntryTotalVotes);
    public int allTotalVotes();
    public double percentageHighestOverAll();
    public String toStringTop3();
    
    public boolean sortList();
    public void clear();
    public int getNumberOfEntries();
    public boolean isEmpty();
}