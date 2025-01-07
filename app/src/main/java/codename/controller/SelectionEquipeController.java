package codename.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codename.model.Game;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectionEquipeController {

    @FXML
    private VBox leftTeam;

    @FXML
    private VBox redTeam;

    @FXML
    private VBox blueTeam;

    @FXML
    private Button randomButton;

    private Game game;

public void initialize() {
    randomButton.setOnAction(event -> {
        // Effacer les équipes existantes
        updateTeams();
        // Mélanger la liste des joueurs
        List<Player> shuffledPlayerList = new ArrayList<>(game.getParameters().getPlayers());
        Collections.shuffle(shuffledPlayerList);

        // Déterminer la taille de chaque équipe
        int totalPlayers = shuffledPlayerList.size();
        int redTeamSize = totalPlayers / 2 + (totalPlayers % 2); // Si impair, équipe rouge en a un de plus
        int blueTeamSize = totalPlayers / 2;

        // Ajouter les joueurs à l'équipe rouge
        for (int i = 0; i < redTeamSize; i++) {
            Player player = shuffledPlayerList.get(i);
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-font-size: 18px;");
            moveToTeam(playerLabel, redTeam);
        }

        // Ajouter les joueurs restants à l'équipe bleue
        for (int i = redTeamSize; i < totalPlayers; i++) {
            Player player = shuffledPlayerList.get(i);
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-font-size: 18px;");
            moveToTeam(playerLabel, blueTeam);
        }
    });

    }

    public void setGame(Game game) {
        this.game = game;
        updateTeams();
    }

    private void updateTeams() {
        leftTeam.getChildren().clear();
        redTeam.getChildren().clear();
        blueTeam.getChildren().clear();

        for (Player player : game.getParameters().getPlayers()) {
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-font-size: 18px;");

            Button redButton = new Button("Rouge");
            redButton.setPrefWidth(100);
            redButton.setOnAction(event -> {
                if (playerLabel.getParent() != redTeam) {
                    moveToTeam(playerLabel, redTeam);
                }
            });

            Button blueButton = new Button("Bleu");
            blueButton.setPrefWidth(100);
            blueButton.setOnAction(event -> {
                if (playerLabel.getParent() != blueTeam) {
                    moveToTeam(playerLabel, blueTeam);
                }
            });

            HBox playerBox = new HBox(20, blueButton, playerLabel, redButton);
            playerBox.setAlignment(javafx.geometry.Pos.CENTER);
            leftTeam.getChildren().add(playerBox);
        }
    }
    
    private void moveToTeam(Label playerLabel, VBox targetTeam) {
        // Supprimer le joueur des autres équipes
        leftTeam.getChildren().remove(playerLabel);
        redTeam.getChildren().remove(playerLabel);
        blueTeam.getChildren().remove(playerLabel);
    
        // Ajouter le joueur à l'équipe cible
        targetTeam.getChildren().add(playerLabel);
    }
    
}