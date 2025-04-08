package by.custom_paint.controllers;

import by.custom_paint.models.shapes.*;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private Slider borderWidthSlider;
    @FXML
    private Label borderWidthLabel;

    private final Random rand = new Random();
    private Point2D randomPoint;
    private GraphicsContext gc;
    private int borderWidth;

    private void getRandomPoint() {
        randomPoint = new Point2D(rand.nextDouble(0, canvas.getWidth()), rand.nextDouble(0, canvas.getHeight()));
    }

    private Color getRandomColor() {
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    private void click(Shape newShape) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        getRandomPoint();
        newShape.setStartPoint(randomPoint.getX(), randomPoint.getY());

        newShape.setFillColor(getRandomColor());
        newShape.setBorderColor(getRandomColor());
        newShape.setBorderWidth(1 + rand.nextDouble() * 10);

        newShape.draw(gc);
    }

    @FXML
    public void lineButtonClicked() {
        LineShape line = new LineShape();

        getRandomPoint();
        line.setEndPoint(randomPoint.getX(), randomPoint.getY());

        click(line);
    }

    @FXML
    public void rectangleButtonClicked() {
        RectangleShape rectangle = new RectangleShape();

        getRandomPoint();
        rectangle.setEndPoint(randomPoint.getX(), randomPoint.getY());

        click(rectangle);
    }

    @FXML
    public void ellipseButtonClicked() {
        EllipseShape ellipse = new EllipseShape();

        getRandomPoint();
        ellipse.setWidth(randomPoint.getX());
        ellipse.setHeight(randomPoint.getY());

        click(ellipse);
    }

    @FXML
    public void polygonButtonClicked() {
        PolygonShape polygon = new PolygonShape();

        polygon.setSidesCount(rand.nextInt(3, 20));
        polygon.setSideLength(rand.nextDouble(polygon.getSidesCount(), canvas.getWidth() / polygon.getSidesCount()));

        click(polygon);
    }

    @FXML
    public void polylineButtonClicked() {
        PolylineShape polyline = new PolylineShape();
        int pointsCount = rand.nextInt(3, 20);
        ArrayList<Point2D> points = new ArrayList<>();

        for (int i = 0; i < pointsCount; i++) {
            getRandomPoint();
            points.add(randomPoint);
        }
        polyline.setPoints(points);

        click(polyline);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEventListeners();
        gc = canvas.getGraphicsContext2D();
    }
}
