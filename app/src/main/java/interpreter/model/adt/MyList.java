package interpreter.model.adt;

import interpreter.exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;

public class MyList <T> implements IList<T>{
    private final List<T> list= new ArrayList<>();

    @Override
    public void add(T a){
        list.add(a);
    }

    @Override
    public int size(){
        return list.size();
    }

    @Override
    public T get(int pos)throws ADTException {
        if(size()-1<pos)
            throw new ADTException("index out of bounds");
        return list.get(pos);
    }

    @Override
    public String toString(){
        return list.toString();
    }

}
