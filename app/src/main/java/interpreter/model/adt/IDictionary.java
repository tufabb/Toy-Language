package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;

import java.util.Map;

//isDefined, lookup, put, update
public interface IDictionary<K, V> {
    boolean isDefined(K key);

    V lookup(K key) throws ADTException;

    void update(K key, V newVal) throws ADTException;

    void put(K key, V val);

    void remove(K key) throws ADTException;

    Map<K,V> toMap();


}
