package by.custom_paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class polygon_shape extends shape {
    protected int sides_count;
    protected double side_length;
    private double[] x_points;
    private double[] y_points;

    public void set_sides_count(int sides_count) {
        this.sides_count = sides_count;
    }

    public int get_sides_count() {
        return sides_count;
    }

    public void set_side_length(double side_length) {
        this.side_length = side_length;
    }

    public double get_side_length() {
        return side_length;
    }

    private void calculate_coordinates() {
        double R = side_length / (2 * Math.sin(Math.PI / sides_count));
        x_points = new double[sides_count];
        y_points = new double[sides_count];
        for (int i = 0; i < sides_count; i++) {
            x_points[i] = start_point.getX() +  R * Math.cos(2 * Math.PI * i / sides_count);
            y_points[i] = start_point.getY() + R * Math.sin(2 * Math.PI * i / sides_count);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        calculate_coordinates();
        gc.fillPolygon(x_points, y_points, sides_count);
        gc.strokePolygon(x_points, y_points, sides_count);
    }
}
