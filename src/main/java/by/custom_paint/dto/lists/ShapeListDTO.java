package by.custom_paint.dto.lists;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.models.lists.ShapeList;

import by.custom_paint.dto.shapes.*;

import by.custom_paint.services.factories.ShapeDTOFactory;

import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

public class ShapeListDTO implements Serializable {
    private final ArrayList<ShapeDTO> shapes;

    @Serial
    private static final long serialVersionUID = 1L;

    public ShapeListDTO(ShapeList shapes) {
        this.shapes = new ArrayList<>();

        shapes.resetIterator();
        while (shapes.hasNext()) {
            Shape shape = shapes.next();

            this.shapes.add(ShapeDTOFactory.getInstance().createShapeDTO(shape));
        }
    }

    public ShapeList toShapeList() {
        ShapeList shapes = new ShapeList();

        for (ShapeDTO shapeDTO : this.shapes) {
            Shape shape = shapeDTO.toShape();

            if (shape != null) {
                shapes.add(shape);
            }
        }

        return shapes;
    }
}
