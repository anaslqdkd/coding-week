package codename.controller;

import javafx.scene.control.Button;
import com.google.gson.Gson;


public class MenuBarController {
    @FXML 
    private Button loadButton;

    @FXML
    private Button saveButton;

    private Game game;

    private void initialize() {
        this.game = Game.getInstance();
        loadButton.setOnAction(event -> {
            game.load();
        });

        saveButton.setOnAction(event -> {
            game.save();
        });
    }
    

    public void saveGame() {
        Gson gson = new Gson();
        String gameStateJson = gson.toJson(gameState);  // Convertir l'état du jeu en JSON
        Files.writeString(Path.of("savegame.json"), gameStateJson);  // Enregistrer dans un fichier

    }

    public void loadGame() {
        Gson gson = new Gson();
        String gameStateJson = Files.readString(Path.of("savegame.json"));  // Lire le fichier
        GameState gameState = gson.fromJson(gameStateJson, GameState.class);  // Convertir le JSON en objet GameState
    }
}

