package by.custom_paint.dto.shapes;

import by.custom_paint.models.shapes.base.Shape;
import by.custom_paint.models.shapes.base.PolyShape;

import by.custom_paint.dto.graphics.*;

import by.custom_paint.services.factories.ShapeFactory;

import java.util.ArrayList;

import java.io.Serial;

import javafx.geometry.Point2D;

public class PolyShapeDTO extends ShapeDTO {
    private final int verticesCount;
    private final ArrayList<PointDTO> vertices;

    @Serial
    private static final long serialVersionUID = 1L;

    public PolyShapeDTO(Shape shape) {
        super(shape);

        this.verticesCount = ((PolyShape) shape).getVerticesCount();
        this.vertices = new ArrayList<>();

        ArrayList<Point2D> vertices = ((PolyShape) shape).getVertices();

        for (Point2D vertex : vertices) {
            this.vertices.add(new PointDTO(vertex));
        }
    }

    @Override
    public Shape toShape() {
        if (!ShapeFactory.getInstance().containsShapeType(this.shapeType)) {
            return null;
        }

        PolyShape shape = (PolyShape) ShapeFactory.getInstance().createShape(this.getShapeType());

        shape.setVerticesCount(this.verticesCount);

        ArrayList<Point2D> vertices = new ArrayList<>();

        for (PointDTO vertex : this.vertices) {
            vertices.add(new Point2D(vertex.getX(), vertex.getY()));
        }

        shape.setVertices(vertices);

        return shape;
    }
}
