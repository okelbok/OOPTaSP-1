package by.custom_paint.controls;

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

public class main_control implements Initializable {
    @FXML
    Button line_button, rectangle_button, ellipse_button, polygon_button, polyline_button;
    @FXML
    Canvas canvas;

    @FXML
    public void line_button_clicked(MouseEvent event) {
        line_shape line = new line_shape();
        get_random_point();
        line.set_end_point(random_point.getX(), random_point.getY());
        click(line);
    }

    @FXML
    public void rectangle_button_clicked(MouseEvent event) {
        rectangle_shape rectangle = new rectangle_shape();
        get_random_point();
        rectangle.set_end_point(random_point.getX(), random_point.getY());
        click(rectangle);
    }

    @FXML
    public void ellipse_button_clicked(MouseEvent event) {
        ellipse_shape ellipse = new ellipse_shape();
        get_random_point();
        ellipse.set_width(random_point.getX());
        ellipse.set_height(random_point.getY());
        click(ellipse);
    }

    @FXML
    public void polygon_button_clicked(MouseEvent event) {
        polygon_shape polygon = new polygon_shape();
        polygon.set_sides_count(rand.nextInt(3, 20));
        polygon.set_side_length(rand.nextDouble(polygon.get_sides_count(), canvas.getWidth() / polygon.get_sides_count()));
        click(polygon);
    }

    @FXML
    public void polyline_button_clicked(MouseEvent event) {
        polyline_shape polyline = new polyline_shape();
        int points_count = rand.nextInt(3, 20);
        ArrayList<Point2D> points = new ArrayList<>();
        for (int i = 0; i < points_count; i++) {
            get_random_point();
            points.add(random_point);
        }
        polyline.set_points(points);
        click(polyline);
    }

    private final Random rand = new Random();
    private Point2D random_point;
    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
    }

    private void get_random_point() {
        random_point = new Point2D(rand.nextDouble(0, canvas.getWidth()), rand.nextDouble(0, canvas.getHeight()));
    }

    private Color get_random_color() {
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    private void click(shape new_shape) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        get_random_point();
        new_shape.set_start_point(random_point.getX(), random_point.getY());
        new_shape.set_fill_color(get_random_color());
        new_shape.set_border_color(get_random_color());
        new_shape.set_border_width(1 + rand.nextDouble() * 10);
        new_shape.draw(gc);
    }
}
