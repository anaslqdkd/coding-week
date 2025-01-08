package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameTestController implements Observer {

    @FXML
    private Label gameStatusLabel;

    private Game game;

    public void setGame(Game game) {
        this.game = game;
        updateGameStatus();
    }

    private void updateGameStatus() {
        gameStatusLabel.setText("Le jeu a commencé !");
    }

    @Override
    public void update() {}
}
