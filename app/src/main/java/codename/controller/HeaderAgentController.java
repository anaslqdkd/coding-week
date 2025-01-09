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
    gameTimer =
        new Timer(
            (minutes, seconds) -> {
              timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
              // Other logic that should happen when time updates, such as switching teams
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
    this.labelAgentName.setText("Agent : " + names);
    System.out.println("Agent de " + game.whosTurn().getColor() + " : " + names);
  }

  @Override
  public void update() {
    this.updateAgentName();
  }

  public void startGameTimer() {
    gameTimer.start(); // Start the timer when the game starts
  }

  public void stopGameTimer() {
    gameTimer.stop(); // Stop the timer when the game ends
  }

  public void resetGameTimer() {
    gameTimer.reset(); // Reset the timer if needed
  }

  private void updateTimer() {
    seconds++;
    int minutes = seconds / 60; // Calculate minutes
    int remainingSeconds = seconds % 60; // Calculate remaining seconds

    // Update the label with the formatted time (minutes:seconds)
    timerLabel.setText(String.format("%02d:%02d", minutes, remainingSeconds));

    System.out.println("Current Time: " + String.format("%02d:%02d", minutes, remainingSeconds));
  }

  @FXML
  private void startButtonClicked() {
    startGameTimer();
  }

  @FXML
  private void stopButtonClicked() {
    stopGameTimer();
  }
}
