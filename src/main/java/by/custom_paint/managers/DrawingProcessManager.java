package by.custom_paint.managers;

import by.custom_paint.models.shapes.Shape;
import by.custom_paint.models.shapes.PolyShape;
import by.custom_paint.models.shapes.PolylineShape;
import by.custom_paint.models.lists.ShapesList;

import by.custom_paint.interfaces.commands.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingProcessManager implements DrawCommand {
    private Shape currentShape;

    private int verticesCount;
    private boolean isPolyline = false;

    private boolean isClicked = false;

    private final Canvas canvas;
    private final GraphicsContext drawingArea;

    private final ShapesListCommands shapesListCommands;

    public DrawingProcessManager(ShapesListCommands shapesListCommands, Canvas canvas) {
        this.shapesListCommands = shapesListCommands;
        this.canvas = canvas;
        drawingArea = canvas.getGraphicsContext2D();
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return this.verticesCount;
    }

    private void resetCurrentShape(int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        currentShape = shapesListCommands.createShape(shapeIndex);

        currentShape.setFillColor(fillColor);
        currentShape.setBorderColor(borderColor);
        currentShape.setBorderWidth(borderWidth);

        if (currentShape instanceof PolyShape) {
            ((PolyShape) currentShape).setVerticesCount(verticesCount);
        }

        isPolyline = currentShape instanceof PolylineShape;
    }

    private void drawShape() {
        shapesListCommands.addShape(currentShape);
        currentShape = null;
        isPolyline = false;

        redraw();
    }

    private void previewShape() {
        redraw();

        currentShape.draw(drawingArea);
    }

    public void handleMousePressed(MouseEvent event, int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        isClicked = true;

        if (isPolyline) {
            if (event.isSecondaryButtonDown()) {
                drawShape();
                return;
            }
        }
        else {
            resetCurrentShape(shapeIndex, fillColor, borderColor, borderWidth);
        }

        currentShape.setStartPoint(event.getX(), event.getY());
    }

    public void handleMouseDragged(MouseEvent event) {
        isClicked = false;

        if (currentShape == null || event.isSecondaryButtonDown()) {
            return;
        }

        currentShape.setEndPoint(event.getX(), event.getY());

        previewShape();
    }

    public void handleMouseReleased(MouseEvent event) {
        if (currentShape == null || isClicked || event.isSecondaryButtonDown()) {
            return;
        }

        if (isPolyline) {
            ((PolylineShape) currentShape).addVertex();

            previewShape();
        }
        else {
            drawShape();
        }
    }

    public static void clearCanvas(Canvas canvas) {
        GraphicsContext drawingArea = canvas.getGraphicsContext2D();

        drawingArea.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void redraw() {
        ShapesList shapes = shapesListCommands.getShapes();

        clearCanvas(canvas);

        shapes.resetIterator();
        while (shapes.hasNext()) {
            shapes.next().draw(drawingArea);
        }
    }
}
