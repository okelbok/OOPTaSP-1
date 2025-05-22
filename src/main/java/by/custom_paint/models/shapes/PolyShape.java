package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.geometry.Point2D;

public abstract class PolyShape extends Shape {
    private int verticesCount;
    private ArrayList<Point2D> vertices;

    protected Map<String, double[]> getCoordinates() {
        Map<String, double[]> coordinates = new HashMap<>() {
            {
                put("xCoordinates", new double[getVerticesCount()]);
                put("yCoordinates", new double[getVerticesCount()]);
            }
        };

        for (int i = 0; i < getVerticesCount(); i++) {
            coordinates.get("xCoordinates")[i] = getVertices().get(i).getX();
            coordinates.get("yCoordinates")[i] = getVertices().get(i).getY();
        }

        return coordinates;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return verticesCount;
    }

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public ArrayList<Point2D> setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
        verticesCount = vertices.size();

        return vertices;
    }
}
