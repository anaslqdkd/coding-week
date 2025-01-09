package codename.model;

import java.util.Collections;
import java.util.List;

public class Board {
  private final Card[][] cards;
  private int rows = 5;
  private int columns = 5;
  private List<String> words;

  public Board(List<String> words) {
    if (words.size() < rows * columns) {
      throw new IllegalArgumentException("La liste de mots doit contenir au moins 25 mots.");
    }

    cards = new Card[rows][columns];
    initializeBoard(words);
  }

  public int getRows() {
    return this.rows;
  }

  public int getColumns() {
    return this.columns;
  }

  public void setGridSize(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
  }

  // private void initializeBoard(List<String> words) {
  //  Collections.shuffle(words);
  //
  //  for (int i = 0; i < 25; i++) {
  //    String color;
  //    if (i < 9) color = "Red";
  //    else if (i < 17) color = "Blue";
  //    else if (i < 24) color = "Neutral";
  //    else color = "Assassin";
  //
  //    int row = i / 5;
  //    int col = i % 5;
  //    cards[row][col] = new Card(words.get(i), color);
  //  }
  //
  //  shuffleBoard();
  // }
  private void initializeBoard(List<String> words) {
    // Ensure the number of words is sufficient for the grid size
    int gridSize = rows * columns;
    if (words.size() < gridSize) {
      throw new IllegalArgumentException("Not enough words to fill the board");
    }

    Collections.shuffle(words);

    int redCount = (int) (gridSize * 0.36); // 36% for red cards
    int blueCount = (int) (gridSize * 0.36); // 36% for blue cards
    int neutralCount = (int) (gridSize * 0.24); // 24% for neutral cards
    int assassinCount = 1; // 1 for the assassin card

    int currentIndex = 0;

    // Iterate through the grid size and assign colors
    for (int i = 0; i < gridSize; i++) {
      String color;
      if (redCount > 0) {
        color = "Red";
        redCount--;
      } else if (blueCount > 0) {
        color = "Blue";
        blueCount--;
      } else if (neutralCount > 0) {
        color = "Neutral";
        neutralCount--;
      } else {
        color = "Assassin";
      }

      // Calculate row and column based on grid size
      int row = i / columns;
      int col = i % columns;

      // Assuming cards is a 2D array, we set the card at the respective position
      cards[row][col] = new Card(words.get(currentIndex), color);
      currentIndex++;
    }

    shuffleBoard();
  }

  public void regenerateBoard(int rows, int columns, List<String> words) {
    // Shuffle the words before placing them on the board
    Collections.shuffle(this.words);

    // Ensure the board is cleared before setting new cards
    // this.clear();  // Make sure the board has a clear method to reset it

    // Populate the board with new words
    Card[][] cards = this.getCards(); // Assuming this returns the current board array
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        // Set new cards with shuffled words
        if (i * columns + j < words.size()) {
          String word = words.get(i * columns + j);
          cards[i][j] = new Card(word, "Neutral"); // Default color, can be changed based on logic
        }
      }
    }
  }

  private void shuffleBoard() {
    List<Card> cardList = new java.util.ArrayList<>();

    // Add all the cards to the list
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        cardList.add(cards[i][j]);
      }
    }

    Collections.shuffle(cardList);

    int currentIndex = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        cards[i][j] = cardList.get(currentIndex++);
      }
    }
  }

  public Card[][] getCards() {
    return cards;
  }

  // public void revealCard(int row, int col) {
  //  if (row < 0 || row >= 5 || col < 0 || col >= 5) {
  //    throw new IllegalArgumentException("Coordonnées de carte invalides.");
  //  }
  //  cards[row][col].reveal();
  // }
  public void revealCard(int row, int col, int rows, int columns) {
    if (row < 0 || row >= rows || col < 0 || col >= columns) {
      throw new IllegalArgumentException("Invalid card coordinates.");
    }
    cards[row][col].reveal();
  }

  public boolean isAssassinRevealed() {
    for (Card[] row : cards) {
      for (Card card : row) {
        if (card.getColor().equals("Assassin") && card.isRevealed()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Card[] row : cards) {
      for (Card card : row) {
        sb.append(card).append("\t");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
