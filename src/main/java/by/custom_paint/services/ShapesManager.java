package by.custom_paint.services;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.*;

public class ShapesManager {
    private static ShapesManager instance;

    private final ShapesList shapes;

    private ShapesManager() {
        shapes = new ShapesList();
    }

    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }

        return instance;
    }

    public ShapesList getShapes() {
        return this.shapes;
    }

    public void addShape(Shape shape) {

    }

    public void removeShape(Shape shape) {

    }

    public void updateShape(Shape shape) {

    }

    public void addShapes(ShapesList shapes) {

    }

    public void removeShapes(ShapesList shapes) {

    }
}
