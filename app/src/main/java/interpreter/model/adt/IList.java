package interpreter.model.adt;

public interface IList<T> {
    void add(T a);
    T get(int pos) throws ADTException;
    int size();
}
