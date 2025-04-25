package by.custom_paint.models.shapes;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolylineShape extends PolyShape {
    @Override
    public ArrayList<Point2D> setVertices(Optional<ArrayList<Point2D>> vertices) {
        this.vertices = vertices.orElse(new ArrayList<>());
        setVerticesCount(vertices.orElse(new ArrayList<>()).size());

        return this.vertices;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        Map<String, double[]> coordinates = getCoordinates();

        gc.strokePolyline(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), getVerticesCount());
    }
}
