package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;

public class rectangle_shape extends shape {
    protected Point2D end_point;

    public void set_end_point(double x, double y) {
        end_point = new Point2D(x, y);
    }

    public Point2D get_end_point() {
        return end_point;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.fillRect(start_point.getX(), start_point.getY(), end_point.getX(), end_point.getY());
        gc.strokeRect(start_point.getX(), start_point.getY(), end_point.getX(), end_point.getY());
    }
}
