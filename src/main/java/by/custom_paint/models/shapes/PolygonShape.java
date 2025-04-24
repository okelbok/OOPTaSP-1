package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class PolygonShape extends Shape {
    private int sidesCount;
    private double sideLength;
    private double[] xPoints;
    private double[] yPoints;

    private void calculateCoordinates() {
        double r = sideLength / (2 * Math.sin(Math.PI / sidesCount));
        xPoints = new double[sidesCount];
        yPoints = new double[sidesCount];

        for (int i = 0; i < sidesCount; i++) {
            xPoints[i] = startPoint.getX() +  r * Math.cos(2 * Math.PI * i / sidesCount);
            yPoints[i] = startPoint.getY() + r * Math.sin(2 * Math.PI * i / sidesCount);
        }
    }

    public int setSidesCount(int sidesCount) {
        this.sidesCount = sidesCount;

        return this.sidesCount;
    }

    public int getSidesCount() {
        return sidesCount;
    }

    public double setSideLength(double sideLength) {
        this.sideLength = sideLength;

        return this.sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        calculateCoordinates();

        gc.fillPolygon(xPoints, yPoints, sidesCount);
        gc.strokePolygon(xPoints, yPoints, sidesCount);
    }
}
