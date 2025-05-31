package by.custom_paint.models.shapes.derived;

import by.custom_paint.models.shapes.base.BoundedShape;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape extends BoundedShape {
    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        if (this.getEndPoint() != null) {
            Point2D startPoint = this.getNormalizedPoint(this.getStartPoint(), this.getEndPoint(), true);
            Point2D endPoint = this.getNormalizedPoint(this.getStartPoint(), this.getEndPoint(), false);

            double width = endPoint.getX() - startPoint.getX();
            double height = endPoint.getY() - startPoint.getY();

            drawingArea.fillRect(
                    startPoint.getX(), startPoint.getY(),
                    width, height
            );
            drawingArea.strokeRect(
                    startPoint.getX(), startPoint.getY(),
                    width, height
            );
        }
    }
}
