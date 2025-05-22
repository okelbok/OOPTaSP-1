package by.custom_paint.managers;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.*;
import by.custom_paint.models.utils.ShapeCreator;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class ShapesManager {
    private static ShapesManager instance;

    private final ShapesList shapes;
    private static Map<String, ShapeCreator<Shape>> shapesFactory = new LinkedHashMap<>()
    {
        {
            put("Line", LineShape::new);
            put("Rectangle", RectangleShape::new);
            put("Ellipse", EllipseShape::new);
            put("Polygon", PolygonShape::new);
            put("Polyline", PolylineShape::new);
        }
    };

    private List<String> shapeTypes;

    private ShapesManager() {
        shapes = new ShapesList();
        shapeTypes = shapesFactory.keySet().stream().toList();
    }

    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }

        return instance;
    }

    public ShapesList getShapes() {
        return shapes;
    }

    public Shape createShape(int shapeIndex) {
        ShapeCreator<Shape> creator = shapesFactory.get(shapeTypes.get(shapeIndex));

        return creator.create();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public void addShapes(ShapesList shapes) {
        this.shapes.addAll(shapes);
    }
    // TODO: add plugin integration
}
