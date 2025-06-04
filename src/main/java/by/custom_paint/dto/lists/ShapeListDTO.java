package by.custom_paint.dto.lists;

import by.custom_paint.models.shapes.base.Shape;
import by.custom_paint.models.shapes.base.PolyShape;

import by.custom_paint.models.lists.ShapeList;

import by.custom_paint.dto.shapes.*;

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

            if (!(shape instanceof PolyShape)) {
                this.shapes.add(new ShapeDTO(shape));
            }
            else {
                this.shapes.add(new PolyShapeDTO((PolyShape) shape));
            }
        }
    }

    public ShapeList toShapeList() {
        ShapeList shapes = new ShapeList();

        for (ShapeDTO shape : this.shapes) {
            shapes.add(shape.toShape());
        }

        return shapes;
    }
}
