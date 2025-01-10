package codename.controller;

import codename.Observer;
import codename.Timer;
import codename.model.Game;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HeaderAgentController implements Observer {
  @FXML private Label labelAgentName;


  private Game game;

  public void initialize() {
    this.game = Game.getInstance();
    this.game.add_observer(this);
    this.game.notify_observator();

  }
  // Démarrer le timer pour un test


  public void updateAgentName() {
    String names = "";
    int count = 0;
    for (Player player : game.whosTurn().getPlayers()) {
      if (!player.isSpymaster()) {
        if (count > 0) {
          names += " - ";
        }
        names += player.getName();
        count++;
      }
    }
    if (count == 0) {
      names = "Personne";
    } else if (count == 1) {
      this.labelAgentName.setText("Agent : " + names);
    } else {
      this.labelAgentName.setText("Agents : " + names);
    }
    System.out.println("Agent de " + game.whosTurn().getColor() + " : " + names);
  }

  @Override
  public void update() {
    this.updateAgentName();
  }
}
