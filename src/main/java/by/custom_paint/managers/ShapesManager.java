package by.custom_paint.managers;

import by.custom_paint.models.shapes.*;
import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.utils.ShapeCreator;

import by.custom_paint.interfaces.commands.ShapesListCommands;
import by.custom_paint.interfaces.observers.ShapeListObserver;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class ShapesManager implements ShapesListCommands {
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

    private final List<ShapeListObserver> shapeListObservers;

    private ShapesManager() {
        shapes = new ShapesList();
        shapeTypes = shapesFactory.keySet().stream().toList();
        shapeListObservers = new ArrayList<>();
    }

    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }

        return instance;
    }

    public void addObserver(ShapeListObserver observer) {
        shapeListObservers.add(observer);
    }

    @Override
    public Shape createShape(int shapeIndex) {
        ShapeCreator<Shape> creator = shapesFactory.get(shapeTypes.get(shapeIndex));

        return creator.create();
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);

        for (ShapeListObserver observer : shapeListObservers) {
            observer.onShapeAdded(shape);
        }
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);

        for (ShapeListObserver observer : shapeListObservers) {
            observer.onShapeRemoved(shape);
        }
    }

    @Override
    public void addShapes(ShapesList shapes) {
        this.shapes.addAll(shapes);
    }

    @Override
    public ShapesList getShapes() {
        return shapes;
    }
    // TODO: add plugin integration
}
