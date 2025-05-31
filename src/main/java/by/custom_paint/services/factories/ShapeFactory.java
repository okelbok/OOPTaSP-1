package by.custom_paint.services.factories;

import by.custom_paint.models.shapes.base.Shape;
import by.custom_paint.models.shapes.derived.*;

import by.custom_paint.services.creators.ShapeCreator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShapeFactory {
    private static ShapeFactory instance;

    private final LinkedHashMap<String, ShapeCreator<Shape>> shapeCreators;
    private final ArrayList<String> shapeTypes;

    private ShapeFactory() {
        shapeCreators = new LinkedHashMap<>()
        {
            {
                put("Line", LineShape::new);
                put("Rectangle", RectangleShape::new);
                put("Ellipse", EllipseShape::new);
                put("Polygon", PolygonShape::new);
                put("Polyline", PolylineShape::new);
            }
        };

        shapeTypes = new ArrayList<>(shapeCreators.keySet());
    }

    public static ShapeFactory getInstance() {
        if (instance == null) {
            instance = new ShapeFactory();
        }

        return instance;
    }

    public Shape createShape(int shapeIndex) {
        ShapeCreator<Shape> creator = shapeCreators.get(this.shapeTypes.get(shapeIndex));

        return creator.create();
    }

    public void addShapeType(String shapeType, ShapeCreator<Shape> creator) {
        this.shapeTypes.add(shapeType);

        this.shapeCreators.put(shapeType, creator);
    }
}
