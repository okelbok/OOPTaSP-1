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
import javafx.scene.paint.Color;

import javafx.event.ActionEvent;

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

    private static VerticesController verticesController;

    private int borderWidth = 1;

    private int currentShapeIndex;

    private ShapesManager shapesManager;
    private DrawingProcessManager drawingProcessManager;
    private UndoRedoManager undoRedoManager;

    private final int POLYGON_INDEX = 3;

    private void setCurrentShapeIndex(Button button) {
        HBox parent = (HBox) button.getParent();
        this.currentShapeIndex = parent.getChildren().indexOf(button);
    }

    @FXML
    private void buttonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();

        setCurrentShapeIndex(button);

        if (this.currentShapeIndex == POLYGON_INDEX) {
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

        this.shapesManager = ShapesManager.getInstance();
        this.drawingProcessManager = new DrawingProcessManager(this.shapesManager, this.canvas);
        this.undoRedoManager = new UndoRedoManager(this.shapesManager, this.drawingProcessManager);

        this.shapesManager.addObserver(this.undoRedoManager);
    }
}
