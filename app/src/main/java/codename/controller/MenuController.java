package codename.controller;

import codename.App;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

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
}
