package by.custom_paint.managers;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.models.lists.ShapeList;

import by.custom_paint.services.factories.ShapeFactory;

import by.custom_paint.services.commands.ShapeListCommands;

import by.custom_paint.services.observers.ShapeListObserver;

import java.util.List;
import java.util.ArrayList;

public class ShapeManager implements ShapeListCommands {
    private static ShapeManager instance;

    private final ShapeList shapes;
    private final ShapeFactory shapeFactory;

    private final List<ShapeListObserver> shapeListObservers;

    private ShapeManager() {
        this.shapes = new ShapeList();
        this.shapeFactory = ShapeFactory.getInstance();
        this.shapeListObservers = new ArrayList<>();
    }

    public static ShapeManager getInstance() {
        if (instance == null) {
            instance = new ShapeManager();
        }

        return instance;
    }

    public void addObserver(ShapeListObserver observer) {
        this.shapeListObservers.add(observer);
    }

    @Override
    public Shape createShape(int shapeIndex) {
        return this.shapeFactory.createShape(shapeIndex);
    }

    @Override
    public void addShape(Shape shape) {
        this.shapes.add(shape);

        for (ShapeListObserver observer : this.shapeListObservers) {
            observer.onShapeAdded(shape);
        }
    }

    @Override
    public void removeShape(Shape shape) {
        this.shapes.remove(shape);

        for (ShapeListObserver observer : this.shapeListObservers) {
            observer.onShapeRemoved(shape);
        }
    }

    @Override
    public void addShapes(ShapeList shapes) {
        this.shapes.addAll(shapes);
    }

    @Override
    public ShapeList getShapes() {
        return this.shapes;
    }
}
