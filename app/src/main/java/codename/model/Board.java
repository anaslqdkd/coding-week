package codename.model;

import java.util.Collections;
import java.util.List;

public class Board {
  private final Card[][] cards;

  public Board(List<String> words) {
    if (words.size() < 25) {
      throw new IllegalArgumentException("La liste de mots doit contenir au moins 25 mots.");
    }

    cards = new Card[5][5];
    initializeBoard(words);
  }

  private void initializeBoard(List<String> words) {
    Collections.shuffle(words);

    for (int i = 0; i < 25; i++) {
      String color;
      if (i < 9) color = "Red";
      else if (i < 17) color = "Blue";
      else if (i < 24) color = "Neutral";
      else color = "Assassin";

      int row = i / 5;
      int col = i % 5;
      cards[row][col] = new Card(words.get(i), color);
    }

    shuffleBoard();
  }

  private void shuffleBoard() {
    List<Card> cardList = new java.util.ArrayList<>();
    for (Card[] row : cards) {
      Collections.addAll(cardList, row);
    }

    Collections.shuffle(cardList);
    for (int i = 0; i < 25; i++) {
      cards[i / 5][i % 5] = cardList.get(i);
    }
  }

  public Card[][] getCards() {
    return cards;
  }

  public void revealCard(int row, int col) {
    if (row < 0 || row >= 5 || col < 0 || col >= 5) {
      throw new IllegalArgumentException("Coordonnées de carte invalides.");
    }
    cards[row][col].reveal();
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
