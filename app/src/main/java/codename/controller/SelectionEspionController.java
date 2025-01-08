package codename.controller;

import java.io.IOException;
import java.util.List;

import codename.model.Game;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectionEspionController {

    @FXML
    private VBox redTeam;

    @FXML
    private VBox blueTeam;

    @FXML
    private Button confirmButton;

    private Game game;

    public void setGame(Game game) {
        this.game = game;
        updateTeams();
    }

    private void updateTeams() {
        redTeam.getChildren().clear();
        blueTeam.getChildren().clear();

        for (Player player : game.getRedTeam().getPlayers()) {
            addPlayerToTeam(player, redTeam, game.getRedTeam().getPlayers());
        }

        for (Player player : game.getBlueTeam().getPlayers()) {
            addPlayerToTeam(player, blueTeam, game.getBlueTeam().getPlayers());
        }
    }

    private void addPlayerToTeam(Player player, VBox teamBox, List<Player> teamPlayers) {
        Label playerLabel = new Label(player.getName());
        playerLabel.setStyle("-fx-font-size: 18px;");
        if (player.isSpymaster()) {
            playerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        }

        Button spymasterButton = new Button("Sélectionner Espion");
        spymasterButton.setOnAction(event -> {
            for (Player p : teamPlayers) {
                p.setSpymaster(false);
            }
            player.setSpymaster(true);
            updateTeams();
        });

        HBox playerBox = new HBox(10, playerLabel, spymasterButton);
        playerBox.setAlignment(javafx.geometry.Pos.CENTER);
        teamBox.getChildren().add(playerBox);
    }

    @FXML
    private void initialize() {
        confirmButton.setOnAction(event -> {
            boolean redTeamHasSpymaster = false;
            boolean blueTeamHasSpymaster = false;
            Player redSpymaster = null;
            Player blueSpymaster = null;

            for (Player player : game.getRedTeam().getPlayers()) {
                if (player.isSpymaster()) {
                    redTeamHasSpymaster = true;
                    redSpymaster = player;
                    break;
                }
            }

            for (Player player : game.getBlueTeam().getPlayers()) {
                if (player.isSpymaster()) {
                    blueTeamHasSpymaster = true;
                    blueSpymaster = player;
                    break;
                }
            }

            if (redTeamHasSpymaster && blueTeamHasSpymaster) {
                // Afficher les espions dans le terminal
                System.out.println("Espion de l'équipe rouge : " + redSpymaster.getName());
                System.out.println("Espion de l'équipe bleue : " + blueSpymaster.getName());

                // Charger et afficher gameTest.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameTest.fxml"));
                    Parent root = loader.load();

                    GameTestController controller = loader.getController();
                    controller.setGame(game);

                    Stage stage = (Stage) confirmButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Chaque équipe doit avoir un espion.");
            }
        });
    }
}