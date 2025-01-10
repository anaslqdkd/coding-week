package codename.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import codename.Observer;
import codename.model.Game;
import codename.model.WordList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// TODO: selection thématiques (menu deroulant)
// option, ajouter pour un menu thematique
// option aléatoire
// option creation depuis 0

public class SettingsController implements Observer {

  @FXML private Slider gridLinesSlider;

  @FXML private Button cancelSettingsButton;

  @FXML private Button saveSettingsButton;

  @FXML private Slider gridColumnsSlider;

  @FXML private CheckBox darkModeCheckbox;

  @FXML private Button loadDatabaseButton;

  @FXML private Button backButton;

  @FXML private Button writeDataBase;

  @FXML private ChoiceBox<String> databaseOptions;
  private Game game;

  @FXML
  private void loadDatabase() {
    FileChooser fileChooser = new FileChooser();

    fileChooser.setTitle("Select Database File");

    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    fileChooser
        .getExtensionFilters()
        .addAll(
            new FileChooser.ExtensionFilter("Database Files", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));

    File selectedFile = fileChooser.showOpenDialog(new Stage());

    if (selectedFile != null) {
      System.out.println("Selected file: " + selectedFile.getAbsolutePath());
      String filePath = selectedFile.getAbsolutePath();
      int wordNumber = game.getSize();
      int rowsNumber = game.getBoard().getRows();
      int columnsNumber = game.getBoard().getColumns();
      try {
        this.game.setFilePath(filePath);
        List<String> words = WordList.getWordListGlobal(wordNumber, filePath);
        this.game.getBoard().setWords(words);
        this.game.getBoard().regenerateBoard(rowsNumber, columnsNumber);
        this.game.notify_observator();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("File selection canceled.");
    }
  }

  @FXML
  private void initialize() {
    System.out.println("Settings controller initialised");
    this.game = Game.getInstance();
    System.out.println("Initial grid lines: " + gridLinesSlider.getValue());
    System.out.println("Initial grid columns: " + gridColumnsSlider.getValue());
    game.add_observer(this);

    gridColumnsSlider
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              System.out.println("Grid columns updated: " + newValue.intValue());
            });

    saveSettingsButton.setOnAction(
        event -> {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(game.getParameters().getCurrentPage()));
            Parent root = loader.load();
            int rows = (int) gridLinesSlider.getValue();
            int columns = (int) gridColumnsSlider.getValue();
            System.out.println("(((((((())))))))" + rows + columns);

            game.setGridSize(rows, columns);
            System.out.println("in settings controller" + rows + columns);
            
            Stage stage = (Stage) saveSettingsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            game.notify_observator();
            stage.show();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });

    cancelSettingsButton.setOnAction(
        event -> {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(game.getParameters().getCurrentPage()));
            Parent root = loader.load();
            
            Stage stage = (Stage) cancelSettingsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            game.notify_observator();
            stage.show();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });    

    try {
      Path databasePath = Paths.get(getClass().getClassLoader().getResource("database").toURI());
      databaseOptions.setValue("");
      List<String> menuOptions =
          Files.list(databasePath)
              .filter(Files::isRegularFile)
              .map(path -> path.getFileName().toString())
              .collect(Collectors.toList());
      for (String option : menuOptions) {
        String addOption = removeExtension(option);
        databaseOptions.getItems().add(addOption);
      }
      databaseOptions.setOnAction(
          event -> {
            String selectedOption = databaseOptions.getValue(); // Get selected item
            if (selectedOption != null) {
              handleChoiceSelection(selectedOption);
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      loadDatabaseButton.setOnAction(
          event -> {
            loadDatabase();
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void handleChoiceSelection(String option) {
    System.out.println("You selected: " + option);

    try {
      String fileName = option + ".txt";

      URL resourceURL = getClass().getClassLoader().getResource("database/" + fileName);
      if (resourceURL == null) {
        throw new IllegalStateException("File not found: " + fileName);
      }

      Path databasePath = Paths.get(resourceURL.toURI());

      int rowsNumber = game.getBoard().getRows();
      int columnsNumber = game.getBoard().getColumns();

      String filePath = databasePath.toString();
      this.game.setFilePath(filePath);

      int wordNumber = rowsNumber * columnsNumber;
      List<String> words = WordList.getWordListGlobal(wordNumber, filePath);

      this.game.getBoard().setWords(words);
      this.game.getBoard().regenerateBoard(rowsNumber, columnsNumber);
      this.game.notify_observator();

      System.out.println("Words updated from file: " + fileName);
    } catch (Exception e) {
      System.err.println("Error loading database file: " + option);
      e.printStackTrace();
    }
  }

  private String removeExtension(String filename) {
    int dotIndex = filename.lastIndexOf('.');
    if (dotIndex > 0) {
      return filename.substring(0, dotIndex);
    } else {
      return filename;
    }
  }

  public void update() {
    System.out.println("Game update in settings controller");
  }
}
