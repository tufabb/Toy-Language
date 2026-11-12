package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private final HashMap<K, V> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void put(K key, V val) {
        dictionary.put(key, val);
    }

    @Override
    public void update(K key, V newVal) throws ADTException {
        if(!dictionary.containsKey(key))
            throw new ADTException("missing key");
        dictionary.replace(key, newVal);
    }

    @Override
    public V lookup(K key) throws ADTException {
        if (!isDefined(key))
            throw new ADTException("missing key");
        return dictionary.get(key);
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    @Override
    public void remove(K key) throws ADTException{
        if(!isDefined(key))
            throw new ADTException("missing key");
        dictionary.remove(key);
    }

    @Override
    public Map<K,V> toMap(){
        return new HashMap<K,V>(dictionary);
    }

    @Override
    public boolean isEmpty(){
        return dictionary.isEmpty();
    }

}
