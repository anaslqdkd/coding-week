package codename.controller;

import codename.model.Game;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionEspionController {

    private Game game;

    @FXML
    private Button settingsButton;

    @FXML
    private Button randomButton;

    @FXML
    private Button confirmButton;

    @FXML
    private VBox playerList;


    private void setgame(Game game) {
        this.game = game;
    }

    /**
     * Initialise le contrôleur après le chargement du fichier FXML.
     */
    @FXML
    private void initialize() {
        // Configuration des actions des boutons
        settingsButton.setOnAction(event -> openSettings());
        randomButton.setOnAction(event -> randomizeSelection());
        confirmButton.setOnAction(event -> {
            try {
                // Charger le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
                Parent root = loader.load();

                // Configurer le contrôleur
                GameController controller = loader.getController();
                controller.setGame(this.game);

                // Configurer la nouvelle scène et l'afficher
                Stage currentStage = (Stage) confirmButton.getScene().getWindow();
                Scene newScene = new Scene(root);
                currentStage.setScene(newScene);
                currentStage.show();

            } catch (IOException e) {
                System.err.println("Erreur lors du chargement de la vue 'selection_equipe.fxml'.");
                e.printStackTrace();
                // Optionnel : afficher une alerte à l'utilisateur
            }
        });
    }

    /**
     * Associe l'objet `Game` à ce contrôleur.
     *
     * @param game L'instance du jeu à utiliser.
     */
    public void setGame(Game game) {
        this.game = game;
        updatePlayerList();
    }

    /**
     * Met à jour la liste des joueurs affichée dans l'interface.
     */
    private void updatePlayerList() {
        playerList.getChildren().clear(); // Nettoie la liste des joueurs

        if (game != null) {
            game.getRedTeam().getPlayers().forEach(player -> addPlayerToView(player, "Rouge"));
            game.getBlueTeam().getPlayers().forEach(player -> addPlayerToView(player, "Bleu"));
        }
    }

    /**
     * Ajoute un joueur à la liste de l'interface utilisateur.
     *
     * @param player Le joueur à ajouter.
     * @param team   L'équipe du joueur ("Rouge" ou "Bleu").
     */
    private void addPlayerToView(Player player, String team) {
        Label playerLabel = new Label(player.getName() + " (" + team + ")");
        playerLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        playerList.getChildren().add(playerLabel);
    }

    /**
     * Action pour le bouton "Paramètres".
     */
    private void openSettings() {
        System.out.println("Ouverture des paramètres...");
        // Logique pour ouvrir les paramètres
    }

    /**
     * Action pour le bouton "Aléatoire".
     */
    private void randomizeSelection() {
        System.out.println("Sélection aléatoire...");
        // Logique pour choisir des éléments aléatoires
    }

    /**
     * Action pour le bouton "Confirmer".
     */
    private void confirmSelection() {
        System.out.println("Sélection confirmée...");
        // Logique pour confirmer la sélection et passer à l'étape suivante
    }
}
