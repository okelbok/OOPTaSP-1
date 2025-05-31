package by.custom_paint.models.shapes.derived;

import by.custom_paint.models.shapes.base.PolyShape;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolygonShape extends PolyShape {
    public PolygonShape() {
        super();

        this.setPolyVertex(true);
    }

    private Point2D getCenterPoint(Point2D startPoint, Point2D endPoint) {
        double centerX = (startPoint.getX() + endPoint.getX()) / 2;
        double centerY = (startPoint.getY() + endPoint.getY()) / 2;

        return new Point2D(centerX, centerY);
    }

    private Point2D getRadiusPoint(Point2D startPoint, Point2D endPoint) {
        double radiusX = Math.abs(endPoint.getX() - startPoint.getX()) / 2;
        double radiusY = Math.abs(endPoint.getY() - startPoint.getY()) / 2;

        return new Point2D(radiusX, radiusY);
    }

    @Override
    public Point2D setEndPoint(double x, double y) {
        Point2D endPoint = super.setEndPoint(x, y);

        this.setVerticesCount(this.getVerticesCount());

        return endPoint;
    }

    @Override
    public int setVerticesCount(int verticesCount) {
        super.setVerticesCount(verticesCount);

        if (this.getEndPoint() != null) {
            ArrayList<Point2D> vertices = new ArrayList<>(this.getVerticesCount());

            Point2D startPoint = this.getStartPoint();
            Point2D endPoint = this.getEndPoint();
            Point2D centerPoint = this.getCenterPoint(startPoint, endPoint);
            Point2D radiusPoint = this.getRadiusPoint(startPoint, endPoint);

            double angleStep = 2 * Math.PI / this.getVerticesCount();
            double initialAngle = -Math.PI / 2;

            for (int i = 0; i < this.getVerticesCount(); i++) {
                double angle = initialAngle + i * angleStep;

                double x = centerPoint.getX() + radiusPoint.getX() * Math.cos(angle);
                double y = centerPoint.getY() + radiusPoint.getY() * Math.sin(angle);

                vertices.add(new Point2D(x, y));
            }

            this.setVertices(vertices);
        }

        return this.getVerticesCount();
    }

    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        Map<String, double[]> coordinates = this.getCoordinates();

        drawingArea.fillPolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), this.getVerticesCount());
        drawingArea.strokePolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), this.getVerticesCount());
    }
}
