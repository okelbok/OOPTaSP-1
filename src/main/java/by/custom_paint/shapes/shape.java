package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class shape {
    protected Point2D startPoint;
    protected Color fillColor, borderColor;
    protected double borderWidth;

    public Point2D setStartPoint(double x, double y) {
        startPoint = new Point2D(x, y);
        return startPoint;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Color setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this.fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this.borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
        return this.borderWidth;
    }

    public double getBorderWidth() {
        return borderWidth;
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(borderColor);
        gc.setLineWidth(borderWidth);
        gc.setFill(fillColor);
    };
}
