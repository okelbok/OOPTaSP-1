package by.custom_paint.services.commands;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.models.lists.ShapeList;

public interface ShapeListCommands {
    Shape createShape(int shapeIndex);

    ShapeList getShapes();

    void addShape(Shape shape);

    void removeShape(Shape shape);

    void addShapes(ShapeList shapes);
}
