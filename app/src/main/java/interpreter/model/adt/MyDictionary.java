package interpreter.model.adt;

import java.util.HashMap;

public class MyDictionary<K,V> implements IDictionary<K,V> {
    private final HashMap<K,V> dictionary=new HashMap<>();

    @Override
    public boolean isDefined(K key){
        return dictionary.containsKey(key);
    }

    @Override
    public void put(K key,V val){
        dictionary.put(key,val);
    }

    @Override
    public void update(K key, V newVal){
        dictionary.replace(key,newVal);
    }
    @Override
    public V lookup(K key) throws ADTException{
        if(!isDefined(key))
            throw new ADTException("missing key");
        return dictionary.get(key);
    }
    @Override
    public String toString(){
        return dictionary.toString();
    }

}
