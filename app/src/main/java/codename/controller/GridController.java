package codename.controller;

import codename.Observer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GridController implements Observer {

  private static final String FILE_NAME = "database.txt";
  @FXML GridPane grid;

  @FXML
  private void initialize() {
    System.out.println("GridController initialized");
    generate_grid(grid);
  }

  public static List<String> getWordList(int nb) throws IOException {
    InputStream inputStream = GridController.class.getClassLoader().getResourceAsStream(FILE_NAME);

    if (inputStream == null) {
      System.out.println("Le fichier " + FILE_NAME + " est introuvable dans les ressources !");
      return Collections.emptyList();
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      List<String> words = reader.lines().collect(Collectors.toList());
      Collections.shuffle(words, new Random());
      List<String> selectedWords = words.stream().distinct().limit(nb).collect(Collectors.toList());

      for (String word : selectedWords) {
        System.out.println(word + "\n");
      }
      System.out.println("Liste de " + selectedWords.size() + " mots générée avec succès !");

      return selectedWords;
    }
  }

  public void generate_grid(GridPane gridpane) {
    final int rows = 5;
    final int columns = 5;

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        StackPane stackPane = new StackPane();

        Label label = new Label("Row " + row + ", Col " + col);
        stackPane.getChildren().add(label);

        gridpane.add(stackPane, col, row);
      }
    }
  }

  @Override
  public void update() {}
}
