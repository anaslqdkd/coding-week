package codename.controller;

import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClueAgentController {
  private Game game;

  private ClueSpyController clueSpyController;

  @FXML private Label clueLabel;

  @FXML private Button endTurnButton;

  @FXML
  public void initialize() {
    this.game = Game.getInstance();
    // Initialiser le label
    clueLabel.setText("En attente...");

    endTurnButton.setOnAction(
        event -> {
          clueLabel.setText("En attente..."); // Réinitialiser le label
          if (clueSpyController != null) {
            clueSpyController.reset(); // Informer ClueSpyController
          }
        });
  }

  public void setClueSpyController(ClueSpyController clueSpyController) {
    this.clueSpyController = clueSpyController;
  }

  public String getClueLabel() {
    return this.clueLabel.getText();
  }

  public void getClue() {
    String text = game.getClue().getText();
    Integer number = game.getClue().getNumber();

    if (text != null && !text.isEmpty() && number != null) {
      clueLabel.setText(text + " - " + number);
    } else {
      clueLabel.setText("En attente...");
    }
  }
}
