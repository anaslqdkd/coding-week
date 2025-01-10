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

  private Team lastTeam;

  @FXML private Label clueLabel;

  @FXML private Button endTurnButton;



  @FXML
  public void initialize() {
    this.game = Game.getInstance();
    // Initialiser le label
    this.game.add_observer(this);
    clueLabel.setText("En attente...");
    endTurnButton.setDisable(true);
    this.lastTeam = game.whosTurn();

    endTurnButton.setOnAction(
        event -> {
          clueLabel.setText("En attente..."); // Réinitialiser le label
          if (clueSpyController != null) {
            clueSpyController.reset();
            endTurnButton.setDisable(true);
            this.game.switchTurn();
            switchButton();
            this.game.notify_observator();
          }
        });
  }


  public void switchButton() {
    if (this.endTurnButton.isDisable()) {
      this.endTurnButton.setDisable(false);
    } else {
      this.endTurnButton.setDisable(true);
    }
  }

  public void switchTeam() {
    if (this.lastTeam != game.whosTurn()) {
      this.lastTeam = game.whosTurn();
      switchButton();
      this.clueSpyController.reset();
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
    disableWin();
  }
}
