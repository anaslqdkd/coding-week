package codename.controller;

import java.io.IOException;

import codename.Observer;
import codename.model.Game;
import codename.model.Parameters;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NbJoueursController implements Observer {

  @FXML private VBox dynamicPlayers;

  @FXML private Button addPlayerButton;

  @FXML private Button removePlayerButton;

  @FXML private Button confirmButton;

  @FXML private Button settingsButton;

  private Game game;
  private Parameters parameters; // Déclarez parameters ici

  private void initializeParameters() {
    parameters = game.getParameters(); // Initialisez parameters ici
    parameters.setNumberOfPlayers(2); // Initialiser à 2 joueurs par défaut
    updateDynamicPlayers();
  }

  @FXML
  public void initialize() {
    System.out.println("initialize NbJoueursController");
    this.game = Game.getInstance();
    initializeParameters();
    game.getParameters().setCurrentPage("/Nb_joueurs.fxml");
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

    addPlayerButton.setOnAction(
        event -> {
          if (parameters.getNumberOfPlayers() < 8) {
            parameters.setNumberOfPlayers(parameters.getNumberOfPlayers() + 1);
            updateDynamicPlayers();
          } else {
            System.out.println("Nombre de joueurs max atteint");
          }
        });

    removePlayerButton.setOnAction(
        event -> {
          if (parameters.getNumberOfPlayers() > 2) {
            parameters.setNumberOfPlayers(parameters.getNumberOfPlayers() - 1);
            updateDynamicPlayers();
          } else {
            System.out.println("Nombre de joueurs min atteint");
          }
        });

    confirmButton.setOnAction(
        event -> {
          boolean allFieldsFilled = true;
          parameters.getPlayers().clear(); // Clear existing players

          for (int i = 0; i < dynamicPlayers.getChildren().size(); i++) {
            HBox playerBox = (HBox) dynamicPlayers.getChildren().get(i);
            TextField playerField = (TextField) playerBox.getChildren().get(1);
            String playerName = playerField.getText();

            if (playerName.isEmpty()) {
              allFieldsFilled = false;
              break;
            } else {
              parameters.addPlayer(new Player(playerName, false));
            }
          }

          if (allFieldsFilled) {
            System.out.println("Nombre de joueurs confirmés : " + parameters.getNumberOfPlayers());
            for (Player player : parameters.getPlayers()) {
              System.out.println(
                  "Player: " + player.getName() + ", Spymaster: " + player.isSpymaster());
            }
            // Charger et afficher selection_equipe.fxml
            try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/selection_equipe.fxml"));
              Parent root = loader.load();

              Stage stage = (Stage) confirmButton.getScene().getWindow();
              stage.setScene(new Scene(root));
              stage.show();
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            System.out.println("Tous les champs doivent être remplis.");
          }
        });
  }

  private void updateDynamicPlayers() {
    // Sauvegarder les noms des joueurs existants
    String[] playerNames = new String[parameters.getNumberOfPlayers()];
    for (int i = 0; i < dynamicPlayers.getChildren().size(); i++) {
        HBox playerBox = (HBox) dynamicPlayers.getChildren().get(i);
        TextField playerField = (TextField) playerBox.getChildren().get(1);
        playerNames[i] = playerField.getText(); // Sauvegarde du texte
    }

    // Effacer les anciens champs
    dynamicPlayers.getChildren().clear();

    // Reconstruire les champs avec les noms existants (si présents)
    for (int i = 0; i < parameters.getNumberOfPlayers(); i++) {
        HBox playerBox = new HBox(20);
        playerBox.setAlignment(javafx.geometry.Pos.CENTER);

        Label playerLabel = new Label("Joueur " + (i + 1) + ":");
        playerLabel.setStyle("-fx-font-size: 18px;");

        TextField playerField = new TextField();
        playerField.setPromptText("Entrez le pseudo");
        
        // Rétablir le texte sauvegardé (s'il existe)
        if (i < playerNames.length && playerNames[i] != null) {
            playerField.setText(playerNames[i]);
        }

        playerBox.getChildren().addAll(playerLabel, playerField);
        dynamicPlayers.getChildren().add(playerBox);
    }
}

  @Override
  public void update() {}
}

