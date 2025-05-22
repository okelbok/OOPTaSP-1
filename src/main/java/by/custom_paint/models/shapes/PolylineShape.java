package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolylineShape extends PolyShape {
    @Override
    public Point2D setStartPoint(double x, double y) {
        Point2D startVertex = super.setStartPoint(x, y);
        ArrayList<Point2D> vertices = new ArrayList<>();

        vertices.add(startVertex);
        setVertices(vertices);

        return startVertex;
    }

    public Point2D addVertex(double x, double y, GraphicsContext drawingArea) {
        Point2D newVertex = new Point2D(x, y);
        ArrayList<Point2D> vertices = getVertices();

        vertices.add(newVertex);
        setVertices(vertices);

        draw(drawingArea);

        return newVertex;
    }

    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        Map<String, double[]> coordinates = getCoordinates();

        drawingArea.strokePolyline(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
    }
}
