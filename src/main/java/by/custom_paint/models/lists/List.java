package by.custom_paint.models.lists;

import java.util.Iterator;

public interface List<T> extends Iterator<T> {
    void add(T item);

    void remove(T item);

    void addAll(List<T> items);

    void removeAll(List<T> items);

    boolean isEmpty();

    boolean contains(T item);
}
