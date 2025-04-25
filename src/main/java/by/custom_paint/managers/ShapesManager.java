package by.custom_paint.managers;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.*;

import java.util.Map;
import java.util.HashMap;

public class ShapesManager {
    private final ShapesList shapes;
    private static final Map<String, Shape> shapesFactory = new HashMap<>()
    {
        {
            put("Line", new LineShape());
            put("Rectangle", new RectangleShape());
            put("Ellipse", new EllipseShape());
            put("Polygon", new PolygonShape());
            put("Polyline", new PolylineShape());
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

    public Shape createShape(String name) {
        return shapesFactory.get(name);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public void addShapes(ShapesList shapes) {
        this.shapes.addAll(shapes);
    }

    public void removeShapes(ShapesList shapes) {
        this.shapes.removeAll(shapes);
    }
}
