package codename.controller;

import codename.Observer;
import codename.model.Team;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClueAgentController implements Observer {
  private Game game;

  private ClueSpyController clueSpyController;

  @FXML
  private Label clueLabel;

  @FXML
  private Button endTurnButton;

  @FXML
  public void initialize() {
    this.game = Game.getInstance();
    // Initialiser le label
    this.game.add_observer(this);
    clueLabel.setText("En attente...");
    endTurnButton.setDisable(true);

    endTurnButton.setOnAction(
        event -> {
          clueLabel.setText("En attente...");
          clueSpyController.reset();
          this.game.switchTurn();
          this.game.notify_observator();
        });
  }

  public void switchButton() {
    if (game.isAgentTurn()) {
      this.endTurnButton.setDisable(false);
      System.out.println("End turn button enabled");
    } else {
      this.endTurnButton.setDisable(true);
      System.out.println("End turn button disabled");
    }
  }

  public void switchTeam() {
    if (game.getLastTurn() != game.whosTurn()) {
      switchButton();
    }
  }

  public void setClueSpyController(ClueSpyController clueSpyController) {
    this.clueSpyController = clueSpyController;
  }

  public String getClueLabel() {
    return this.clueLabel.getText();
  }

  public void getClue() {
    if (game.getClue() != null) {
      String text = game.getClue().getText();
      Integer number = game.getClue().getNumber();

      if (text != null && number != null) {
        clueLabel.setText(text + " - " + number);
      } else {
        clueLabel.setText("En attente...");
      }
    } else {
      clueLabel.setText("En attente...");
    }
  }

  public void disableWin() {
    if (game.isGameOver()) {
      endTurnButton.setDisable(true);
    }
  }

  @Override
  public void update() {
    switchTeam();
    getClue();
    System.out.println("agentTurn" + game.isAgentTurn());
    System.out.println(game.getClue());
    disableWin();
  }
}
