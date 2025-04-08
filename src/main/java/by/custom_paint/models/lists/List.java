package by.custom_paint.models.lists;

public interface List<T> {
    void add(T item);

    void remove(T item);

    void addAll(List<T> items);

    void removeAll(List<T> items);

    boolean isEmpty();

    boolean contains(T item);
}
