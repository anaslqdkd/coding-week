package codename.controller;

import codename.Observer;
import codename.model.Card;
import codename.model.Game;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AgentGridController implements Observer {

  private GameController gameController;

  private static final String FILE_NAME = "database.txt";
  @FXML GridPane gridAgent;
  private Game game;
  List<String> words =
      Arrays.asList(
          "apple",
          "banana",
          "cherry",
          "date",
          "elderberry",
          "fig",
          "grape",
          "honeydew",
          "kiwi",
          "lemon",
          "mango",
          "nectarine",
          "orange",
          "papaya",
          "quince",
          "raspberry",
          "strawberry",
          "tangerine",
          "ugli",
          "vanilla",
          "watermelon",
          "xigua",
          "yam",
          "zucchini",
          "acorn",
          "almond");

  @FXML
  private void initialize() {
    // if (game == null) {
    //  throw new IllegalStateException("Game instance not set for AgentGridController");
    // }
    System.out.println("AgentGridController initialized");
    generate_grid_agent(gridAgent);
  }

  public void setGame(Game game) {
    System.out.println("here");
    this.game = gameController.getGame();
  }

  public void setGameController(GameController gameController) {
    this.gameController = gameController;
  }

  public void generate_grid_agent(GridPane gridPane) {
    final int rows = 5;
    final int columns = 5;
    Card[][] matrix = this.game.getBoard().getCards();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        StackPane stackPane = new StackPane();

        Card card = matrix[row][col];
        Label label = new Label(card.getWord());
        label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black;");

        stackPane.setPrefSize(100, 100);
        final int currentRow = row;
        final int currentCol = col;

        stackPane.setMinSize(50, 50);
        stackPane.setMaxSize(200, 200);
        Rectangle rectangle = new Rectangle(100, 60); // Width: 100, Height: 60
        rectangle.setArcWidth(10); // Rounded corners
        rectangle.setArcHeight(10);
        String color = card.getColor();
        if (card.isRevealed()) {
          if (color == "Red") {
            rectangle.setFill(Color.RED);
          }
          if (color == "Blue") {
            rectangle.setFill(Color.BLUE);
          }
          if (color == "Assassin") {
            rectangle.setFill(Color.BLACK);
            label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: white;");
            // mourir xd
          }
          if (color == "Neutral") {
            rectangle.setFill(Color.WHEAT);
          }
        } else {
          rectangle.setFill(Color.BEIGE);
        }
        stackPane.setOnMouseClicked(
            event -> {
              handleCardClick(currentRow, currentCol);
            });

        stackPane.getChildren().add(0, rectangle);
        stackPane.getChildren().add(label);

        gridPane.add(stackPane, col, row);
      }
    }
  }

  public void handleCardClick(int row, int col) {
    Card[][] cards = game.getBoard().getCards();
    Card clickedCard = cards[row][col]; // Get the clicked card
    if (!clickedCard.isRevealed()) {
      clickedCard.reveal();
      System.out.println("Card revealed: " + clickedCard.getWord());
    } else {
      System.out.println("Card already revealed: " + clickedCard.getWord());
    }
    update();
  }

  @Override
  public void update() {
    // generate_grid_agent(gridAgent);
    // generate_grid_spy(gridSpy);
  }
}
