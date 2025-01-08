package codename;

import codename.controller.ClueAgentController;
import codename.controller.ClueSpyController;
import codename.controller.GameController;
import codename.controller.GridAgentController;
import codename.controller.GridSpyController;

public class Manager {
  private GameController gameSpyController;
  private GameController gameAgentController;

  public Manager(GameController gameSpyController, GameController gameAgentController) {
    this.gameSpyController = gameSpyController;
    this.gameAgentController = gameAgentController;
  }

  public void setUpClueController() {
    ClueAgentController clueAgentController = gameAgentController.getClueAgentController();
    ClueSpyController clueSpyController = gameSpyController.getClueSpyController();
    GridAgentController gridAgentController = gameAgentController.getGridAgentController();

    clueSpyController.setClueAgentController(clueAgentController);
    clueSpyController.setGridAgentController(gridAgentController);
    clueAgentController.setClueSpyController(clueSpyController);
  }

  public void setUpGridController() {
    ClueAgentController clueAgentController = gameAgentController.getClueAgentController();
    GridAgentController gridAgentController = gameAgentController.getGridAgentController();
    GridSpyController gridSpyController = gameSpyController.getGridSpyController();
    gridSpyController.setGridAgentController(gridAgentController);
    gridAgentController.setGridSpyController(gridSpyController);
    gridAgentController.setClueAgentController(clueAgentController);
  }
}
