package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;

public class lineShape extends shape {
    private Point2D endPoint;

    public Point2D setEndPoint(double x, double y) {
        endPoint = new Point2D(x, y);
        return endPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }
}
