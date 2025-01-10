package codename.controller;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import java.util.ArrayList;
import codename.Observer;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import codename.model.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Optional;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.UUID;

public class MenuBarController {
    @FXML
    private MenuItem loadButton;

    @FXML
    private MenuItem saveButton;

    private Game game;

    @FXML
    private void initialize() {
        this.game = Game.getInstance();

        loadButton.setOnAction(event -> {
            try {
                loadGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveButton.setOnAction(event -> {
            try {
                saveGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void saveGame() throws IOException {
        // Utiliser DirectoryChooser pour demander à l'utilisateur de choisir un répertoire
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir le répertoire pour sauvegarder");
    
        // Ouvrir la boîte de dialogue pour choisir le répertoire
        File selectedDirectory = directoryChooser.showDialog(new Stage());
    
        // Si l'utilisateur a choisi un répertoire
        if (selectedDirectory != null) {
            // Demander à l'utilisateur d'entrer un nom de fichier
            TextInputDialog fileNameDialog = new TextInputDialog("game_save"); // Valeur par défaut
            fileNameDialog.setTitle("Nom du fichier");
            fileNameDialog.setHeaderText("Veuillez entrer un nom pour le fichier de sauvegarde.");
            fileNameDialog.setContentText("Nom du fichier (sans extension) :");
    
            // Obtenir le nom du fichier entré par l'utilisateur
            Optional<String> result = fileNameDialog.showAndWait();
    
            // Si l'utilisateur a entré un nom, procéder à la sauvegarde
            if (result.isPresent() && !result.get().isEmpty()) {
                String fileName = result.get() + ".ser"; // Ajouter l'extension .ser pour la sérialisation
                File file = new File(selectedDirectory, fileName);
    
                // Sérialiser l'objet `game`
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(game); // Sérialiser l'objet game dans le fichier
                }
    
                System.out.println("Partie enregistrée dans : " + file.getAbsolutePath());
            } else {
                System.out.println("Aucun nom de fichier fourni. La sauvegarde a été annulée.");
            }
        } else {
            System.out.println("Aucun répertoire choisi, la sauvegarde a été annulée.");
        }
    }
    

    
    public void loadGame() throws IOException {
        // Utiliser un FileChooser pour demander un fichier à charger
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger une partie");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Sérialisés", "*.ser"));
    
        // Ouvrir la boîte de dialogue
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            // Charger l'objet Game depuis le fichier sérialisé
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                // Désérialiser l'objet Game
                Game loadedGame = (Game) in.readObject();
                
                // Mettre à jour l'instance du jeu
                Game.setInstance(loadedGame);
    
                // Réinitialiser les observateurs, car ils étaient marqués comme `transient` et n'ont pas été sérialisés
                ArrayList<Observer> observers = new ArrayList<>();  // Créez une nouvelle liste d'observateurs
                Game.getInstance().setObservers(observers);
    
                // Vous pouvez maintenant ajouter les observateurs nécessaires à votre jeu après la désérialisation
                // Exemple : Game.getInstance().addObserver(someObserver);
    
                System.out.println("Partie chargée depuis : " + file.getAbsolutePath());
    
                // Notifier les observateurs pour rafraîchir l'UI
                Game.getInstance().notify_observator(); // Cette méthode doit notifier l'UI des changements
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Erreur lors du chargement du fichier : " + e.getMessage());
            }
        } else {
            System.out.println("Aucun fichier choisi, le chargement a été annulé.");
        }
    }
    
    
}
