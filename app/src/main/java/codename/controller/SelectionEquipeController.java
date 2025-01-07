package codename.controller;

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

    private Game game;

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
                redTeam.getChildren().add(playerLabel);
                leftTeam.getChildren().remove(playerLabel);
            });

            Button blueButton = new Button("Bleu");
            blueButton.setPrefWidth(100);
            blueButton.setOnAction(event -> {
                blueTeam.getChildren().add(playerLabel);
                leftTeam.getChildren().remove(playerLabel);
            });

            HBox playerBox = new HBox(20, blueButton, playerLabel, redButton);
            playerBox.setAlignment(javafx.geometry.Pos.CENTER);
            leftTeam.getChildren().add(playerBox);
        }
    }
}