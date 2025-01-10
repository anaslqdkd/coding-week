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

  @FXML private Label timerLabel;
  @FXML private Button startButton;
  private int seconds = 0;
  private Timer gameTimer;

  private Game game;

  public void initialize() {
    this.game = Game.getInstance();
    this.game.add_observer(this);
    this.game.notify_observator();
    int initialTimeInSeconds = 10;
    gameTimer =
        new Timer(
            initialTimeInSeconds,
            new Timer.TimerCallback() {
              @Override
              public void onTimeUpdate(int minutes, int seconds) {
                // Update the UI with the formatted time
                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
              }

              @Override
              public void onTimeUp() {
                // Time's up logic (e.g., switch teams, game over, etc.)
                System.out.println("Time's up! Switching teams...");
                // Add your logic here (e.g., switching teams)
              }
            });
  }

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

  @FXML
  private void startTimer() {
    gameTimer.start(); // Start the countdown timer
  }

  @FXML
  private void stopTimer() {
    gameTimer.stop(); // Stop the countdown timer
  }

  @FXML
  private void resetTimer() {
    gameTimer.reset(); // Reset the timer to initial time
  }
}
