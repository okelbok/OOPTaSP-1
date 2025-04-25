package by.custom_paint.managers;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.*;

import java.util.Map;
import java.util.HashMap;

public class ShapesManager {
    private final ShapesList shapes;

    private static final Map<ShapeType, Shape> shapesFactory = new HashMap<ShapeType, Shape>()
    {
        {
            put(ShapeType.LINE, new LineShape());
            put(ShapeType.RECTANGLE, new RectangleShape());
            put(ShapeType.ELLIPSE, new EllipseShape());
            put(ShapeType.POLYGON, new PolygonShape());
            put(ShapeType.POLYLINE, new PolylineShape());
        }
    };
    private static ShapesManager instance;

    private ShapesManager() {
        shapes = new ShapesList();
    }

    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }

        return instance;
    }

    public ShapesList getShapes() {
        return this.shapes;
    }

    public Shape createShape(ShapeType shapeType) {
        return shapesFactory.get(shapeType);
    }

    public void removeShape(Shape shape) {

    }

    public void updateShape(Shape shape) {

    }

    public void addShapes(ShapesList shapes) {

    }

    public void removeShapes(ShapesList shapes) {

    }
}
