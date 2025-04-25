package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolygonShape extends PolyShape {
    private Point2D getCenterPoint(double width, double height) {
        double centerX = width / 2 + Math.min(getStartPoint().getX(), getEndPoint().getX());
        double centerY = height / 2 + Math.min(getStartPoint().getY(), getEndPoint().getY());

        return new Point2D(centerX, centerY);
    }

    @Override
    public ArrayList<Point2D> setVertices(Optional<ArrayList<Point2D>> vertices) {
        super.setVertices(vertices);

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

            this.vertices.add(new Point2D(
                    centerPoint.getX() + sideLength * Math.cos(angle),
                    centerPoint.getY() + sideLength * Math.sin(angle)
            ));
        }

        return this.vertices;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        Map<String, double[]> coordinates = getCoordinates();

        gc.fillPolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
        gc.strokePolygon(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
    }
}
