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
                put(LineShape.class.getSimpleName(), LineShape::new);
                put(RectangleShape.class.getSimpleName(), RectangleShape::new);
                put(EllipseShape.class.getSimpleName(), EllipseShape::new);
                put(PolygonShape.class.getSimpleName(), PolygonShape::new);
                put(PolylineShape.class.getSimpleName(), PolylineShape::new);
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

    public boolean containsShapeType(String shapeType) {
        return this.shapeTypes.contains(shapeType);
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
