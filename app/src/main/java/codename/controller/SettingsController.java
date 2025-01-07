/*
Pour fonctionner, a besoin de :

SettingsController controller = loader.getController();
        GridController grid_controller = new GridController();
        grid_controller.getWordList(25);

*/

package codename.controller;

import codename.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;



public class SettingsController {

    @FXML
    private Slider gridLinesSlider;

    @FXML
    private Button cancelSettingsButton;

    @FXML
    private Button saveSettingsButton;

    @FXML
    private Slider gridColumnsSlider;

    @FXML
    private CheckBox darkModeCheckbox;

    @FXML
    private App app;

    @FXML
    private Button backButton;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void goBackToMenu() {
        try {
            app.showMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveSettings() {
        try {
            System.out.println("Chargement de menu.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
            Parent root = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) saveSettingsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");
            System.out.println("Navigation vers le menu réussie.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page menu.fxml");
        }


    }
}
