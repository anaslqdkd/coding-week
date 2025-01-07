package codename.controller;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import codename.App;

public class MenuController {
    private App app;

    @FXML
    private Button settingsButton;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void openSettings() {
        try {
            app.showSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private Button playButton;

    @FXML
    private void initialize() {
        playButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nb_joueurs.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

