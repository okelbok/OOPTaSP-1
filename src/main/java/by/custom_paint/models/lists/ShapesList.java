package by.custom_paint.models.lists;

import by.custom_paint.models.shapes.Shape;

import java.util.LinkedList;

public class ShapesList implements List<Shape> {
    private final LinkedList<Shape> shapes;
    private Shape next = null;

    public ShapesList() {
        shapes = new LinkedList<>();
    }

    @Override
    public void add(Shape item) {
        shapes.add(item);
    }

    @Override
    public void remove(Shape item) {
        shapes.remove(item);
    }

    @Override
    public void addAll(List<Shape> items) {
        while (items.hasNext()) {
            shapes.add(items.next());
        }
    }

    @Override
    public void removeAll(List<Shape> items) {
        while (items.hasNext()) {
            shapes.remove(items.next());
        }
    }

    @Override
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    @Override
    public boolean contains(Shape item) {
        return false;
    }

    @Override
    public boolean hasNext() {
        if (next == null) {
            next = shapes.getFirst();
            return true;
        }

        if (next != shapes.getLast()) {
            next = shapes.get(shapes.indexOf(next) + 1);
            return true;
        }
        next = null;

        return false;
    }

    @Override
    public Shape next() {
        return next;
    }
}
