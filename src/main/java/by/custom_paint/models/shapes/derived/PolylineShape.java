package by.custom_paint.models.shapes.derived;

import by.custom_paint.models.shapes.base.PolyShape;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class PolylineShape extends PolyShape {
    public PolylineShape() {
        super();

        this.setMultiClick(true);
    }

    @Override
    public Point2D setStartPoint(double x, double y) {
        Point2D startVertex = super.setStartPoint(x, y);
        ArrayList<Point2D> vertices = (this.getVertices() != null) ? this.getVertices() : new ArrayList<>();

        vertices.add(startVertex);
        this.setVertices(vertices);

        return startVertex;
    }

    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        this.updateVertices();

        Map<String, double[]> coordinates = this.getCoordinates();

        drawingArea.strokePolyline(coordinates.get("xCoordinates"), coordinates.get("yCoordinates"), this.getVerticesCount());

        this.removeLastVertex();
    }
}
