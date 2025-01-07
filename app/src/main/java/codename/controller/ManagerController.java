package codename.controller;


import codename.model.Game;


public class ManagerController {
    private GameController gameSpyController;
    private GameController gameAgentController;

    public ManagerController(GameController gameSpyController, GameController gameAgentController) {
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