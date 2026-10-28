package interpreter.model.adt;

import interpreter.exceptions.ADTException;

public interface IList<T> {
    void add(T a);

    T get(int pos) throws ADTException;

    int size();
}
