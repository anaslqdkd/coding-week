package codename.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private final String color; // Rouge ou Bleu
  private final List<Player> players;
  private int score;

  public Team(String color) {
    this.color = color;
    this.players = new ArrayList<>();
    if (color == "Red") {
      this.score = 9;
    } else {
      this.score = 8;
    }
  }

  public String getColor() {
    return color;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public int getScore() {
    return score;
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

    public void incrementScore() {
        score++;
    }

    public void clear() {
        players.clear();
    }

  @Override
  public String toString() {
    return "Team{" + "color='" + color + '\'' + ", players=" + players + ", score=" + score + '}';
  }
}
