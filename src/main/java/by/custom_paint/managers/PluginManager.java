package by.custom_paint.managers;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.dto.shapes.ShapeDTO;

import by.custom_paint.services.creators.*;
import by.custom_paint.services.factories.*;

import by.custom_paint.services.plugins.ShapePlugin;

import java.util.jar.JarFile;
import java.util.jar.JarEntry;

import java.io.File;

import java.net.URL;
import java.net.URLClassLoader;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PluginManager {
    private final Stage stage;

    private final FileChooser fileChooser;
    private final ExtensionFilter extension;

    private final static String EXTENSION = "*.jar";

    public PluginManager(Stage stage) {
        this.stage = stage;

        this.fileChooser = new FileChooser();
        this.extension = new ExtensionFilter("Jar Files (" + EXTENSION + ")", EXTENSION);

        this.fileChooser.getExtensionFilters().add(extension);
    }

    private boolean containsPlugin(JarFile jar) {
        return jar.stream()
                .map(JarEntry::getName)
                .anyMatch(name -> name.endsWith(".class") && !name.contains("$"));
    }

    private ShapePlugin loadPluginFromJar(URLClassLoader loader, JarEntry entry) throws Exception {
        String className = entry.getName()
                .replace("/", ".")
                .replace(".class", "");

        Class<?> loadedClass;

        try {
            loadedClass = loader.loadClass(className);
        }
        catch (NoClassDefFoundError | IllegalAccessError e) {
            throw new Exception(e.getMessage());
        }

        if (ShapePlugin.class.isAssignableFrom(loadedClass)) {
            return (ShapePlugin) loadedClass.getDeclaredConstructor().newInstance();
        }

        return null;
    }

    private void addPlugin(ShapePlugin plugin) {
        Class<? extends Shape> shapeClass = plugin.getShapeClass();
        Class<? extends ShapeDTO> shapeDTOClass = plugin.getShapeDTOClass();

        String shapeType = plugin.getShapeType();

        ShapeCreator shapeCreator = () -> {
            try {
                return shapeClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        };

        ShapeDTOCreator<Shape> shapeDTOCreator = (shape) -> {
            try {
                return shapeDTOClass.getDeclaredConstructor(Shape.class).newInstance(shape);
            } catch (Exception e) {
                return null;
            }
        };

        ShapeFactory.getInstance().addShapeType(shapeType, shapeCreator);
        ShapeDTOFactory.getInstance().addShapeDTO(shapeType, shapeDTOCreator);
    }

    private ShapePlugin tryToLoadPlugin(File jarFile) throws Exception {
        try (JarFile jar = new JarFile(jarFile)) {
            if (!containsPlugin(jar)) {
                return null;
            }

            URL jarUrl = jarFile.toURI().toURL();

            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl}, ShapePlugin.class.getClassLoader())) {
                for (JarEntry entry : jar.stream().toArray(JarEntry[]::new)) {
                    if (entry.getName().endsWith(".class")) {
                        ShapePlugin plugin;

                        try {
                            plugin = loadPluginFromJar(loader, entry);
                        }
                        catch (Exception e) {
                            continue;
                        }

                        if (plugin != null) {
                            addPlugin(plugin);

                            return plugin;
                        }
                    }
                }
            }
        }

        return null;
    }

    public String loadPlugin() throws Exception {
        this.fileChooser.setTitle("Open Plugin File");

        File file = this.fileChooser.showOpenDialog(this.stage);

        if (file != null) {
            ShapePlugin plugin = tryToLoadPlugin(file);

            if (plugin != null) {
                return plugin.getShapeType();
            }
            else {
                throw new Exception("Plugin could not be loaded");
            }
        }

        return null;
    }
}