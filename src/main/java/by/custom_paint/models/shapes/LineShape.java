package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class LineShape extends Shape {
    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        gc.strokeLine(
                getStartPoint().getX(), getStartPoint().getY(),
                getEndPoint().getX(), getEndPoint().getY()
        );
    }
}
