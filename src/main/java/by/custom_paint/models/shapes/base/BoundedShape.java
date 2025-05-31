package by.custom_paint.models.shapes.base;

import javafx.geometry.Point2D;

public abstract class BoundedShape extends Shape {
    protected Point2D getNormalizedPoint(Point2D startPoint, Point2D endPoint, boolean isStartPoint) {
        if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
            double newStartX = Math.min(startPoint.getX(), endPoint.getX());
            double newStartY = Math.min(startPoint.getY(), endPoint.getY());
            double newEndX = Math.max(startPoint.getX(), endPoint.getX());
            double newEndY = Math.max(startPoint.getY(), endPoint.getY());

            startPoint = new Point2D(newStartX, newStartY);
            endPoint = new Point2D(newEndX, newEndY);
        }

        return (isStartPoint ? startPoint : endPoint);
    }

    public void normalizePoints() {
        Point2D startPoint = this.getStartPoint();
        Point2D endPoint = this.getEndPoint();
        Point2D normalizedStartPoint = this.getNormalizedPoint(startPoint, endPoint, true);
        Point2D normalizedEndPoint = this.getNormalizedPoint(endPoint, startPoint, false);

        this.setStartPoint(normalizedStartPoint.getX(), normalizedStartPoint.getY());
        this.setEndPoint(normalizedEndPoint.getX(), normalizedEndPoint.getY());
    }
}
