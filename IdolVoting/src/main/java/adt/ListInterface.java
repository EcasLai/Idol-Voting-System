package adt;

/**
 *
 * @author Lai Chee Sheng
 */
public interface ListInterface<T> {
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public T remove(int givenPosition);
    public void clear();
    public boolean replace(int givenPosition, T newEntry);
    public T get(int index);
    public T set(int index, T element);
    public T getEntry(int givenPosition);
    public boolean contains(T anEntry);
    public int size();
    public boolean isEmpty();
}
