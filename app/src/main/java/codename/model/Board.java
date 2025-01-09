package codename.model;

import java.util.Collections;
import java.util.List;

public class Board {
  private Card[][] cards;
  private int rows = 5;
  private int columns = 5;
  private int redCount;
  private int blueCount;
  private List<String> words;

  public Board(List<String> words) {
    if (words.size() < rows * columns) {
      throw new IllegalArgumentException("La liste de mots doit contenir au moins 25 mots.");
    }
    this.redCount = 9;
    this.blueCount = 8;
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
    this.cards = new Card[rows][columns];
  }

  public void setWords(List<String> words) {
    this.words = words;
  }

  public List<String> getWords() {
    return this.words;
  }

  public void regenerateBoard(int rows, int columns) {
    setGridSize(rows, columns);
    initializeBoard(this.words);
  }

  public int getBlueTeamCount() {
    return blueCount;
  }

  public int getRedTeamCount() {
    return redCount;
  }

  private void initializeBoard(List<String> words) {
    int gridSize = rows * columns;
    if (words.size() < gridSize) {
      throw new IllegalArgumentException("Not enough words to fill the board");
    }

    Collections.shuffle(words);
    System.out.println("Gridsize : " + gridSize);
    this.redCount = (int) (gridSize * 0.36);
    this.blueCount = (int) (gridSize * 0.36);
    int redCardToPut = redCount;
    int blueCardToPut = blueCount;
    int neutralCount = (int) (gridSize * 0.24);
    int assassinCount = 1;

    int currentIndex = 0;

    for (int i = 0; i < gridSize; i++) {
      String color;
      if (redCardToPut > 0) {
        color = "Red";
        redCardToPut--;
      } else if (blueCardToPut > 0) {
        color = "Blue";
        blueCardToPut--;
      } else if (neutralCount > 0) {
        color = "Neutral";
        neutralCount--;
      } else {
        color = "Assassin";
      }

      int row = i / columns;
      int col = i % columns;

      cards[row][col] = new Card(words.get(currentIndex), color);
      currentIndex++;
    }

    shuffleBoard();
  }

  private void shuffleBoard() {
    List<Card> cardList = new java.util.ArrayList<>();

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
