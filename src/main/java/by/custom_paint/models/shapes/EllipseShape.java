package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class EllipseShape extends Shape {
    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        if (getEndPoint() != null) {
            swapPoints();

            double width = getEndPoint().getX() - getStartPoint().getX();
            double height = getEndPoint().getY() - getStartPoint().getY();

            drawingArea.fillOval(
                    getStartPoint().getX(), getStartPoint().getY(),
                    width, height
            );
            drawingArea.strokeOval(
                    getStartPoint().getX(), getStartPoint().getY(),
                    width, height
            );
        }
    }
}
