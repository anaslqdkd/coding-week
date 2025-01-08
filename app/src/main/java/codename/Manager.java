package codename;

import codename.controller.AgentGridController;
import codename.controller.ClueAgentController;
import codename.controller.ClueSpyController;
import codename.controller.GameController;

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

    AgentGridController agentGridController = gameAgentController.getAgentGridController();
    agentGridController.setClueAgentController(clueAgentController);
    clueSpyController.setClueAgentController(clueAgentController);
    clueAgentController.setClueSpyController(clueSpyController);
  }

  // TODO: à mettre les controlleurs de grille dans Manager
  // public void setUpGridController() {
  //  AgentGridController agentGridController = gameAgentController.getAgentGridController();
  //  SpyGridController spyGridController = gameAgentController.getSpyGridController();
  // }
}

