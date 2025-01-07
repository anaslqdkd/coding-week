package codename.controller;

import codename.Observer;
import javafx.fxml.FXML;

public class GameController implements Observer {
  // private GridController gridController;

  @FXML
  private void initialize() {
    // try{
    //  FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("/grid.fxml"));
    //          GridPane grid = gridLoader.load();
    //          gridController = gridLoader.getController();
    //
    // }catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //       try {
    //       game.revealCard(cardIndex);
    //   } catch (IllegalArgumentException e) {
    //       System.out.println("Erreur : " + e.getMessage());
    //       return;
    //   }

    //   // Décrémenter les clics restants
    //   game.decrementClicksRemaining();

    //   // Vérifier si la partie est terminée
    //   if (game.isGameOver()) {
    //       System.out.println("L'équipe " + game.getCurrentTurn().getColor() + " a gagné !");
    //       return;
    //   }

    //   // Si clics épuisés, changer de tour
    //   if (game.getClicksRemaining() == 0) {
    //       System.out.println("Clics maximum atteints. Tour terminé.");
    //       game.switchTurn();
    //   }
    // }
  }

  @Override
  public void update() {}
}

