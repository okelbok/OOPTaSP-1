package by.custom_paint.models.shapes;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PolylineShape extends Shape {
    private ArrayList<Point2D> points;
    private double[] xPoints;
    private double[] yPoints;
    private int pointsCount;

    private void setPointsCount() { this.pointsCount = points.size(); }

    private void getPointsCoordinates() {
        xPoints = new double[pointsCount];
        yPoints = new double[pointsCount];

        for (int i = 0; i < pointsCount; i++) {
            xPoints[i] = points.get(i).getX();
            yPoints[i] = points.get(i).getY();
        }
    }

    public ArrayList<Point2D> setPoints(ArrayList<Point2D> points) {
        this.points = points;

        return this.points;
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        setPointsCount();
        getPointsCoordinates();

        gc.strokePolyline(xPoints, yPoints, pointsCount);
    }
}
