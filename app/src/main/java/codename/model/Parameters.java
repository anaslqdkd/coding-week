package codename.model;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
  private int numberOfPlayers;
  private String gameMode; // "multi" ou "solo"
  private List<Player> players;
  private String currentPage;

  public Parameters() {
    this.numberOfPlayers = 0;
    this.gameMode = "multi";
    this.players = new ArrayList<>();
    this.currentPage = "/menu.fxml";
  }

  public String getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(String currentPage) {
    this.currentPage = currentPage;
  }

  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public String getGameMode() {
    return gameMode;
  }

  public void setGameMode(String gameMode) {
    this.gameMode = gameMode;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public void addNumberOfPlayers() {
    this.numberOfPlayers = this.players.size();
  }

  public void removePlayer(Player player) {
    this.players.remove(player);
    this.numberOfPlayers = this.players.size();
  }
}

