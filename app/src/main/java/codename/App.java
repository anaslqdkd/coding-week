package codename;

import codename.controller.*;
import codename.controller.GameController;
import codename.model.Game;

import java.util.Arrays;
import java.util.List;

import codename.model.WordList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private Game game;

  @Override
  public void start(Stage primaryStage) {
    try {
      List<String> words =
          Arrays.asList(
              "Anchor",
              "Beacon",
              "Castle",
              "Desert",
              "Eclipse",
              "Falcon",
              "Glacier",
              "Harbor",
              "Ivory",
              "Jaguar",
              "Kingdom",
              "Lantern",
              "Mirage",
              "Nebula",
              "Oasis",
              "Prism",
              "Quasar",
              "Raven",
              "Summit",
              "Twilight",
              "Umbra",
              "Vortex",
              "Warden",
              "Xenon",
              "Zephyr");
      // Game game = new Game(words);
      this.game = Game.getInstance(words);

      // Charger la fenêtre des agents (gameAgent.fxml)
      FXMLLoader agentsLoader = new FXMLLoader(getClass().getResource("/gameAgent.fxml"));
      Parent agentsRoot = agentsLoader.load();
      Scene agentsScene = new Scene(agentsRoot);
      Stage agentsStage = new Stage();
      agentsStage.setTitle("CodeName - Agents");
      agentsStage.setScene(agentsScene);

      // Charger la fenêtre des espions (gameSpy.fxml)
      FXMLLoader spiesLoader = new FXMLLoader(getClass().getResource("/gameSpy.fxml"));
      Parent spiesRoot = spiesLoader.load();
      Scene spiesScene = new Scene(spiesRoot);
      Stage spiesStage = new Stage();
      spiesStage.setTitle("CodeName - Espions");
      spiesStage.setScene(spiesScene);

      // Récupérer les GameController des deux scènes
      GameController gameAgentController = agentsLoader.getController();
      GameController gameSpyController = spiesLoader.getController();

      // Accéder aux sous-contrôleurs ClueAgentController et ClueSpyController
      ClueAgentController clueAgentController = gameAgentController.getClueAgentController();
      ClueSpyController clueSpyController = gameSpyController.getClueSpyController();

      clueSpyController.setClueAgentController(clueAgentController);
      clueAgentController.setClueSpyController(clueSpyController);

      agentsStage.show();
      spiesStage.show();
    } catch (Exception e) {
      e.printStackTrace(); // Afficher tous les détails de l'erreur
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
