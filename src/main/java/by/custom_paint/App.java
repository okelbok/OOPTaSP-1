package by.custom_paint;

import java.util.Objects;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class App extends Application {
    private static Stage stage;

    public static Image getMainIcon() {
        InputStream iconStream = App.class.getResourceAsStream("images/main_icon.png");

        return new Image(Objects.requireNonNull(iconStream));
    }

    public static Stage getStage() {
        return App.stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;

        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/main_view.fxml"));
        Scene scene = new Scene(loader.load());

        stage.getIcons().add(getMainIcon());
        stage.setTitle("Custom Paint");
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}