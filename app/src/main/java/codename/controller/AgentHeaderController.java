package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import codename.model.Player;

public class AgentHeaderController implements Observer {
    @FXML private Label labelAgentName;
    
    private Game game;

    public void initialize() {
        this.game = Game.getInstance();
        this.game.add_observer(this);
        this.game.notify_observator();
    }

    public void updateAgentName() {
        String names = "";
        int count = 0;
        for (Player player : game.whosTurn().getPlayers()) {
            if (!player.isSpymaster()) {
                if (count > 0) {
                    names += " - ";
                }
                names += player.getName() ;
                count++;
            }
        }
        this.labelAgentName.setText("Agent : " + names);
        System.out.println("Agent de " + game.whosTurn().getColor() + " : " + names);
    } 

    @Override
    public void update() {
        this.updateAgentName();
    }
}
