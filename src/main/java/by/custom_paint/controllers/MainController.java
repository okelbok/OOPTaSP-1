package by.custom_paint.controllers;

import by.custom_paint.managers.*;

import java.util.*;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;
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
    public MenuItem undoMenuItem;
    @FXML
    public MenuItem redoMenuItem;

    private static verticesController verticesController;

    private int borderWidth = 1;

    private int currentShapeIndex;

    private ShapesManager shapesManager;
    private DrawingProcessManager drawingProcessManager;
    private UndoRedoManager undoRedoManager;

    private final int POLYGON_INDEX = 3;

    private void setCurrentShapeIndex(Button button) {
        HBox parent = (HBox) button.getParent();
        currentShapeIndex = parent.getChildren().indexOf(button);
    }

    @FXML
    private void buttonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();

        setCurrentShapeIndex(button);

        if (currentShapeIndex == POLYGON_INDEX) {
            verticesController.showModal();
            drawingProcessManager.setVerticesCount(verticesController.getVerticesCount());
        }
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        drawingProcessManager.handleMousePressed(
                event,
                currentShapeIndex,
                fillColorPicker.getValue(),
                borderColorPicker.getValue(),
                borderWidth
        );
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        drawingProcessManager.handleMouseDragged(event);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        drawingProcessManager.handleMouseReleased(event);
    }

    @FXML
    private void undoRequested() {
        undoRedoManager.undo();
    }

    @FXML
    private void redoRequested() {
        undoRedoManager.redo();
    }

    private void setEventListeners() {
        borderWidthSlider.valueProperty().addListener((observableValue, number, t1) -> {
            borderWidth = (int) borderWidthSlider.getValue();

            borderWidthLabel.setText(String.valueOf(borderWidth));
        });
    }

    private void setInitialLayout() {
        DrawingProcessManager.clearCanvas(canvas);
        borderColorPicker.setValue(Color.BLACK);
    }

    private void getPolygonModal() {
        verticesController = verticesController.getVerticesController();

        if (verticesController != null) {
            verticesController.setVerticesScene();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEventListeners();
        setInitialLayout();
        getPolygonModal();

        shapesManager = ShapesManager.getInstance();
        drawingProcessManager = new DrawingProcessManager(shapesManager, canvas);
        undoRedoManager = new UndoRedoManager(shapesManager, drawingProcessManager);

        shapesManager.addObserver(undoRedoManager);
    }
}
