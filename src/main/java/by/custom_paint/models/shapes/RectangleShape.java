package by.custom_paint.models.shapes;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape extends Shape {
    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        gc.fillRect(
                getStartPoint().getX(), getStartPoint().getY(),
                getEndPoint().getX(), getEndPoint().getY()
        );
        gc.strokeRect(
                getStartPoint().getX(), getStartPoint().getY(),
                getEndPoint().getX(), getEndPoint().getY()
        );
    }
}
