package by.custom_paint.models.utils;

import by.custom_paint.models.shapes.Shape;

import java.security.Timestamp;

public class Action {
    private Shape shape;
    private Timestamp timestamp;

    public Shape setShape(Shape shape) {
        this.shape = shape;
        return this.shape;
    }

    public Shape getShape() {
        return this.shape;
    }

    public Timestamp setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this.timestamp;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }
}
