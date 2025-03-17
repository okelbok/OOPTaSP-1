package by.custom_paint.shapes;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class polylineShape extends shape {
    protected ArrayList<Point2D> points;
    private double[] xPoints;
    private double[] yPoints;
    private int pointsCount;

    public ArrayList<Point2D> setPoints(ArrayList<Point2D> points) {
        this.points = points;
        return this.points;
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    private void getPointsCount() {
        pointsCount = points.size();
    }

    private void getPointsCoordinates() {
        xPoints = new double[pointsCount];
        yPoints = new double[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            xPoints[i] = points.get(i).getX();
            yPoints[i] = points.get(i).getY();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        getPointsCount();
        getPointsCoordinates();
        gc.strokePolyline(xPoints, yPoints, pointsCount);
    }
}
