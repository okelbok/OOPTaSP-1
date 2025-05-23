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
        this.drawingArea = canvas.getGraphicsContext2D();
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;

        return this.verticesCount;
    }

    private void resetCurrentShape(int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        this.currentShape = this.shapesListCommands.createShape(shapeIndex);

        this.currentShape.setFillColor(fillColor);
        this.currentShape.setBorderColor(borderColor);
        this.currentShape.setBorderWidth(borderWidth);

        if (this.currentShape instanceof PolyShape) {
            ((PolyShape) this.currentShape).setVerticesCount(this.verticesCount);
        }

        this.isPolyline = this.currentShape instanceof PolylineShape;
    }

    private void previewShape() {
        redraw();

        currentShape.draw(this.drawingArea);
    }

    private void drawShape() {
        this.shapesListCommands.addShape(this.currentShape);
        this.currentShape = null;
        this.isPolyline = false;

        redraw();
    }

    public void handleMousePressed(MouseEvent event, int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        this.isClicked = true;

        if (this.isPolyline) {
            if (event.isSecondaryButtonDown()) {
                drawShape();

                return;
            }
        }
        else {
            resetCurrentShape(shapeIndex, fillColor, borderColor, borderWidth);
        }

        this.currentShape.setStartPoint(event.getX(), event.getY());
    }

    public void handleMouseDragged(MouseEvent event) {
        this.isClicked = false;

        if (this.currentShape == null || event.isSecondaryButtonDown()) {
            return;
        }

        this.currentShape.setEndPoint(event.getX(), event.getY());

        previewShape();
    }

    public void handleMouseReleased(MouseEvent event) {
        if (this.currentShape == null || this.isClicked || event.isSecondaryButtonDown()) {
            return;
        }

        if (this.isPolyline) {
            ((PolylineShape) this.currentShape).addVertex();

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
        ShapesList shapes = this.shapesListCommands.getShapes();

        clearCanvas(this.canvas);

        shapes.resetIterator();
        while (shapes.hasNext()) {
            shapes.next().draw(this.drawingArea);
        }
    }
}
