package by.custom_paint.utils;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.*;

abstract class ShapesManager {
    private ShapesList shapesList;

    abstract public ShapesList getShapes();

    abstract public void addShape(Shape shape);

    abstract public void removeShape(Shape shape);

    abstract public void updateShape(Shape shape);

    abstract public void addShapes(ShapesList shapesList);

    abstract public void removeShapes(ShapesList shapesList);
}
