package codename.controller;

import com.google.gson.Gson;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import codename.model.Game;

public class MenuBarController {
    @FXML 
    private Button loadButton;

    @FXML
    private Button saveButton;

    private Game game;

    // @FXML
    // private void initialize() {
    //     this.game = Game.getInstance();
    //     loadButton.setOnAction(event -> {
    //         game.load();
    //     });

    //     saveButton.setOnAction(event -> {
    //         game.save();
    //     });
    // }
    

    // public void saveGame() {
    //     Gson gson = new Gson();
    //     String gameStateJson = gson.toJson(game);  // Convertir l'état du jeu en JSON
    //     Files.writeString(Path.of("savegame.json"), gameStateJson);  // Enregistrer dans un fichier

    // }

    // public void loadGame() {
    //     Gson gson = new Gson();
    //     String gameStateJson = Files.readString(Path.of("savegame.json"));  // Lire le fichier
    //     GameState gameState = gson.fromJson(gameStateJson, Game.class);  // Convertir le JSON en objet GameState
    // }
}

