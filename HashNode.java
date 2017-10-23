
import java.util.LinkedList;
import java.util.ArrayList;

class HashNode<K, V> {

    K key;
    V value;

    public HashNode(K _key, V _value) {
        key = _key;
        value = _value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
