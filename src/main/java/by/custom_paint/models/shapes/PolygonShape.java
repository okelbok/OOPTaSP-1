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

    private Point2D getCenterPoint(double width, double height) {
        double centerX = width / 2 + Math.min(getStartPoint().getX(), getEndPoint().getX());
        double centerY = height / 2 + Math.min(getStartPoint().getY(), getEndPoint().getY());

        return new Point2D(centerX, centerY);
    }

    @Override
    public int setVerticesCount(int verticesCount) {
        super.setVerticesCount(verticesCount);

        if (getEndPoint() != null) {
            ArrayList<Point2D> vertices = new ArrayList<>(getVerticesCount());

            double width = Math.abs(getEndPoint().getX() - getStartPoint().getX());
            double height = Math.abs(getEndPoint().getY() - getStartPoint().getY());
            Point2D centerPoint = getCenterPoint(width, height);
            double anglePitch = 2 * Math.PI / getVerticesCount();

            for (int i = 0; i < getVerticesCount(); i++) {
                double angle = i * anglePitch;
                double sideLength = Math.min(
                        (width / 2) / Math.abs(Math.cos(angle)),
                        (height / 2) / Math.abs(Math.sin(angle))
                );

                vertices.add(new Point2D(
                        centerPoint.getX() + sideLength * Math.cos(angle),
                        centerPoint.getY() + sideLength * Math.sin(angle)
                ));
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
