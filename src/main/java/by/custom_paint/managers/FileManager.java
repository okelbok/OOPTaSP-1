package by.custom_paint.managers;

import by.custom_paint.dto.lists.ShapeListDTO;

import by.custom_paint.services.commands.*;

import by.custom_paint.utils.MessageBoxHandler;

import java.io.*;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManager {
    private final Stage stage;

    private final FileChooser fileChooser;
    private final ExtensionFilter extension;

    private final ShapeListCommands shapeListCommands;
    private final DrawCommand drawCommand;

    private final static String EXTENSION = "*.shpl";

    public FileManager(Stage stage, ShapeListCommands shapeListCommands, DrawCommand drawCommand) {
        this.stage = stage;

        this.shapeListCommands = shapeListCommands;
        this.drawCommand = drawCommand;

        this.fileChooser = new FileChooser();
        this.extension = new ExtensionFilter("Shape List Files (" + EXTENSION + ")", EXTENSION);

        this.fileChooser.getExtensionFilters().add(extension);
    }

    private boolean deserialize(File file) {
        ShapeListDTO shapeListDTO;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            shapeListDTO = (ShapeListDTO) in.readObject();
        } catch (IOException e) {
            MessageBoxHandler.showError(
                    "Error loading file",
                    e.getMessage()
            );

            return false;
        }
        catch (ClassNotFoundException e) {
            return false;
        }

        if (shapeListDTO != null) {
            this.shapeListCommands.addShapes(shapeListDTO.toShapeList());
            this.drawCommand.redraw();

            return true;
        }

        return false;
    }

    public boolean loadFromFile() {
        if (!this.shapeListCommands.getShapes().isEmpty()) {
            boolean isConfirmed = MessageBoxHandler.getConfirmation(
                    "Are you sure you want to load shapes from file?",
                    "All of the drawn shapes will be deleted."
            );

            if (!isConfirmed) {
                return false;
            }
        }

        this.fileChooser.setTitle("Open Shape List File");

        File file = this.fileChooser.showOpenDialog(this.stage);

        if (file != null) {
            return deserialize(file);
        }
        else {
            return false;
        }
    }

    private void serialize(File file) {
        ShapeListDTO shapeListDTO = new ShapeListDTO(this.shapeListCommands.getShapes());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(shapeListDTO);
        } catch (IOException e) {
            MessageBoxHandler.showError(
                    "Error saving file",
                    e.getMessage()
            );
        }
    }

    public void saveToFile() {
        if (this.shapeListCommands.getShapes().isEmpty()) {
            MessageBoxHandler.showError(
                    "Shapes cannot be saved to file",
                    "At least one shape has to be drawn."
            );

            return ;
        }

        this.fileChooser.setTitle("Save Shape List File");

        File file = this.fileChooser.showSaveDialog(this.stage);

        if (file != null) {
            serialize(file);
        }
    }
}