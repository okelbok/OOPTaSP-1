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

public class PolygonVerticesController implements Initializable {
    @FXML
    private Spinner<Integer> verticesCountSpinner;

    private int verticesCount = MIN_VERTICES_COUNT;

    private Stage polygonVerticesDialog;
    private Scene polygonVerticesScene = null;

    private static final int MIN_VERTICES_COUNT = 3;
    private static final FXMLLoader VERTICES_LOADER = new FXMLLoader(App.class.getResource("views/polygon_vertices_view.fxml"));

    @FXML
    private void okButtonClicked() {
        setVerticesCount(verticesCountSpinner.getValue());
        polygonVerticesDialog.close();
    }

    private int setVerticesCount(int vertexesCount) {
        this.verticesCount = vertexesCount;

        return this.verticesCount;
    }

    public int getVerticesCount() {
        return this.verticesCount;
    }

    public static PolygonVerticesController getPolygonVerticesController() {
        try {
            VERTICES_LOADER.load();
        }
        catch (IOException e) {
            MessageBoxHandler.showError(
                    "Polygon vertices window error",
                    "Resources for polygon vertices count window are missing or misplaced."
            );
            return null;
        }

        return VERTICES_LOADER.getController();
    }

    public Scene setPolygonVerticesScene() {
        if (polygonVerticesScene == null) {
            try {
                polygonVerticesScene = new Scene(VERTICES_LOADER.load());
            }
            catch (IOException e) {
                polygonVerticesScene = new Scene(VERTICES_LOADER.getRoot());
            }
        }

        return polygonVerticesScene;
    }

    public void showModal() {
        verticesCountSpinner.getValueFactory().setValue(setVerticesCount(MIN_VERTICES_COUNT));

        polygonVerticesDialog = new Stage();
        polygonVerticesDialog.initModality(Modality.APPLICATION_MODAL);

        polygonVerticesScene = setPolygonVerticesScene();

        polygonVerticesDialog.getIcons().add(App.getMainIcon());
        polygonVerticesDialog.setTitle("Custom Paint: Polygon Vertices Count");
        polygonVerticesDialog.setScene(polygonVerticesScene);

        polygonVerticesDialog.setResizable(false);
        polygonVerticesDialog.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPolygonVerticesScene();
    }
}
