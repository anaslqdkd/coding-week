package codename.controller;

import java.io.IOException;

import codename.Observer;
import codename.model.Game;
import codename.model.WordList;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;

public class GameController implements Observer {
  @FXML private ClueAgentController clueAgentController; // Injecté automatiquement via fx:include

  @FXML private ClueSpyController clueSpyController; // Injecté automatiquement via fx:include
  @FXML private GridAgentController gridAgentController; // Injecté automatiquement via fx:include

  @FXML private GridSpyController gridSpyController; // Injecté automatiquement via fx:include

  @FXML private InfoController infoController;

  public ClueAgentController getClueAgentController() {
    return clueAgentController;
  }

  public ClueSpyController getClueSpyController() {
    return clueSpyController;
  }

  public GridAgentController getGridAgentController() {
    return gridAgentController;
  }

  public GridSpyController getGridSpyController() {
    return gridSpyController;
  }

  public InfoController getInfoController() {
    return infoController;
  }

  @FXML
  private void initialize() {
    try {
      List<String> words = WordList.getWordList(25, "database.txt");

      // Initialize the singleton game instance

      Game game = Game.getInstance(words);
      game.add_observer(this);

    }catch (IOException e) {
            e.printStackTrace();
        }
  }

  @Override
  public void update() {}
}
