package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class shape {
    protected Point2D start_point;
    protected Color fill_color, border_color;
    protected double border_width;

    public void set_start_point(double x, double y) {
        start_point = new Point2D(x, y);
    }

    public Point2D get_start_point() {
        return start_point;
    }

    public void set_fill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public Color get_fill_color() {
        return fill_color;
    }

    public void set_border_color(Color border_color) {
        this.border_color = border_color;
    }

    public Color get_border_color() {
        return border_color;
    }

    public void set_border_width(double border_width) {
        this.border_width = border_width;
    }

    public double get_border_width() {
        return border_width;
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(border_color);
        gc.setLineWidth(border_width);
        gc.setFill(fill_color);
    };
}
