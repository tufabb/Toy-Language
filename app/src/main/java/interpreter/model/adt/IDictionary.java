package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;

//isDefined, lookup, put, update
public interface IDictionary<K, V> {
    boolean isDefined(K key);

    V lookup(K key) throws ADTException;

    void update(K key, V newVal);

    void put(K key, V val);


}
