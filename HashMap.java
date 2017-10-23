//Hash map from lecture on 2/10 with minor changes to hashing algorithm
import java.util.LinkedList;
import java.util.ArrayList;

class HashMapException extends Exception {

    public HashMapException(String message) {
        super(message);
    }
}

public class HashMap<K, V> {

    private HashNode<K, V>[] items;
    private int maxSize;

    public HashMap(int _size) {
        maxSize = _size;
        items = (HashNode<K, V>[])new HashNode[maxSize];
    }

    public void put(K key, V value) throws HashMapException {
        int index = hash(key);
        if(index<0) {
          index = -1*index;
        }
        boolean firstPass = true;
        while (items[index] != null) {
            index++;
            if (index == maxSize) {
                if (firstPass) {
                    index = 0;
                    firstPass = false;
                }
                else {
                    throw new HashMapException("Hashmap out of room");
                }
            }
        }
        items[index] = new HashNode<K, V>(key, value);

    }

    public V get(K key) throws HashMapException {
        int index = hash(key);
        boolean firstPass = true;
        if(index<0) {
          index = -1*index;
        }
        while (items[index] != null && !items[index].getKey().equals(key)) {
            index++;
            if (index == maxSize) {
                if (firstPass) {
                    index = 0;
                    firstPass = false;
                }
                else {
                    throw new HashMapException("Key not found");
                }
            }
        }

        return items[index].getValue();

    }

    public int hash(K key) {
        return key.hashCode() % maxSize;
    }
}
