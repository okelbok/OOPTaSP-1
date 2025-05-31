package by.custom_paint.services.observers;

import by.custom_paint.models.shapes.base.Shape;

public interface ShapeListObserver {
    void onShapeAdded(Shape shape);

    void onShapeRemoved(Shape shape);
}
