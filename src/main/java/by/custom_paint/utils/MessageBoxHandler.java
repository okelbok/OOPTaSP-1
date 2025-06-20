package by.custom_paint.utils;

import by.custom_paint.App;

import java.util.Optional;

import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MessageBoxHandler {
    public static void showError(String message, String comment) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

        alertStage.getIcons().add(App.getMainIcon());
        alert.setTitle("Custom Paint: Error Appeared");
        alert.setHeaderText(message);
        alert.setContentText(comment);

        alert.showAndWait();
    }

    public static boolean getConfirmation(String message, String comment) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result;
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

        alertStage.getIcons().add(App.getMainIcon());
        alert.setTitle("Custom Paint: Confirmation Needed");
        alert.setHeaderText(message);
        alert.setContentText(comment);

        result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}

