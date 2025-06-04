package by.custom_paint.services.plugins;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.dto.shapes.ShapeDTO;

public interface ShapePlugin {
    Class<? extends Shape> getShapeClass();
    Class<? extends ShapeDTO> getShapeDTOClass();

    String getShapeType();
}