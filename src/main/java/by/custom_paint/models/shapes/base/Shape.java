package by.custom_paint.models.shapes.base;

import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    private Point2D startPoint, endPoint;

    private Color fillColor, borderColor;
    private double borderWidth;

    private boolean isPolyVertex = false;
    private boolean isMultiClick = false;

    public Point2D setStartPoint(double x, double y) {
        this.startPoint = new Point2D(x, y);

        return this.startPoint;
    }

    public Point2D getStartPoint() {
        return this.startPoint;
    }

    public Point2D setEndPoint(double x, double y) {
        this.endPoint = new Point2D(x, y);

        return this.endPoint;
    }

    public Point2D getEndPoint() {
        return this.endPoint;
    }

    public Color setFillColor(Color fillColor) {
        this.fillColor = fillColor;

        return this.fillColor;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Color setBorderColor(Color borderColor) {
        this.borderColor = borderColor;

        return this.borderColor;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public double setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;

        return this.borderWidth;
    }

    public double getBorderWidth() {
        return this.borderWidth;
    }

    protected boolean setPolyVertex(boolean isPolyVertex) {
        this.isPolyVertex = isPolyVertex;

        return this.isPolyVertex;
    }

    protected boolean setMultiClick(boolean isMultiClick) {
        this.isMultiClick = isMultiClick;

        return this.isMultiClick;
    }

    public boolean isPolyVertex() {
        return this.isPolyVertex;
    }

    public boolean isMultiClick() {
        return this.isMultiClick;
    }

    public void draw(GraphicsContext drawingArea) {
        drawingArea.setStroke(this.borderColor);
        drawingArea.setLineWidth(this.borderWidth);
        drawingArea.setFill(this.fillColor);
    }
}
