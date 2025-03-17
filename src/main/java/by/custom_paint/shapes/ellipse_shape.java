package by.custom_paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class ellipse_shape extends shape {
    protected double width, height;

    public void set_width(double width) {
        this.width = width;
    }

    public double get_width() {
        return width;
    }

    public void set_height(double height) {
        this.height = height;
    }

    public double get_height() {
        return height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.fillOval(start_point.getX(), start_point.getY(), width, height);
        gc.strokeOval(start_point.getX(), start_point.getY(), width, height);
    }
}
