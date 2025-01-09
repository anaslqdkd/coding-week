package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import codename.model.Player;

public class ClueHeaderController implements Observer {
    @FXML private Label labelSpyName;
    
    private Game game;

    public void initialize() {
        this.game = Game.getInstance();
        this.game.add_observer(this);
        this.game.notify_observator();
    }

    public void updateSpyName() {
        String spyMasterName = "";
        for (Player player : game.whosTurn().getPlayers()) {
            if (player.isSpymaster()) {
                spyMasterName = player.getName();
                this.labelSpyName.setText("Maître-Espion :" + player.getName());
            }
            break;
        }
        this.labelSpyName.setText("Maître-Espion : " + spyMasterName);
        System.out.println("Espion de " + game.whosTurn() + " :" + spyMasterName);
    } 


    @Override
    public void update() {
        this.updateSpyName();
    }
}
