package codename.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import codename.Manager;
import codename.Observer;
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


public class SelectionEspionController implements Observer {

    @FXML
    private VBox redTeam;

    @FXML
    private VBox blueTeam;

    @FXML
    private Button confirmButton;

    @FXML
    private Button randomButton;

    @FXML
    private Button settingsButton;

    private Game game;

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
        System.out.println("initialize SelectionEspionController");
        this.game = Game.getInstance();
        updateTeams();
        game.add_observer(this);

        settingsButton.setOnAction(
            event -> {
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) settingsButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
              } catch (IOException e) {
                e.printStackTrace();
              }
            });

        randomButton.setOnAction(event -> {
            updateTeams();
            game.getRedTeam().clearSpy();
            game.getBlueTeam().clearSpy();
            Random random = new Random();
            int randomRed= random.nextInt(game.getRedTeam().getPlayers().size());
            int randomBlue= random.nextInt(game.getBlueTeam().getPlayers().size());

            for (Player p : game.getRedTeam().getPlayers()) {
                p.setSpymaster(false);
            }

            for (Player p : game.getBlueTeam().getPlayers()) {
                p.setSpymaster(false);
            }

            for (Player player : game.getRedTeam().getPlayers()) {
                if (randomRed == 0) {
                    player.setSpymaster(true);
                    System.out.println(player.getName());
                    break;
                }
                randomRed--;
            }

            for (Player player : game.getBlueTeam().getPlayers()) {
                if (randomBlue== 0) {
                    player.setSpymaster(true);
                    System.out.println(player.getName());
                    break;
                }
                randomBlue--;
            }

            updateTeams();
        });

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

                // Charger et lancer le jeu
                try {
                    // Charger la fenêtre des agents (gameAgent.fxml)
                    FXMLLoader agentsLoader = new FXMLLoader(getClass().getResource("/gameAgent.fxml"));
                    Parent agentsRoot = agentsLoader.load();
                    Scene agentsScene = new Scene(agentsRoot);
                    Stage agentsStage = new Stage();
                    agentsStage.setTitle("CodeName - Agents");
                    agentsStage.setScene(agentsScene);

                    // Charger la fenêtre des espions (gameSpy.fxml)
                    FXMLLoader spiesLoader = new FXMLLoader(getClass().getResource("/gameSpy.fxml"));
                    Parent spiesRoot = spiesLoader.load();
                    Scene spiesScene = new Scene(spiesRoot);
                    Stage spiesStage = new Stage();
                    spiesStage.setTitle("CodeName - Espions");
                    spiesStage.setScene(spiesScene);

                    // Récupérer les GameController des deux scènes

                    Manager controllerManager =
                        new Manager(spiesLoader.getController(), agentsLoader.getController());
                    controllerManager.setUpClueController();
                    controllerManager.setUpGridController();

                    agentsStage.show();
                    spiesStage.show();
                } catch (Exception e) {
                e.printStackTrace(); // Afficher tous les détails de l'erreur
                System.exit(1);
                }
            } else {
                System.out.println("Chaque équipe doit avoir un espion.");
            }
        });
    }
    @Override
    public void update() {
    }
}