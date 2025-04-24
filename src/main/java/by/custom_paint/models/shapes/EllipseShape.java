package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class EllipseShape extends Shape {
    protected double width, height;

    public double setWidth(double width) {
        this.width = width;

        return this.width;
    }

    public double getWidth() {
        return width;
    }

    public double setHeight(double height) {
        this.height = height;
        return this.height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        gc.fillOval(startPoint.getX(), startPoint.getY(), width, height);
        gc.strokeOval(startPoint.getX(), startPoint.getY(), width, height);
    }
}
