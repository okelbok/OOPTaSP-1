package by.custom_paint.models.lists;

import by.custom_paint.models.shapes.base.Shape;

import java.util.ArrayList;

public class ShapeList implements List<Shape> {
    private final ArrayList<Shape> shapes;
    private Shape next = null;

    public ShapeList() {
        this.shapes = new ArrayList<>();
    }

    public void resetIterator() {
        this.next = (isEmpty()) ? null : this.shapes.getFirst();
    }

    @Override
    public void add(Shape item) {
        this.shapes.add(item);
        this.next = this.shapes.getFirst();
    }

    @Override
    public void remove(Shape item) {
        this.shapes.remove(item);

        this.resetIterator();
    }

    @Override
    public void addAll(List<Shape> items) {
        while (items.hasNext()) {
            this.shapes.add(items.next());
        }

        this.resetIterator();
    }

    @Override
    public boolean isEmpty() {
        return this.shapes.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public Shape next() {
        if (this.isEmpty()) {
            return null;
        }

        Shape result = this.next;

        if (this.next == null) {
            this.next = this.shapes.getFirst();
        }
        else if (this.next != this.shapes.getLast()) {
            this.next = this.shapes.get(this.shapes.indexOf(this.next) + 1);
        }
        else {
            this.next = null;
        }

        return result;
    }
}
