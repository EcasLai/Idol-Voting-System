
package adt;

import entity.Poll;
import java.util.Comparator;
import java.util.Iterator;

public interface SortedListInterface <T extends Comparable<T>>{
    
    public Iterator<T> getIterator();
    
    public boolean add(T newEntry); // DONE
    
    public boolean contains(T anEntry); // DONE
    
    public T get(Integer selectedPosition); // DONE
    
    public T get(T anEntry); // DONE
    
    public T getLast();

    public void sortAscending(SortedList listA, Comparator A); // DONE
    
    public void sortDescending(SortedList listA, Comparator A); // DONE
    
    public SortedListInterface filter(SortedList listA, String str); // DONE
    
    public boolean isEmpty(); // DONE
    
    public int size(); // DONE
    
    public void clear(); // DONE

    public Object get(SortedListInterface<Poll> Poll);

    
}
