package codename.controller;

import codename.Observer;
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
  private void initialize() {}

  @Override
  public void update() {}
}
