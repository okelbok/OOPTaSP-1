package by.custom_paint.controllers;

import by.custom_paint.shapes.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    Button lineButton, rectangleButton, ellipseButton, polygonButton, polylineButton;
    @FXML
    Canvas canvas;

    @FXML
    public void lineButtonClicked(MouseEvent event) {
        lineShape line = new lineShape();
        getRandomPoint();
        line.setEndPoint(randomPoint.getX(), randomPoint.getY());
        click(line);
    }

    @FXML
    public void rectangleButtonClicked(MouseEvent event) {
        rectangleShape rectangle = new rectangleShape();
        getRandomPoint();
        rectangle.setEndPoint(randomPoint.getX(), randomPoint.getY());
        click(rectangle);
    }

    @FXML
    public void ellipseButtonClicked(MouseEvent event) {
        ellipseShape ellipse = new ellipseShape();
        getRandomPoint();
        ellipse.setWidth(randomPoint.getX());
        ellipse.setHeight(randomPoint.getY());
        click(ellipse);
    }

    @FXML
    public void polygonButtonClicked(MouseEvent event) {
        polygonShape polygon = new polygonShape();
        polygon.setSidesCount(rand.nextInt(3, 20));
        polygon.setSideLength(rand.nextDouble(polygon.getSidesCount(), canvas.getWidth() / polygon.getSidesCount()));
        click(polygon);
    }

    @FXML
    public void polylineButtonClicked(MouseEvent event) {
        polylineShape polyline = new polylineShape();
        int pointsCount = rand.nextInt(3, 20);
        ArrayList<Point2D> points = new ArrayList<>();
        for (int i = 0; i < pointsCount; i++) {
            getRandomPoint();
            points.add(randomPoint);
        }
        polyline.setPoints(points);
        click(polyline);
    }

    private final Random rand = new Random();
    private Point2D randomPoint;
    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
    }

    private void getRandomPoint() {
        randomPoint = new Point2D(rand.nextDouble(0, canvas.getWidth()), rand.nextDouble(0, canvas.getHeight()));
    }

    private Color getRandomColor() {
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    private void click(shape newShape) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        getRandomPoint();
        newShape.setStartPoint(randomPoint.getX(), randomPoint.getY());
        newShape.setFillColor(getRandomColor());
        newShape.setBorderColor(getRandomColor());
        newShape.setBorderWidth(1 + rand.nextDouble() * 10);
        newShape.draw(gc);
    }
}
