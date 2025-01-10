package codename.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WriteDataBaseController implements Observer {

    @FXML
    private TextArea databaseTextArea;

    @FXML
    private Button cancelButton;

    @FXML
    private MenuButton themeButton;

    @FXML
    private TextField titleField;

    @FXML
    private Button saveButton;

    private Game game;

    private Path selectedFilePath;

    @FXML
    private void initialize() {
        System.out.println("initialize WriteDataBaseController");
        this.game = Game.getInstance();
        game.add_observer(this);

        // Charger les fichiers .txt existants dans le répertoire database
        loadDatabaseFiles();

        cancelButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveButton.setOnAction(event -> handleSaveButtonAction());
    }

    private void loadDatabaseFiles() {
        try {
            Path databaseDir = Paths.get("/home/raph/Documents/semestre_3/codingweek/grp29/app/src/main/resources/database");
            Files.list(databaseDir)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {
                    MenuItem menuItem = new MenuItem(path.getFileName().toString().replace(".txt", ""));
                    menuItem.setOnAction(event -> loadDatabaseFile(path));
                    themeButton.getItems().add(menuItem);
                });

            // Ajouter une option pour créer un nouveau fichier
            MenuItem newFileItem = new MenuItem("Nouveau fichier");
            newFileItem.setOnAction(event -> {
                titleField.clear();
                databaseTextArea.clear();
                selectedFilePath = null;
            });
            themeButton.getItems().add(newFileItem);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDatabaseFile(Path path) {
        try {
            selectedFilePath = path;
            titleField.setText(path.getFileName().toString().replace(".txt", ""));
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            databaseTextArea.setText(content.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        // Vérifier que le titre ne contient pas d'espaces et possède au moins un caractère
        String title = titleField.getText().trim();
        if (title.isEmpty() || title.contains(" ")) {
            System.out.println("Le titre doit contenir au moins un caractère et ne doit pas contenir d'espaces.");
            return;
        }

        // Vérifier qu'il y a au moins 25 mots dans le contenu
        String content = databaseTextArea.getText().trim();
        String[] words = content.split("\\s+");
        if (words.length < 25) {
            System.out.println("Le contenu doit contenir au moins 25 mots.");
            return;
        }

        // Chemin du fichier à enregistrer
        if (selectedFilePath == null) {
            selectedFilePath = Paths.get("/home/raph/Documents/semestre_3/codingweek/grp29/app/src/main/resources/database", title + ".txt");

            // Vérifier si le fichier existe déjà
            if (Files.exists(selectedFilePath)) {
                System.out.println("Un fichier avec ce nom existe déjà. Enregistrement annulé pour éviter d'écraser le fichier existant.");
                return;
            }
        }

        // Enregistrer le contenu dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFilePath.toFile()))) {
            writer.write(content);
            System.out.println("Fichier enregistré : " + selectedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Revenir à la page des paramètres
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
}