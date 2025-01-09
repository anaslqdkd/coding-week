package codename.controller;

import codename.Observer;
import codename.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController implements Observer {
  @FXML private ClueAgentController clueAgentController; // Injecté automatiquement via fx:include

  @FXML private ClueSpyController clueSpyController; // Injecté automatiquement via fx:include
  @FXML private GridAgentController gridAgentController; // Injecté automatiquement via fx:include

  @FXML private GridSpyController gridSpyController; // Injecté automatiquement via fx:include

  @FXML private InfoController infoController;
  @FXML private Button startButton;
  @FXML private Button stopButton;
  private int seconds = 0;

  @FXML private Label timerLabel;
  private Timer gameTimer;

  public ClueAgentController getClueAgentController() {
    return clueAgentController;
  }

  public ClueSpyController getClueSpyController() {
    return clueSpyController;
  }

  public GridAgentController getGridAgentController() {
    return gridAgentController;
  }

  public GridSpyController getGridSpyController() {
    return gridSpyController;
  }

  public InfoController getInfoController() {
    return infoController;
  }

  @FXML
  private void initialize() {
    gameTimer =
        new Timer(
            (minutes, seconds) -> {
              timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
              // Other logic that should happen when time updates, such as switching teams
            });
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

  @Override
  public void update() {}
}
