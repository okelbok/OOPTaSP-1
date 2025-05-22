package by.custom_paint.controllers;

import by.custom_paint.App;
import by.custom_paint.common_utils.MessageBoxHandler;

import java.util.ResourceBundle;

import java.io.IOException;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Spinner;

public class verticesController implements Initializable {
    @FXML
    private Spinner<Integer> verticesCountSpinner;

    private int verticesCount = MIN_VERTICES_COUNT;

    private Stage verticesDialog;
    private Scene verticesScene = null;

    private static final int MIN_VERTICES_COUNT = 3;
    private static final FXMLLoader VERTICES_LOADER = new FXMLLoader(App.class.getResource("views/vertices_view.fxml"));

    @FXML
    private void okButtonClicked() {
        setVerticesCount(verticesCountSpinner.getValue());
        verticesDialog.close();
    }

    private int setVerticesCount(int vertexesCount) {
        this.verticesCount = vertexesCount;

        return this.verticesCount;
    }

    public int getVerticesCount() {
        return this.verticesCount;
    }

    public static verticesController getVerticesController() {
        try {
            VERTICES_LOADER.load();
        }
        catch (IOException e) {
            MessageBoxHandler.showError(
                    "Vertices window error",
                    "Resources for vertices count window are missing or misplaced."
            );
            return null;
        }

        return VERTICES_LOADER.getController();
    }

    public Scene setVerticesScene() {
        if (verticesScene == null) {
            try {
                verticesScene = new Scene(VERTICES_LOADER.load());
            }
            catch (IOException e) {
                verticesScene = new Scene(VERTICES_LOADER.getRoot());
            }
        }

        return verticesScene;
    }

    public void showModal() {
        verticesCountSpinner.getValueFactory().setValue(setVerticesCount(MIN_VERTICES_COUNT));

        verticesDialog = new Stage();
        verticesDialog.initModality(Modality.APPLICATION_MODAL);

        verticesScene = setVerticesScene();

        verticesDialog.getIcons().add(App.getMainIcon());
        verticesDialog.setTitle("Custom Paint: Vertices Count");
        verticesDialog.setScene(verticesScene);

        verticesDialog.setResizable(false);
        verticesDialog.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVerticesScene();
    }
}
