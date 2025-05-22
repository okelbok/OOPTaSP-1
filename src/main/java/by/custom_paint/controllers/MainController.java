package by.custom_paint.controllers;

import by.custom_paint.managers.*;

import java.util.*;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane canvasAnchorPane;

    private static PolygonVerticesController polygonVerticesController;

    private int borderWidth = 1;

    private int currentShapeIndex;

    private DrawingProcessManager drawingProcessManager;

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
            polygonVerticesController.showModal();
            drawingProcessManager.setVerticesCount(polygonVerticesController.getVerticesCount());
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

    private void setEventListeners() {
        borderWidthSlider.valueProperty().addListener((observableValue, number, t1) -> {
            borderWidth = (int) borderWidthSlider.getValue();

            borderWidthLabel.setText(String.valueOf(borderWidth));
        });
    }

    private void setInitialLayout() {
        canvas.widthProperty().bind(canvasAnchorPane.widthProperty());
        canvas.heightProperty().bind(canvasAnchorPane.heightProperty());

        DrawingProcessManager.clearCanvas(canvas);
        borderColorPicker.setValue(Color.BLACK);
    }

    private void getPolygonModal() {
        polygonVerticesController = PolygonVerticesController.getPolygonVerticesController();

        if (polygonVerticesController != null) {
            polygonVerticesController.setPolygonVerticesScene();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEventListeners();
        setInitialLayout();
        getPolygonModal();

        drawingProcessManager = new DrawingProcessManager(canvas);
    }
}
