package by.custom_paint.models.shapes;

import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    private Point2D startPoint, endPoint;
    private Color fillColor, borderColor;
    private double borderWidth;

    public Point2D setStartPoint(double x, double y) {
        startPoint = new Point2D(x, y);

        return startPoint;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D setEndPoint(double x, double y) {
        endPoint = new Point2D(x, y);

        return endPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
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

    protected void swapPoints() {
        Point2D startPoint = getStartPoint();
        Point2D endPoint = getEndPoint();

        if (startPoint.getX() > endPoint.getX()) {
            setStartPoint(
                    endPoint.getX(),
                    startPoint.getY()
            );

            setEndPoint(
                    startPoint.getX(),
                    endPoint.getY()
            );
        }

        if (startPoint.getY() > endPoint.getY()) {
            setStartPoint(
                    startPoint.getX(),
                    endPoint.getY()
            );

            setEndPoint(
                    endPoint.getX(),
                    startPoint.getY()
            );
        }
    }

    public void draw(GraphicsContext drawingArea) {
        drawingArea.setStroke(getBorderColor());
        drawingArea.setLineWidth(getBorderWidth());
        drawingArea.setFill(getFillColor());
    }
}
