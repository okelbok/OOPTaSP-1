package by.custom_paint.utils;

import by.custom_paint.models.lists.ShapesList;

abstract class FileManager {
    abstract public ShapesList loadFromFile(String path);

    abstract public void saveToFile(String path, ShapesList shapes);
}
