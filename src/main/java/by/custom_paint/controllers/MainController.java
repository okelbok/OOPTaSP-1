package by.custom_paint.controllers;

import by.custom_paint.models.shapes.base.Shape;

import by.custom_paint.managers.*;

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

    private int currentShapeIndex;

    private ShapeManager shapeManager;
    private DrawingProcessManager drawingProcessManager;
    private UndoRedoManager undoRedoManager;
    private FileManager fileManager;

    private void setCurrentShapeIndex(Control control) {
        HBox parent = (HBox) control.getParent();

        if (control instanceof Button) {
            this.currentShapeIndex = parent.getChildren().indexOf(control);
        }
    }

    @FXML
    private void choiceItemClicked(Event event) {
        Control control = (Control) event.getSource();

        setCurrentShapeIndex(control);

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

    }

    @FXML
    private void saveToFileRequested() {

    }

    private void setEventListeners() {
        this.borderWidthSlider.valueProperty().addListener((observableValue, number, t1) -> {
            this.borderWidth = (int) this.borderWidthSlider.getValue();

            this.borderWidthLabel.setText(String.valueOf(this.borderWidth));
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

        this.shapeManager = ShapeManager.getInstance();
        this.drawingProcessManager = new DrawingProcessManager(this.shapeManager, this.canvas);
        this.undoRedoManager = new UndoRedoManager(this.shapeManager, this.drawingProcessManager);
        this.fileManager = new FileManager();

        this.shapeManager.addObserver(this.undoRedoManager);
    }
}
