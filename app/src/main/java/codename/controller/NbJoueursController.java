package codename.controller;

import java.io.IOException;

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

public class NbJoueursController {

    @FXML
    private VBox dynamicPlayers;

    @FXML
    private Button addPlayerButton;

    @FXML
    private Button removePlayerButton;

    @FXML
    private Button confirmButton;

    private Parameters parameters;

    @FXML
    public void initialize() {
        parameters = new Parameters();
        parameters.setNumberOfPlayers(2); // Initialiser à 2 joueurs par défaut
        updateDynamicPlayers();

        addPlayerButton.setOnAction(event -> {
            if (parameters.getNumberOfPlayers() < 8) {
                parameters.setNumberOfPlayers(parameters.getNumberOfPlayers() + 1);
                updateDynamicPlayers();
            } else {
                System.out.println("Nombre de joueurs max atteint");
            }
        });

        removePlayerButton.setOnAction(event -> {
            if (parameters.getNumberOfPlayers() > 2) {
                parameters.setNumberOfPlayers(parameters.getNumberOfPlayers() - 1);
                updateDynamicPlayers();
            } else {
                System.out.println("Nombre de joueurs min atteint");
            }
        });

        confirmButton.setOnAction(event -> {
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
                    System.out.println("Player: " + player.getName() + ", Spymaster: " + player.isSpymaster());
                }
                // Charger et afficher selection_mode.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/selection_mode.fxml"));
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
        dynamicPlayers.getChildren().clear();
        for (int i = 1; i <= parameters.getNumberOfPlayers(); i++) {
            HBox playerBox = new HBox(20);
            playerBox.setAlignment(javafx.geometry.Pos.CENTER);
            Label playerLabel = new Label("Joueur " + i + ":");
            playerLabel.setStyle("-fx-font-size: 18px;");
            TextField playerField = new TextField();
            playerField.setPromptText("Entrez le pseudo");
            playerBox.getChildren().addAll(playerLabel, playerField);
            dynamicPlayers.getChildren().add(playerBox);
        }
    }
}
