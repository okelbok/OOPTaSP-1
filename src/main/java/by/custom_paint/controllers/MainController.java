package by.custom_paint.controllers;

import by.custom_paint.App;
import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.managers.*;

import by.custom_paint.utils.MessageBoxHandler;

import java.util.*;
import java.net.URL;

import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainController implements Initializable {
    @FXML
    private ColorPicker fillColorPicker, borderColorPicker;
    @FXML
    private Slider borderWidthSlider;
    @FXML
    private Label borderWidthLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private ChoiceBox<String> pluginsChoiceBox;

    private static VerticesController verticesController;

    private int borderWidth = 1;

    private int currentShapeIndex = 0;

    private ShapeManager shapeManager;
    private DrawingProcessManager drawingProcessManager;
    private UndoRedoManager undoRedoManager;
    private FileManager fileManager;
    private PluginManager pluginManager;

    private final static int BASE_SHAPES_COUNT = 5;

    private void setCurrentShapeIndex(Control control) {
        HBox parent = (HBox) control.getParent();

        if (control instanceof Button) {
            this.currentShapeIndex = parent.getChildren().indexOf(control);
        }
    }

    private void changeShape() {
        Shape shape = this.drawingProcessManager.initDrawingProcess(
                this.currentShapeIndex,
                this.fillColorPicker.getValue(),
                this.borderColorPicker.getValue(),
                this.borderWidth
        );

        if (shape.isPolyVertex()) {
            verticesController.showModal();
            this.drawingProcessManager.setVerticesCount(verticesController.getVerticesCount());
        }
    }

    @FXML
    private void shapeButtonClicked(Event event) {
        Control control = (Control) event.getSource();

        this.pluginsChoiceBox.getSelectionModel().clearSelection();

        setCurrentShapeIndex(control);

        changeShape();
    }

    @FXML
    private void drawingProcessUpdated() {
        this.drawingProcessManager.updateDrawingProcess(
                this.fillColorPicker.getValue(),
                this.borderColorPicker.getValue(),
                this.borderWidth
        );
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        this.drawingProcessManager.handleMousePressed(
                event,
                this.currentShapeIndex,
                this.fillColorPicker.getValue(),
                this.borderColorPicker.getValue(),
                this.borderWidth
        );
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        this.drawingProcessManager.handleMouseDragged(event);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        this.drawingProcessManager.handleMouseReleased(event);
    }

    @FXML
    private void undoRequested() {
        this.undoRedoManager.undo();
    }

    @FXML
    private void redoRequested() {
        this.undoRedoManager.redo();
    }

    @FXML
    private void loadFromFileRequested() {
        if (this.fileManager.loadFromFile()) {
            this.undoRedoManager.clearHistory();
        }
    }

    @FXML
    private void saveToFileRequested() {
        this.fileManager.saveToFile();
    }

    @FXML
    private void pluginLoadRequested() {
        String pluginShapeType = null;

        try {
            pluginShapeType = this.pluginManager.loadPlugin();
        }
        catch (Exception e) {
            MessageBoxHandler.showError(
                    "Plugin load error",
                    e.getMessage()
            );
        }

        if (pluginShapeType != null) {
            this.pluginsChoiceBox.getItems().add(pluginShapeType);
        }
    }

    private void setEventListeners() {
        this.borderWidthSlider.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    this.borderWidth = (int) this.borderWidthSlider.getValue();

                    this.borderWidthLabel.setText(String.valueOf(this.borderWidth));

                    this.drawingProcessUpdated();
        });

        this.pluginsChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (!this.pluginsChoiceBox.getItems().isEmpty() && newValue.intValue() > -1) {
                        this.currentShapeIndex = BASE_SHAPES_COUNT + newValue.intValue();
                    }
                    else {
                        this.currentShapeIndex = 0;
                    }

                    changeShape();
        });
    }

    private void setInitialLayout() {
        DrawingProcessManager.clearCanvas(this.canvas);
        this.borderColorPicker.setValue(Color.BLACK);
    }

    private void getVerticesModal() {
        verticesController = VerticesController.getVerticesController();

        if (verticesController != null) {
            verticesController.setVerticesScene();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEventListeners();
        setInitialLayout();
        getVerticesModal();

        this.shapeManager = new ShapeManager();
        this.drawingProcessManager = new DrawingProcessManager(this.canvas, this.shapeManager);
        this.undoRedoManager = new UndoRedoManager(this.shapeManager, this.drawingProcessManager);
        this.fileManager = new FileManager(App.getStage(), this.shapeManager, this.drawingProcessManager);
        this.pluginManager = new PluginManager(App.getStage());

        this.shapeManager.addObserver(this.undoRedoManager);
    }
}
