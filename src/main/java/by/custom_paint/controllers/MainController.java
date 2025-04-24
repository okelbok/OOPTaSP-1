package by.custom_paint.controllers;

import by.custom_paint.models.shapes.*;
import by.custom_paint.services.ShapesManager;

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

    private final Random rand = new Random();
    private Point2D randomPoint;
    private GraphicsContext gc;
    private int borderWidth = 1;

    private static PolygonSidesController polygonSidesController;
    private final ShapesManager shapesManager = ShapesManager.getInstance();

    private void getRandomPoint() {
        randomPoint = new Point2D(rand.nextDouble(0, canvas.getWidth()), rand.nextDouble(0, canvas.getHeight()));
    }

    private void draw(Shape shape) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        getRandomPoint();
        shape.setStartPoint(randomPoint.getX(), randomPoint.getY());

        shape.setFillColor(fillColorPicker.getValue());
        shape.setBorderColor(borderColorPicker.getValue());
        shape.setBorderWidth(borderWidth);

        shapesManager.addShape(shape);
        shape.draw(gc);
    }

    @FXML
    private void lineButtonClicked() {
        LineShape line = new LineShape();

        getRandomPoint();
        line.setEndPoint(randomPoint.getX(), randomPoint.getY());

        draw(line);
    }

    @FXML
    private void rectangleButtonClicked() {
        RectangleShape rectangle = new RectangleShape();

        getRandomPoint();
        rectangle.setEndPoint(randomPoint.getX(), randomPoint.getY());

        draw(rectangle);
    }

    @FXML
    private void ellipseButtonClicked() {
        EllipseShape ellipse = new EllipseShape();

        getRandomPoint();
        ellipse.setWidth(randomPoint.getX());
        ellipse.setHeight(randomPoint.getY());

        draw(ellipse);
    }

    @FXML
    private void polygonButtonClicked() {
        polygonSidesController.showModal();

        PolygonShape polygon = new PolygonShape();

        polygon.setSidesCount(polygonSidesController.getSidesCount());
        polygon.setSideLength(rand.nextDouble(polygon.getSidesCount(), canvas.getWidth() / polygon.getSidesCount()));

        draw(polygon);
    }

    @FXML
    private void polylineButtonClicked() {
        PolylineShape polyline = new PolylineShape();
        int pointsCount = rand.nextInt(3, 20);
        ArrayList<Point2D> points = new ArrayList<>();

        for (int i = 0; i < pointsCount; i++) {
            getRandomPoint();
            points.add(randomPoint);
        }
        polyline.setPoints(points);

        draw(polyline);
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
        FXMLLoader loader = PolygonSidesController.getPolygonSidesLoader();

        try {
            loader.load();
        }
        catch (IOException e) {
            showError(
                    "Resources for polygon angles count window are missing or misplaced.", ""
            );
            loader = null;
        }

        if (loader != null) {
            polygonSidesController = PolygonSidesController.getPolygonSidesLoader().getController();
            polygonSidesController.setPolygonSidesScene();
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
