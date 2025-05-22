package by.custom_paint.models.lists;

import by.custom_paint.models.shapes.Shape;

import java.util.ArrayList;

public class ShapesList implements List<Shape> {
    private final ArrayList<Shape> shapes;
    private Shape next = null;

    public ShapesList() {
        shapes = new ArrayList<>();
    }

    public void resetIterator() {
        next = (isEmpty()) ? null : shapes.getFirst();
    }

    @Override
    public void add(Shape item) {
        shapes.add(item);
        next = shapes.getFirst();
    }

    @Override
    public void remove(Shape item) {
        shapes.remove(item);
        next = shapes.getFirst();
    }

    @Override
    public void addAll(List<Shape> items) {
        while (items.hasNext()) {
            shapes.add(items.next());
        }
        next = shapes.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Shape next() {
        if (isEmpty()) {
            return null;
        }

        Shape result = next;

        if (next == null) {
            next = shapes.getFirst();
        }
        else if (next != shapes.getLast()) {
            next = shapes.get(shapes.indexOf(next) + 1);
        }
        else {
            next = null;
        }

        return result;
    }
}
