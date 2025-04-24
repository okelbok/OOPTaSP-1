package by.custom_paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class App extends Application {
    public static Image getMainIcon() {
        InputStream iconStream = App.class.getResourceAsStream("/images/main_icon.png");

        return new Image(Objects.requireNonNull(iconStream));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/main_view.fxml"));
        String css = Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm();

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(css);

        stage.getIcons().add(getMainIcon());
        stage.setTitle("Custom Paint");
        stage.setScene(scene);

        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}