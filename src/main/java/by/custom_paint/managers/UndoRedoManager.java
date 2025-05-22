package by.custom_paint.managers;

import by.custom_paint.interfaces.commands.DrawCommand;
import by.custom_paint.interfaces.commands.ShapesListCommands;
import by.custom_paint.interfaces.observers.ShapeListObserver;
import by.custom_paint.models.shapes.Shape;

import java.util.Stack;

public class UndoRedoManager implements ShapeListObserver {
    private final Stack<Shape> undoStack;
    private final Stack<Shape> redoStack;

    private final ShapesListCommands shapesListCommands;
    private final DrawCommand drawCommand;

    public UndoRedoManager(ShapesListCommands shapesListCommands, DrawCommand drawCommand) {
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        this.shapesListCommands = shapesListCommands;
        this.drawCommand = drawCommand;
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }

        Shape shapeToRemove = undoStack.pop();

        shapesListCommands.removeShape(shapeToRemove);

        drawCommand.redraw();
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }

        Shape shapeToAdd = redoStack.pop();

        shapesListCommands.addShape(shapeToAdd);

        drawCommand.redraw();
    }

    @Override
    public void onShapeAdded(Shape shape) {
        undoStack.push(shape);
    }

    @Override
    public void onShapeRemoved(Shape shape) {
        redoStack.push(shape);
    }
}