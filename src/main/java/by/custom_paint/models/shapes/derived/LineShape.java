package by.custom_paint.models.shapes.derived;

import by.custom_paint.models.shapes.base.Shape;

import javafx.scene.canvas.GraphicsContext;

public class LineShape extends Shape {
    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        if (this.getEndPoint() != null) {
            drawingArea.strokeLine(
                    this.getStartPoint().getX(), this.getStartPoint().getY(),
                    this.getEndPoint().getX(), this.getEndPoint().getY()
            );
        }
    }
}
