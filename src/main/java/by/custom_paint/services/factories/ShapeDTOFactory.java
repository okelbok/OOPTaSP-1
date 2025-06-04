package by.custom_paint.services.factories;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.dto.shapes.*;

import by.custom_paint.services.creators.ShapeDTOCreator;

import java.util.HashMap;

public class ShapeDTOFactory {
    private static ShapeDTOFactory instance;

    private final HashMap<String, ShapeDTOCreator<Shape>> shapeDTOCreators;

    private ShapeDTOFactory() {
        this.shapeDTOCreators = new HashMap<>()
        {
            {
                put("Shape", ShapeDTO::new);
                put("PolyShape", PolyShapeDTO::new);
            }
        };
    }

    public static ShapeDTOFactory getInstance() {
        if (instance == null) {
            instance = new ShapeDTOFactory();
        }

        return instance;
    }

    public ShapeDTO createShapeDTO(Shape shape) {
        ShapeDTOCreator<Shape> creator = this.shapeDTOCreators.get(shape.getClass().getSimpleName());

        return creator.createShapeDTO(shape);
    }

    public void addShapeDTO(String shapeType, ShapeDTOCreator<Shape> creator) {
        this.shapeDTOCreators.put(shapeType, creator);
    }
}
