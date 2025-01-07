package codename.controller;

import codename.model.Clue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClueAgentController {
    private Clue clue;

    private ClueSpyController clueSpyController; 

    @FXML
    private Label clueLabel;

    @FXML
    private Button endTurnButton;
    
    @FXML
    public void initialize() {
        // Initialiser le label
        clueLabel.setText("En attente...");

        endTurnButton.setOnAction(event -> {
            clueLabel.setText("En attente..."); // Réinitialiser le label
            if (clueSpyController != null) {
                clueSpyController.reset(); // Informer ClueSpyController
            }
        });
    }

    public void setClueSpyController(ClueSpyController clueSpyController) {
        this.clueSpyController = clueSpyController;
    }

    public void getClue(Clue clue) {
        String text = clue.getText();
        Integer number = clue.getNumber();

        if (text != null && !text.isEmpty() && number != null) {
            clueLabel.setText("Indice donné : " + text + " - " + number);
        } else {
            clueLabel.setText("En attente...");
        }
    }
}
