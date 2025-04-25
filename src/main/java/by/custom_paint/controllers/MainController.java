package by.custom_paint.controllers;

import by.custom_paint.managers.ShapesManager;

import java.util.*;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Point2D;

import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

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
    private ScrollPane canvasScrollPane;
    @FXML
    private AnchorPane canvasAnchorPane;

    private GraphicsContext gc;
    private int borderWidth = 1;

    private static PolygonVerticesController polygonVerticesController;
    private final ShapesManager shapesManager = ShapesManager.getInstance();

    private void preview(double startX, double startY, double endX, double endY) {

    }

    private void draw(double startX, double startY, double endX, double endY) {

    }

    @FXML
    private void click(ActionEvent event) {
        Button temp = (Button) event.getSource();
        System.err.println(temp.getText());
    }

    private void setEventListeners() {
        borderWidthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                borderWidth = (int) borderWidthSlider.getValue();

                borderWidthLabel.setText(String.valueOf(borderWidth));
            }
        });
    }

    private void setCanvasLayout() {
        canvas.widthProperty().bind(canvasAnchorPane.widthProperty());
        canvas.heightProperty().bind(canvasAnchorPane.heightProperty());

        canvasScrollPane.setFitToWidth(true);
        canvasScrollPane.setFitToHeight(true);
    }

    private void getPolygonModal() {
        FXMLLoader loader = PolygonVerticesController.getPolygonVerticesLoader();

        try {
            loader.load();
        }
        catch (IOException e) {
            showError(
                    "Resources for polygon vertexes count window are missing or misplaced.", ""
            );
            loader = null;
        }

        if (loader != null) {
            polygonVerticesController = PolygonVerticesController.getPolygonVerticesLoader().getController();
            polygonVerticesController.setPolygonVerticesScene();
        }
    }

    public static void showError(String message, String comment) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Custom Paint: Error Appeared");
        alert.setHeaderText(message);
        alert.setContentText(comment);

        alert.showAndWait();
    }

    public static boolean getConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result;

        alert.setTitle("Custom Paint: Confirmation is Required");
        alert.setContentText(message);

        result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEventListeners();
        setCanvasLayout();
        getPolygonModal();

        gc = canvas.getGraphicsContext2D();
    }
}
