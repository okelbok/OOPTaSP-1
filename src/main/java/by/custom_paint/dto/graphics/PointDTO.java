package by.custom_paint.dto.graphics;

import java.io.Serial;
import java.io.Serializable;

import javafx.geometry.Point2D;

public class PointDTO implements Serializable {
    private final double x;
    private final double y;

    @Serial
    private final static long serialVersionUID = 1L;

    public PointDTO(Point2D point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
