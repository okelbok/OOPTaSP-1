package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class EllipseShape extends Shape {
    private double width, height;

    private double setWidth() {
        width = Math.abs(getEndPoint().getX() - getStartPoint().getX());

        return width;
    }

    private double setHeight() {
        height = Math.abs(getEndPoint().getY() - getStartPoint().getY());

        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        gc.fillOval(
                getStartPoint().getX(), getStartPoint().getY(),
                setWidth(), setHeight());
        gc.strokeOval(
                getStartPoint().getX(), getStartPoint().getY(),
                getWidth(), getHeight());
    }
}
