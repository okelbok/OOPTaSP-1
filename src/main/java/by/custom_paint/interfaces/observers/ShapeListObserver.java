package by.custom_paint.interfaces.observers;

import by.custom_paint.models.shapes.Shape;

public interface ShapeListObserver {
    void onShapeAdded(Shape shape);

    void onShapeRemoved(Shape shape);
}
