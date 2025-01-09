/*
Pour fonctionner, a besoin de :

SettingsController controller = loader.getController();
        GridController grid_controller = new GridController();
        grid_controller.getWordList(25);

*/

package codename.controller;

import codename.App;
import codename.Observer;
import codename.model.Game;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsController implements Observer {

  @FXML private Slider gridLinesSlider;

  @FXML private Button cancelSettingsButton;

  @FXML private Button saveSettingsButton;

  @FXML private Slider gridColumnsSlider;

  @FXML private CheckBox darkModeCheckbox;

  @FXML private App app;

  @FXML private Button backButton;
  private Game game;

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

  // @FXML
  // public void saveSettings() {
  //    try {
  //        System.out.println("Chargement de menu.fxml");
  //        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
  //        Parent root = loader.load();
  //
  //        // Obtenir la scène actuelle
  //        Stage stage = (Stage) saveSettingsButton.getScene().getWindow();
  //        stage.setScene(new Scene(root));
  //        stage.setTitle("Menu");
  //        System.out.println("Navigation vers le menu réussie.");
  //    } catch (Exception e) {
  //        e.printStackTrace();
  //        System.out.println("Erreur lors du chargement de la page menu.fxml");
  //    }
  //
  //
  // }
  @FXML
  private void initialize() {
    System.out.println("Settings controller initialised");
    this.game = Game.getInstance();
    System.out.println("Initial grid lines: " + gridLinesSlider.getValue());
    System.out.println("Initial grid columns: " + gridColumnsSlider.getValue());
    game.add_observer(this);
    gridColumnsSlider
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              System.out.println("Grid columns updated: " + newValue.intValue());
            });
    saveSettingsButton.setOnAction(
        event -> {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
            Parent root = loader.load();
            int rows = (int) gridLinesSlider.getValue();
            int columns = (int) gridColumnsSlider.getValue();
            System.out.println("(((((((())))))))" + rows + columns);

            game.setGridSize(rows, columns);
            System.out.println("in settings controller" + rows + columns);
            Stage stage = (Stage) saveSettingsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            game.notify_observator();
            stage.show();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  public void update() {
    System.out.println("Game update in settings controller");
  }
}
