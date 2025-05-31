package by.custom_paint.models.shapes.base;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.geometry.Point2D;

public abstract class PolyShape extends Shape {
    private int verticesCount;
    private ArrayList<Point2D> vertices;

    private Point2D currentVertex;

    protected Map<String, double[]> getCoordinates() {
        Map<String, double[]> coordinates = new HashMap<>() {
            {
                put("xCoordinates", new double[verticesCount]);
                put("yCoordinates", new double[verticesCount]);
            }
        };

        for (int i = 0; i < getVerticesCount(); i++) {
            coordinates.get("xCoordinates")[i] = vertices.get(i).getX();
            coordinates.get("yCoordinates")[i] = vertices.get(i).getY();
        }

        return coordinates;
    }

    protected void updateVertices() {
        if (this.currentVertex != null) {
            this.vertices.add(this.currentVertex);

            this.verticesCount++;
        }
    }

    protected void removeLastVertex() {
        if (this.currentVertex != null) {
            this.vertices.removeLast();

            this.verticesCount--;
        }
    }

    @Override
    public Point2D setEndPoint(double x, double y) {
        super.setEndPoint(x, y);

        this.currentVertex = new Point2D(x, y);

        return this.currentVertex;
    }

    public int getVerticesCount() {
        return this.verticesCount;
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return this.verticesCount;
    }

    public ArrayList<Point2D> getVertices() {
        return this.vertices;
    }

    public void addVertex() {
        this.updateVertices();

        this.currentVertex = null;
    }

    public ArrayList<Point2D> setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
        this.verticesCount = vertices.size();

        return this.vertices;
    }
}
