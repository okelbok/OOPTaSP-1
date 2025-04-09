package by.custom_paint.utils;

import by.custom_paint.models.lists.ActionsList;

abstract class UndoRedoManager {
    private ActionsList actions;

    abstract public ActionsList getActions();

    abstract public void updateActions();

    abstract public void undo();

    abstract public void redo();
}