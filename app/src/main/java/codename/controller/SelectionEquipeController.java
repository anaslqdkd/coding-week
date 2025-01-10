package codename.controller;

import codename.Observer;
import codename.model.Game;
import codename.model.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectionEquipeController implements Observer {

  @FXML private VBox leftTeam;

  @FXML private VBox redTeam;

  @FXML private VBox blueTeam;

  @FXML private Button randomButton;

  @FXML private Button confirmButton;

  @FXML private Button settingsButton;

  private Game game;

  private void updateTeams() {

    for (Player player : game.getParameters().getPlayers()) {
      Label playerLabel = new Label(player.getName());
      playerLabel.setStyle("-fx-font-size: 18px;  -fx-text-fill: #212E53;");

      Button redButton = new Button("Rouge");
      redButton.setPrefWidth(140);
      redButton.setPrefHeight(10);

      redButton.setOnAction(
          event -> {
            if (!game.getRedTeam().getPlayers().contains(player)
                && !game.getBlueTeam().getPlayers().contains(player)) {
              game.addPlayerToRedTeam(player);
              game.notify_observator();
            } else if (game.getBlueTeam().getPlayers().contains(player)) {
              game.swicthPlayer(player);
              game.notify_observator();
            }
            game.notify_observator();
          });

      Button blueButton = new Button("Bleu");
      blueButton.setStyle(
          "-fx-background-color: #4A919E; /* Bleu sarcelle */\n"
              + "-fx-text-fill: #212E53;\n"
              + "-fx-border-color: #212E53;");

      blueButton.setOnMouseEntered(
          event -> {
            blueButton.setStyle(
                "-fx-background-color: #BED3C3; /* Bleu clair au survol */\n"
                    + "-fx-text-fill: #212E53;\n"
                    + "-fx-border-color: #212E53;");
          });

      blueButton.setOnMouseExited(
          event -> {
            blueButton.setStyle(
                "-fx-background-color: #4A919E; /* Bleu sarcelle */\n"
                    + "-fx-text-fill: #212E53;\n"
                    + "-fx-border-color: #212E53;");
          });

      blueButton.setPrefWidth(140);
      blueButton.setPrefHeight(30);

      blueButton.setOnAction(
          event -> {
            if (!game.getRedTeam().getPlayers().contains(player)
                && !game.getBlueTeam().getPlayers().contains(player)) {
              game.addPlayerToBlueTeam(player);
              game.notify_observator();
            } else if (game.getRedTeam().getPlayers().contains(player)) {
              game.swicthPlayer(player);
              game.notify_observator();
            }
            game.notify_observator();
          });

      HBox playerBox = new HBox(20, blueButton, playerLabel, redButton);
      playerBox.setAlignment(javafx.geometry.Pos.CENTER);
      leftTeam.getChildren().add(playerBox);
    }
  }

  @FXML
  private void initialize() {
    System.out.println("initialize SelectionEquipeController");
    this.game = Game.getInstance();
    game.getParameters().setCurrentPage("/selection_equipe.fxml");
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

    confirmButton.setOnAction(
        event -> {
          System.out.println(
              "Left team is : "
                  + (leftTeam.getChildren().size()
                      - redTeam.getChildren().size()
                      - blueTeam.getChildren().size()));
          if (leftTeam.getChildren().size()
                      - redTeam.getChildren().size()
                      - blueTeam.getChildren().size()
                  == 0
              && redTeam.getChildren().size() >= 2
              && blueTeam.getChildren().size() >= 2) {

            for (int i = 0; i < redTeam.getChildren().size(); i++) {
              Label playerLabel = (Label) redTeam.getChildren().get(i);
              playerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #212E53;");
              String playerName = playerLabel.getText();
              game.addPlayerToRedTeam(new Player(playerName, false));
            }

            for (int i = 0; i < blueTeam.getChildren().size(); i++) {
              Label playerLabel = (Label) blueTeam.getChildren().get(i);
              playerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #212E53;");

              String playerName = playerLabel.getText();
              game.addPlayerToBlueTeam(new Player(playerName, false));
            }

            for (int i = 0; i < blueTeam.getChildren().size(); i++) {
              Label playerLabel = (Label) blueTeam.getChildren().get(i);
              String playerName = playerLabel.getText();
              game.addPlayerToBlueTeam(new Player(playerName, false));
            }

            System.out.println("Composition de l'équipe rouge :");
            for (Player player : game.getRedTeam().getPlayers()) {
              System.out.println(
                  "Player: " + player.getName() + ", Spymaster: " + player.isSpymaster());
            }

            System.out.println("Composition de l'équipe bleue :");
            for (Player player : game.getBlueTeam().getPlayers()) {
              System.out.println(
                  "Player: " + player.getName() + ", Spymaster: " + player.isSpymaster());
            }

            // Charger et afficher selection_espion.fxml
            try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/selection_espion.fxml"));
              Parent root = loader.load();

              Stage stage = (Stage) confirmButton.getScene().getWindow();
              stage.setScene(new Scene(root));
              stage.show();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });

    randomButton.setOnAction(
        event -> {
          // Effacer les équipes existantes
          updateTeams();
          game.clearTeams();
          // Mélanger la liste des joueurs
          List<Player> shuffledPlayerList = new ArrayList<>(game.getParameters().getPlayers());
          Collections.shuffle(shuffledPlayerList);

          // Déterminer la taille de chaque équipe
          int totalPlayers = shuffledPlayerList.size();
          int redTeamSize =
              totalPlayers / 2 + (totalPlayers % 2); // Si impair, équipe rouge en a un de plus

          // Ajouter les joueurs à l'équipe rouge
          for (int i = 0; i < redTeamSize; i++) {
            Player player = shuffledPlayerList.get(i);
            game.addPlayerToRedTeam(player);
          }

          // Ajouter les joueurs restants à l'équipe bleue
          for (int i = redTeamSize; i < totalPlayers; i++) {
            Player player = shuffledPlayerList.get(i);
            game.addPlayerToBlueTeam(player);
          }
          updateTeamLabel();
        });
  }

  public void updateTeamLabel() {
    leftTeam.getChildren().clear();
    redTeam.getChildren().clear();
    blueTeam.getChildren().clear();

    for (Player player : game.getRedTeam().getPlayers()) {
      Label playerLabel = new Label(player.getName());
      playerLabel.setStyle("-fx-font-size: 18px;");
      redTeam.getChildren().add(playerLabel);
      leftTeam.getChildren().remove(playerLabel);
    }

    for (Player player : game.getBlueTeam().getPlayers()) {
      Label playerLabel = new Label(player.getName());
      playerLabel.setStyle("-fx-font-size: 18px;  -fx-text-fill: #212E53;");
      blueTeam.getChildren().add(playerLabel);
      leftTeam.getChildren().remove(playerLabel);
    }

    for (Player player : game.getParameters().getPlayers()) {
      Label playerLabel = new Label(player.getName());
      playerLabel.setStyle("-fx-font-size: 18px;  -fx-text-fill: #212E53;");
      leftTeam.getChildren().add(playerLabel);

      Button redButton = new Button("Rouge");
      redButton.setPrefWidth(140);
      redButton.setPrefHeight(10);
      redButton.setOnAction(
          event -> {
            if (!game.getRedTeam().getPlayers().contains(player)
                && !game.getBlueTeam().getPlayers().contains(player)) {
              game.addPlayerToRedTeam(player);
              game.notify_observator();
            } else if (game.getBlueTeam().getPlayers().contains(player)) {
              game.swicthPlayer(player);
              game.notify_observator();
            }
            game.notify_observator();
          });

      Button blueButton = new Button("Bleu");
      blueButton.setPrefWidth(140);
      blueButton.setPrefHeight(10);
      blueButton.setStyle(
          "-fx-background-color: #4A919E; /* Bleu sarcelle */\n"
              + "-fx-text-fill: #212E53;\n"
              + "-fx-border-color: #212E53;");

      blueButton.setOnMouseEntered(
          event -> {
            blueButton.setStyle(
                "-fx-background-color: #BED3C3; /* Bleu clair au survol */\n"
                    + "-fx-text-fill: #212E53;\n"
                    + "-fx-border-color: #212E53;");
          });

      blueButton.setOnMouseExited(
          event -> {
            blueButton.setStyle(
                "-fx-background-color: #4A919E; /* Bleu sarcelle */\n"
                    + "-fx-text-fill: #212E53;\n"
                    + "-fx-border-color: #212E53;");
          });
      blueButton.setOnAction(
          event -> {
            if (!game.getRedTeam().getPlayers().contains(player)
                && !game.getBlueTeam().getPlayers().contains(player)) {
              game.addPlayerToBlueTeam(player);
              game.notify_observator();
            }
          });

      HBox playerBox = new HBox(20, blueButton, playerLabel, redButton);
      playerBox.setAlignment(javafx.geometry.Pos.CENTER);
      leftTeam.getChildren().add(playerBox);
    }
  }

  @Override
  public void update() {
    updateTeamLabel();
  }
}

