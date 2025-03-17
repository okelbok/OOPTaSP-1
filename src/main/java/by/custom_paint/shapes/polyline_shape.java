package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class polyline_shape extends shape {
    protected ArrayList<Point2D> points;
    private double[] x_points;
    private double[] y_points;
    private int points_count;

    public void set_points(ArrayList<Point2D> points) {
        this.points = points;
    }

    public ArrayList<Point2D> get_points() {
        return points;
    }

    private void get_points_count() {
        points_count = points.size();
    }

    private void get_points_coordinates() {
        x_points = new double[points_count];
        y_points = new double[points_count];
        for (int i = 0; i < points_count; i++) {
            x_points[i] = points.get(i).getX();
            y_points[i] = points.get(i).getY();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        get_points_count();
        get_points_coordinates();
        gc.strokePolyline(x_points, y_points, points_count);
    }
}
