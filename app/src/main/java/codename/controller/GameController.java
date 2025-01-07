package codename.controller;

import codename.Observer;
import codename.model.Game;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class GameController implements Observer {
  private Game game;

  @FXML private ClueAgentController clueAgentController; // Injecté automatiquement via fx:include

  @FXML private ClueSpyController clueSpyController; // Injecté automatiquement via fx:include
  @FXML private AgentGridController agentGridController; // Injecté automatiquement via fx:include
  @FXML private SpyGridController spyGridController; // Injecté automatiquement via fx:include

  public ClueAgentController getClueAgentController() {
    return clueAgentController;
  }

  public ClueSpyController getClueSpyController() {
    return clueSpyController;
  }

  public AgentGridController getAgentGridController() {
    return agentGridController;
  }

  public SpyGridController getSpyGridController() {
    return spyGridController;
  }

  @FXML
  private void initialize() {
    try {
      System.out.println("game controller initialised");
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
      FXMLLoader gridAgentLoader = new FXMLLoader(getClass().getResource("/gridAgent.fxml"));
      GridPane gridPaneAgent = gridAgentLoader.load();
      agentGridController = gridAgentLoader.getController();

      FXMLLoader gridSpyLoader = new FXMLLoader(getClass().getResource("/gridSpy.fxml"));
      GridPane gridPaneSpy = gridSpyLoader.load();
      spyGridController = gridSpyLoader.getController();

      System.out.println(words.size());
      game = new Game(words);
      if (game != null) {
        System.out.println("game is not null");
        agentGridController.setGame(game);
        spyGridController.setGame(game);
      } else {
        System.out.println("game is null");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    // try {
    //  FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("/grid.fxml"));
    //  GridPane grid = gridLoader.load();
    //  gridController = gridLoader.getController();
    //
    // } catch (IOException e) {
    //  e.printStackTrace();
    // }
    //       try {
    //       game.revealCard(cardIndex);
    //   } catch (IllegalArgumentException e) {
    //       System.out.println("Erreur : " + e.getMessage());
    //       return;
    //   }

    //   // Décrémenter les clics restants
    //   game.decrementClicksRemaining();

    //   // Vérifier si la partie est terminée
    //   if (game.isGameOver()) {
    //       System.out.println("L'équipe " + game.getCurrentTurn().getColor() + " a gagné !");
    //       return;
    //   }

    //   // Si clics épuisés, changer de tour
    //   if (game.getClicksRemaining() == 0) {
    //       System.out.println("Clics maximum atteints. Tour terminé.");
    //       game.switchTurn();
    //   }
    // }
  }

  public Game getGame() {
    return game;
  }

  @Override
  public void update() {}
}
