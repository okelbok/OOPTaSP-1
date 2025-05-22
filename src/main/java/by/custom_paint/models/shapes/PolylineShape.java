package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolylineShape extends PolyShape {
    private Point2D tempVertex = null;

    @Override
    public Point2D setStartPoint(double x, double y) {
        Point2D startVertex = super.setStartPoint(x, y);
        ArrayList<Point2D> vertices = (getVertices() != null) ? getVertices() : new ArrayList<>();

        vertices.add(startVertex);
        setVertices(vertices);

        return startVertex;
    }

    @Override
    public Point2D setEndPoint(double x, double y) {
        tempVertex = new Point2D(x, y);

        return tempVertex;
    }

    private void updateVertices(Point2D newVertex) {
        ArrayList<Point2D> vertices = getVertices();

        vertices.add(newVertex);
        setVertices(vertices);
    }

    private void removeLastVertex() {
        ArrayList<Point2D> vertices = getVertices();

        vertices.removeLast();
        setVertices(vertices);
    }

    public void addVertex() {
        updateVertices(tempVertex);

        tempVertex = null;
    }

    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        if (tempVertex != null) {
            updateVertices(tempVertex);
        }

        Map<String, double[]> coordinates = getCoordinates();

        drawingArea.strokePolyline(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());

        if (tempVertex != null) {
            removeLastVertex();
        }
    }
}
