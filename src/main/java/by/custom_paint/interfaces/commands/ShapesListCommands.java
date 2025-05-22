package by.custom_paint.interfaces.commands;

import by.custom_paint.models.shapes.Shape;
import by.custom_paint.models.lists.ShapesList;

public interface ShapesListCommands {
    Shape createShape(int shapeIndex);

    ShapesList getShapes();

    void addShape(Shape shape);

    void removeShape(Shape shape);

    void addShapes(ShapesList shapes);
}
