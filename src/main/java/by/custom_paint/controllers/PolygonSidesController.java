package by.custom_paint.controllers;

import by.custom_paint.App;

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

public class PolygonSidesController implements Initializable {
    @FXML
    private Spinner<Integer> sidesCountSpinner;

    private int sidesCount;
    private final static int MIN_SIDES_COUNT = 3;

    private static final FXMLLoader polygonSidesLoader = new FXMLLoader(App.class.getResource("views/polygon_sides_view.fxml"));
    private Stage polygonSidesDialog;
    private Scene polygonSidesScene = null;

    @FXML
    private void okButtonClicked() {
        setSidesCount(sidesCountSpinner.getValue());
        polygonSidesDialog.close();
    }

    private int setSidesCount(int sidesCount) {
        this.sidesCount = sidesCount;
        return this.sidesCount;
    }

    public int getSidesCount() {
        return this.sidesCount;
    }

    public static FXMLLoader getPolygonSidesLoader() {
        return polygonSidesLoader;
    }

    public Scene setPolygonSidesScene() {
        if (polygonSidesScene == null) {
            try {
                polygonSidesScene = new Scene(polygonSidesLoader.load());
            }
            catch (IOException e) {
                polygonSidesScene = new Scene(polygonSidesLoader.getRoot());
            }
        }

        return polygonSidesScene;
    }

    public void showModal() {
        sidesCountSpinner.getValueFactory().setValue(setSidesCount(MIN_SIDES_COUNT));

        polygonSidesDialog = new Stage();
        polygonSidesDialog.initModality(Modality.APPLICATION_MODAL);

        polygonSidesScene = setPolygonSidesScene();

        polygonSidesDialog.getIcons().add(App.getMainIcon());
        polygonSidesDialog.setTitle("Custom Paint: Polygon Sides Count");
        polygonSidesDialog.setScene(polygonSidesScene);

        polygonSidesDialog.setResizable(false);
        polygonSidesDialog.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPolygonSidesScene();
    }
}
