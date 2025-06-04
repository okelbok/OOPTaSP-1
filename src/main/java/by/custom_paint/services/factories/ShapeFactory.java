package by.custom_paint.services.factories;

import by.custom_paint.models.shapes.base.Shape;
import by.custom_paint.models.shapes.derived.*;

import by.custom_paint.services.creators.ShapeCreator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShapeFactory {
    private static ShapeFactory instance;

    private final LinkedHashMap<String, ShapeCreator> shapeCreators;
    private final ArrayList<String> shapeTypes;

    private ShapeFactory() {
        this.shapeCreators = new LinkedHashMap<>()
        {
            {
                put("LineShape", LineShape::new);
                put("RectangleShape", RectangleShape::new);
                put("EllipseShape", EllipseShape::new);
                put("PolygonShape", PolygonShape::new);
                put("PolylineShape", PolylineShape::new);
            }
        };

        this.shapeTypes = new ArrayList<>(shapeCreators.keySet());
    }

    public static ShapeFactory getInstance() {
        if (instance == null) {
            instance = new ShapeFactory();
        }

        return instance;
    }

    public Shape createShape(int shapeIndex) {
        ShapeCreator creator = this.shapeCreators.get(this.shapeTypes.get(shapeIndex));

        return creator.createShape();
    }

    public Shape createShape(String shapeType) {
        ShapeCreator creator = this.shapeCreators.get(shapeType);

        return creator.createShape();
    }

    public void addShapeType(String shapeType, ShapeCreator creator) {
        this.shapeTypes.add(shapeType);

        this.shapeCreators.put(shapeType, creator);
    }
}
