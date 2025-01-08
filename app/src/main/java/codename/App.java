package codename;

import codename.controller.MenuController;
import codename.controller.SettingsController;
import codename.model.Game;
import codename.model.WordList;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private Stage primaryStage;

  private Game game;

  private List<String> words =
      Arrays.asList(
          "Avion",
          "Banane",
          "Château",
          "Forêt",
          "Fusée",
          "Pyramide",
          "Robot",
          "Glace",
          "Océan",
          "Marteau",
          "Étoile",
          "Camion",
          "Plume",
          "Dragon",
          "Temple",
          "Sirène",
          "Diamant",
          "Ciel",
          "Désert",
          "Lune",
          "Soleil",
          "Bateau",
          "Volcan",
          "Feuille",
          "Requin");

  @Override
  public void start(Stage primaryStage) throws Exception {
    List<String> words = WordList.getWordList(25, "database.txt");
    this.game = Game.getInstance(words);
    ; // Create a new game

    URL fxmlURL = getClass().getResource("/menu.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find menu.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void showSettings() throws Exception {
    URL fxmlURL = getClass().getResource("/settings.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find settings.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(fxmlURL);
    Parent root = loader.load();

    // Pass the application reference to the controller
    SettingsController controller = loader.getController();
    controller.setApp(this);

    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName - Paramètres");
    primaryStage.setScene(scene);
  }

  public void showMenu() throws Exception {
    URL fxmlURL = getClass().getResource("/menu.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find menu.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(fxmlURL);
    Parent root = loader.load();

    // Pass the application reference to the controller
    MenuController controller = loader.getController();
    controller.setApp(this);

    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName - Menu");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
