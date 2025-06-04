package by.custom_paint.managers;

import by.custom_paint.models.shapes.base.*;

import by.custom_paint.models.lists.ShapeList;

import by.custom_paint.services.commands.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingProcessManager implements DrawCommand {
    private Shape currentShape;

    private int verticesCount;

    private boolean isClicked = false;

    private final Canvas canvas;
    private final GraphicsContext drawingArea;

    private final ShapeListCommands shapeListCommands;

    public DrawingProcessManager(Canvas canvas, ShapeListCommands shapeListCommands) {
        this.canvas = canvas;
        this.drawingArea = canvas.getGraphicsContext2D();

        this.shapeListCommands = shapeListCommands;
    }

    private void setCurrentShapeVerticesCount() {
        if (this.currentShape instanceof PolyShape) {
            ((PolyShape) this.currentShape).setVerticesCount(this.verticesCount);
        }
    }

    private void resetCurrentShape(int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        this.currentShape = this.shapeListCommands.createShape(shapeIndex);

        this.currentShape.setFillColor(fillColor);
        this.currentShape.setBorderColor(borderColor);
        this.currentShape.setBorderWidth(borderWidth);

        this.setCurrentShapeVerticesCount();
    }

    private void previewShape() {
        redraw();

        this.currentShape.draw(this.drawingArea);
    }

    private void drawShape() {
        this.shapeListCommands.addShape(this.currentShape);

        redraw();

        if (this.currentShape instanceof BoundedShape) {
            ((BoundedShape) this.currentShape).normalizePoints();
        }
        this.currentShape = null;
    }

    public static void clearCanvas(Canvas canvas) {
        GraphicsContext drawingArea = canvas.getGraphicsContext2D();

        drawingArea.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public int setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
        this.setCurrentShapeVerticesCount();

        return this.verticesCount;
    }

    public Shape initDrawingProcess(int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        if (this.currentShape != null && this.currentShape.isMultiClick()) {
            drawShape();
        }

        resetCurrentShape(shapeIndex, fillColor, borderColor, borderWidth);

        return this.currentShape;
    }

    public void updateDrawingProcess(Color fillColor, Color borderColor, int borderWidth) {
        if (this.currentShape != null) {
            this.currentShape.setFillColor(fillColor);
            this.currentShape.setBorderColor(borderColor);
            this.currentShape.setBorderWidth(borderWidth);
        }
    }

    public void handleMousePressed(MouseEvent event, int shapeIndex, Color fillColor, Color borderColor, int borderWidth) {
        this.isClicked = true;

        if (this.currentShape == null) {
            initDrawingProcess(shapeIndex, fillColor, borderColor, borderWidth);
        }

        if (this.currentShape.isMultiClick()) {
            if (event.isSecondaryButtonDown()) {
                drawShape();

                return;
            }
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

        if (this.currentShape.isMultiClick()) {
            ((PolyShape) this.currentShape).addVertex();

            previewShape();
        }
        else {
            drawShape();
        }
    }

    @Override
    public void redraw() {
        ShapeList shapes = this.shapeListCommands.getShapes();

        clearCanvas(this.canvas);

        shapes.resetIterator();
        while (shapes.hasNext()) {
            shapes.next().draw(this.drawingArea);
        }
    }
}
