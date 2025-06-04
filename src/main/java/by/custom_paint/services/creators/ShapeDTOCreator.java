package by.custom_paint.services.creators;

import by.custom_paint.dto.shapes.ShapeDTO;

public interface ShapeDTOCreator<Shape> {
    ShapeDTO createShapeDTO(Shape shape);
}