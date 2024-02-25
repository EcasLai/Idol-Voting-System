package adt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Joshua Koh Min En
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MapInterface<K,V> {
    // Query Operationsv
    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    
    // Bulk Operations
    void clear();
    
    // Views
    Set<K> keySet();
    Collection<V> values();
    Set<Entry<K,V>> entrySet();
    
    
    public interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);     
        boolean equals(Object o);
        int hashCode();

        public static <K extends Comparable<? super K>, V> Comparator<MapInterface.Entry<K, V>> comparingByKey() {
            return (Comparator<MapInterface.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }
        public static <K, V extends Comparable<? super V>> Comparator<MapInterface.Entry<K, V>> comparingByValue() {
            return (Comparator<MapInterface.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }

        public static <K, V> Comparator<MapInterface.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<MapInterface.Entry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }

        public static <K, V> Comparator<MapInterface.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<MapInterface.Entry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }    
    }
}
