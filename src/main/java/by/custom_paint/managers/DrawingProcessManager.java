package by.custom_paint.managers;

import by.custom_paint.models.lists.ShapesList;
import by.custom_paint.models.shapes.PolyShape;
import by.custom_paint.models.shapes.Shape;
import by.custom_paint.models.shapes.PolylineShape;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingProcessManager {
    private final ShapesManager shapesManager;

    private int verticesCount;
    private boolean isPolyline = false;

    private Shape currentShape;

    private final Canvas canvas;
    private final GraphicsContext drawingArea;

    public DrawingProcessManager(Canvas canvas) {
        this.canvas = canvas;
        drawingArea = canvas.getGraphicsContext2D();

        shapesManager = ShapesManager.getInstance();
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return this.verticesCount;
    }

    private void resetCurrentShape(int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        currentShape = shapesManager.createShape(shapeIndex);

        currentShape.setFillColor(fillColor);
        currentShape.setBorderColor(borderColor);
        currentShape.setBorderWidth(borderWidth);

        if (currentShape instanceof PolyShape) {
            ((PolyShape) currentShape).setVerticesCount(verticesCount);
        }

        isPolyline = currentShape instanceof PolylineShape;
    }

    private void redraw() {
        ShapesList shapes = shapesManager.getShapes();

        clearCanvas(canvas);

        shapes.resetIterator();
        while (shapes.hasNext()) {
            shapes.next().draw(drawingArea);
        }
    }

    private void drawShape() {
        shapesManager.addShape(currentShape);
        currentShape = null;
        isPolyline = false;

        redraw();
    }

    public void handleMousePressed(MouseEvent event, int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
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

    private void previewShape() {
        redraw();

        currentShape.draw(drawingArea);
    }

    public void handleMouseDragged(MouseEvent event) {
        if (currentShape == null || event.isSecondaryButtonDown()) return;

        currentShape.setEndPoint(event.getX(), event.getY());

        previewShape();
    }

    public void handleMouseReleased(MouseEvent event) {
        if (currentShape == null || event.isSecondaryButtonDown()) return;

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
}
