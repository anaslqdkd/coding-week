package codename;


import codename.controller.ClueAgentController;
import codename.controller.ClueSpyController;
import codename.controller.GameController;
import codename.model.Game;


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

        clueSpyController.setClueAgentController(clueAgentController);
        clueAgentController.setClueSpyController(clueSpyController);
    }
}