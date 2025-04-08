package by.custom_paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class app extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("views/view.fxml"));
        // TODO: load icons in run-time
        InputStream iconStream = getClass().getResourceAsStream("/images/main_icon.png");
        Image mainIcon = new Image(Objects.requireNonNull(iconStream));
        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(mainIcon);
        stage.setTitle("Custom Paint");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
