package codename.controller;

import codename.Observer;
import codename.model.Game;
import codename.model.WordList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class GameController implements Observer {
  @FXML private ClueAgentController clueAgentController; // Injecté automatiquement via fx:include

  @FXML private ClueSpyController clueSpyController; // Injecté automatiquement via fx:include
  @FXML private AgentGridController agentGridController; // Injecté automatiquement via fx:include

  @FXML private SpyGridController spyGridController; // Injecté automatiquement via fx:include

  @FXML private InfoController infoController;

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

  public InfoController getInfoController() {
    return infoController;
  }

  @FXML
  private void initialize() {
    try {
      System.out.println("GameController initialized");
      List<String> words = WordList.getWordList(25,"database.txt");
    
      // Initialize the singleton game instance
    

      Game game = Game.getInstance(words);
      game.add_observer(this);

      FXMLLoader gridAgentLoader = new FXMLLoader(getClass().getResource("/gridAgent.fxml"));
      GridPane gridPaneAgent = gridAgentLoader.load();
      agentGridController = gridAgentLoader.getController();

      FXMLLoader gridSpyLoader = new FXMLLoader(getClass().getResource("/gridSpy.fxml"));
      GridPane gridPaneSpy = gridSpyLoader.load();
      FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("/infoAgent.fxml"));
      infoController = infoLoader.getController();
      spyGridController = gridSpyLoader.getController();

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

  // }

  @Override
  public void update() {
    agentGridController.update();
    spyGridController.update();
  }
}
