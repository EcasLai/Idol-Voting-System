package adt;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Objects;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joshua Koh Min En
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap<K,V> implements MapInterface<K,V>, Cloneable, Serializable {
    // Data Fields
    private Node<K,V>[] array;
    private int capacity;
    private int size;
    
    
    // Constructor
    public HashMap() {
        capacity = 16;
        size = 0;
        array = new Node[capacity];
    }
     
    public HashMap(int capacity) {
        this.capacity = capacity;
        size = 0;
        array = (Node<K,V>[]) new Node[capacity];
    }
    
    
    // Query Operations
     @Override
    public int size() {
        return size;
    } 
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean containsKey(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        
        while(head != null) {
            if(head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Node<K,V> node : array) {
            while(node != null) {
                if(node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
    
    @Override
    public V get(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        
        while(head != null) {
            if(head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Node<K,V> node = array[index];
        while (node != null) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.setValue(value);
                return oldValue;
            }
            node = node.next;
        }
        Node<K,V> newNode;
        newNode = new Node<>(key, value, array[index]);
        array[index] = newNode;
        size++;
        return null;
    }
    
    @Override
    public V remove(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        Node<K,V> prev = null;
        while(head != null) {
            if(head.key.equals(key)) {
                if(prev == null) {
                    array[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    
    // Bulk Operations
    @Override
    public void clear() {
        array = new Node[capacity];
        size = 0;
    }
    
    
    // Views
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Entry<K,V> entry : array) {
            if (entry != null) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    
    @Override
    public Collection<V> values() {
        Class<V> clazz = null;
        V[] valueArr = (V[]) Array.newInstance(clazz, capacity);
        
        for(int i=0; i<array.length; i++) {
            if(array[i] != null) {
                valueArr[i] = array[i].getValue();
            }
        }
        
        Collection<V> vs = Arrays.asList(valueArr);
        return vs;
    }
 
    @Override
    public Set<MapInterface.Entry<K,V>> entrySet() {
        Set<MapInterface.Entry<K,V>> entries = new HashSet<>();
         for (Node<K,V> node : array) {
             while (node != null) {
                 entries.add(node);
                 node = node.next;
             }
         }
         return entries;
    }
   
   
    // Methods
    private int hash(Object key) {
        return Math.abs(key.hashCode() % capacity);
    }
   
    private int getIndex(K key) {
        int hash = key.hashCode();
        hash = hash < 0 ? -hash : hash;
        return hash % capacity;
    }
    
    // Class Within Class
    static class Node<K,V> implements MapInterface.Entry<K,V> {
        final K key;
        V value;
        Node<K,V> next;

        Node( K key, V value, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey()        { return key; }
        @Override
        public final V getValue()      { return value; }
        @Override
        public final String toString() { return key + "=" + value; }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;

            return o instanceof MapInterface.Entry<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }
    }
}
