package by.custom_paint.dto.shapes;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.dto.graphics.*;

import by.custom_paint.services.factories.ShapeFactory;

import java.io.Serial;
import java.io.Serializable;

public class ShapeDTO implements Serializable {
    private final PointDTO startPoint;
    private final PointDTO endPoint;

    private final ColorDTO fillColor, borderColor;
    private final double borderWidth;

    protected final String shapeType;

    @Serial
    private final static long serialVersionUID = 1L;

    public ShapeDTO(Shape shape) {
        this.startPoint = new PointDTO(shape.getStartPoint());
        this.endPoint = new PointDTO(shape.getEndPoint());

        this.fillColor = new ColorDTO(shape.getFillColor());
        this.borderColor = new ColorDTO(shape.getBorderColor());
        this.borderWidth = shape.getBorderWidth();

        this.shapeType = shape.getClass().getSimpleName();
    }

    public Shape toShape() {
        if (!ShapeFactory.getInstance().containsShapeType(this.shapeType)) {
            return null;
        }

        Shape shape = ShapeFactory.getInstance().createShape(this.shapeType);

        shape.setStartPoint(this.startPoint.getX(), this.startPoint.getY());
        shape.setEndPoint(this.endPoint.getX(), this.endPoint.getY());

        shape.setFillColor(this.fillColor.getColor());
        shape.setBorderColor(this.borderColor.getColor());
        shape.setBorderWidth(this.borderWidth);

        return shape;
    }

    protected String getShapeType() {
        return this.shapeType;
    }
}
