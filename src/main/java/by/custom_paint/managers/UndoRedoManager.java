package by.custom_paint.managers;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.services.commands.*;
import by.custom_paint.services.observers.ShapeListObserver;

import java.util.ArrayDeque;

public class UndoRedoManager implements ShapeListObserver {
    private final ArrayDeque<Shape> undoDeque;
    private final ArrayDeque<Shape> redoDeque;

    private boolean isUndo = false;
    private boolean isRedo = false;

    private final ShapeListCommands shapeListCommands;
    private final DrawCommand drawCommand;

    public UndoRedoManager(ShapeListCommands shapeListCommands, DrawCommand drawCommand) {
        this.undoDeque = new ArrayDeque<>();
        this.redoDeque = new ArrayDeque<>();

        this.shapeListCommands = shapeListCommands;
        this.drawCommand = drawCommand;
    }

    public void undo() {
        if (this.undoDeque.isEmpty()) {
            return;
        }

        this.isUndo = true;

        Shape shapeToRemove = this.undoDeque.pollFirst();

        this.shapeListCommands.removeShape(shapeToRemove);
        this.redoDeque.addFirst(shapeToRemove);

        this.isUndo = false;

        this.drawCommand.redraw();
    }

    public void redo() {
        if (this.redoDeque.isEmpty()) {
            return;
        }

        this.isRedo = true;

        Shape shapeToAdd = this.redoDeque.pollFirst();

        this.shapeListCommands.addShape(shapeToAdd);
        this.undoDeque.addFirst(shapeToAdd);

        this.isRedo = false;

        this.drawCommand.redraw();
    }

    public void clearHistory() {
        this.undoDeque.clear();
        this.redoDeque.clear();
    }

    @Override
    public void onShapeAdded(Shape shape) {
        if (!this.isRedo) {
            this.undoDeque.addFirst(shape);

            if (!this.isUndo) {
                this.redoDeque.clear();
            }
        }
    }

    @Override
    public void onShapeRemoved(Shape shape) {
        if (!this.isUndo) {
            this.redoDeque.addFirst(shape);
        }
    }
}