package codename.controller;

import codename.model.Game;
import codename.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

    /**
     * Initialise le contrôleur après le chargement du fichier FXML.
     */
    @FXML
    private void initialize() {
        // Configuration des actions des boutons
        settingsButton.setOnAction(event -> openSettings());
        randomButton.setOnAction(event -> randomizeSelection());
        confirmButton.setOnAction(event -> confirmSelection());
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
