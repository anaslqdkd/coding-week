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

public class GridSpyController implements Observer {
  private GridAgentController gridAgentController;

  private static final String FILE_NAME = "database.txt";
  @FXML GridPane gridSpy;
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
    System.out.println("GridSpyController initialized");
    this.game = Game.getInstance();
    game.add_observer(this);
    System.out.println(game);
    generate_grid_spy(gridSpy);
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setGridAgentController(GridAgentController gridAgentController) {
    this.gridAgentController = gridAgentController;
  }

  public void generate_grid_spy(GridPane gridpane) {
    final int rows = 5;
    final int columns = 5;
    Card[][] matrix = game.getBoard().getCards();
    System.out.println("print in generate grid");

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
        if (color == "Red") {
          rectangle.setFill(Color.RED);
        }
        if (color == "Blue") {
          rectangle.setFill(Color.BLUE);
        }
        if (color == "Assassin") {
          rectangle.setFill(Color.BLACK);
          label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: white;");
        }
        if (color == "Neutral") {
          rectangle.setFill(Color.WHEAT);
        }

        stackPane.getChildren().add(0, rectangle);
        if (!card.isRevealed()) {
          stackPane.getChildren().add(label);
        }

        gridpane.add(stackPane, col, row);
      }
    }
  }

  @Override
  public void update() {
    generate_grid_spy(gridSpy);
  }
}
