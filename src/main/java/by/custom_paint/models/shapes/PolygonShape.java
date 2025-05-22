package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolygonShape extends PolyShape {
    @Override
    public Point2D setEndPoint(double x, double y) {
        Point2D endPoint = super.setEndPoint(x, y);
        setVerticesCount(getVerticesCount());

        return endPoint;
    }

    @Override
    public int setVerticesCount(int verticesCount) {
        super.setVerticesCount(verticesCount);

        if (getEndPoint() != null) {
            ArrayList<Point2D> vertices = new ArrayList<>(getVerticesCount());

            Point2D startPoint = getStartPoint();
            Point2D endPoint = getEndPoint();

            double centerX = (startPoint.getX() + endPoint.getX()) / 2;
            double centerY = (startPoint.getY() + endPoint.getY()) / 2;

            double radiusX = Math.abs(endPoint.getX() - startPoint.getX()) / 2;
            double radiusY = Math.abs(endPoint.getY() - startPoint.getY()) / 2;

            double angleStep = 2 * Math.PI / getVerticesCount();

            double initialAngle = -Math.PI / 2;

            for (int i = 0; i < getVerticesCount(); i++) {
                double angle = initialAngle + i * angleStep;

                double x = centerX + radiusX * Math.cos(angle);
                double y = centerY + radiusY * Math.sin(angle);

                vertices.add(new Point2D(x, y));
            }

            setVertices(vertices);
        }

        return getVerticesCount();
    }

    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        Map<String, double[]> coordinates = getCoordinates();

        drawingArea.fillPolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
        drawingArea.strokePolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
    }
}
