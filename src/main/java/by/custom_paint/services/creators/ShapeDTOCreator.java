package by.custom_paint.services.creators;

import by.custom_paint.dto.shapes.ShapeDTO;

import java.lang.reflect.InvocationTargetException;

public interface ShapeDTOCreator<Shape> {
    ShapeDTO createShapeDTO(Shape shape);
}