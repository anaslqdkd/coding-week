package codename.controller;

import codename.Observer;
import codename.Timer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import codename.model.Player;

public class HeaderSpyController implements Observer {
    @FXML
    private Label labelSpyName;
    @FXML
    private Label timerLabel;


    private Game game;

    public void initialize() {
        this.game = Game.getInstance();
        this.game.add_observer(this);
        this.game.notify_observator();
        game.initializeTimer(60, new Timer.TimerCallback() {
            @Override
            public void onTimeUpdate(int minutes, int seconds) {
                // Met à jour le Label avec le temps restant
                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onTimeUp() {
                // Logique quand le temps est écoulé
                
                timerLabel.setText("Temps écoulé !");
                System.out.println("Fin du temps ! Passez le tour !");
                game.notify_observator();

            }
        });
        game.getTimer().start();
    }

    public void updateSpyName() {
        String spyMasterName = "";
        for (Player player : game.whosTurn().getPlayers()) {
            if (player.isSpymaster()) {
                spyMasterName = player.getName();
                this.labelSpyName.setText("Maître-Espion :" + player.getName());
                break;
            }
        }
        this.labelSpyName.setText("Maître-Espion : " + spyMasterName);
        System.out.println("Espion de " + game.whosTurn().getColor() + " :" + spyMasterName);
    }



    @Override
    public void update() {
        this.updateSpyName();
    }
}
