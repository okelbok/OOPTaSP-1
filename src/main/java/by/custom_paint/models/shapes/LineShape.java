package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class LineShape extends Shape {
    @Override
    public void draw(GraphicsContext drawingArea) {
        super.draw(drawingArea);

        if (getEndPoint() != null) {
            drawingArea.strokeLine(
                    getStartPoint().getX(), getStartPoint().getY(),
                    getEndPoint().getX(), getEndPoint().getY()
            );
        }
    }
}
