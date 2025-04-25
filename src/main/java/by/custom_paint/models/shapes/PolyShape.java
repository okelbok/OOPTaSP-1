package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import javafx.geometry.Point2D;

public abstract class PolyShape extends Shape {
    private int verticesCount;
    protected ArrayList<Point2D> vertices;

    protected int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return this.verticesCount;
    }

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

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public ArrayList<Point2D> setVertices(Optional<ArrayList<Point2D>> vertices) {
        this.vertices = vertices.orElse(new ArrayList<>());

        return this.vertices;
    }
}
