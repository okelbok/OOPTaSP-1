package by.custom_paint.models.plugins;

import by.custom_paint.dto.shapes.ShapeDTO;
import by.custom_paint.models.shapes.base.Shape;

public interface ShapePlugin {
    String getShapeName();

    Class<? extends Shape> getShapeClass();
    Class<? extends ShapeDTO> getShapeDTOClass();
}
