package by.custom_paint.managers;

import by.custom_paint.models.shapes.Shape;
import by.custom_paint.interfaces.commands.*;
import by.custom_paint.interfaces.observers.ShapeListObserver;

import java.util.ArrayDeque;

public class UndoRedoManager implements ShapeListObserver {
    private final ArrayDeque<Shape> undoDeque;
    private final ArrayDeque<Shape> redoDeque;

    private boolean isUndo = false;
    private boolean isRedo = false;

    private final ShapesListCommands shapesListCommands;
    private final DrawCommand drawCommand;

    public UndoRedoManager(ShapesListCommands shapesListCommands, DrawCommand drawCommand) {
        this.undoDeque = new ArrayDeque<>();
        this.redoDeque = new ArrayDeque<>();

        this.shapesListCommands = shapesListCommands;
        this.drawCommand = drawCommand;
    }

    public void undo() {
        if (undoDeque.isEmpty()) {
            return;
        }

        isUndo = true;

        Shape shapeToRemove = undoDeque.pollFirst();

        shapesListCommands.removeShape(shapeToRemove);
        redoDeque.addFirst(shapeToRemove);

        isUndo = false;

        drawCommand.redraw();
    }

    public void redo() {
        if (redoDeque.isEmpty()) {
            return;
        }

        isRedo = true;

        Shape shapeToAdd = redoDeque.pollFirst();

        shapesListCommands.addShape(shapeToAdd);
        undoDeque.addFirst(shapeToAdd);

        isRedo = false;

        drawCommand.redraw();
    }

    public void clearHistory() {
        undoDeque.clear();
        redoDeque.clear();
    }

    @Override
    public void onShapeAdded(Shape shape) {
        if (!isRedo) {
            undoDeque.addFirst(shape);

            if (!isUndo) {
                redoDeque.clear();
            }
        }
    }

    @Override
    public void onShapeRemoved(Shape shape) {
        if (!isUndo) {
            redoDeque.addFirst(shape);
        }
    }
}