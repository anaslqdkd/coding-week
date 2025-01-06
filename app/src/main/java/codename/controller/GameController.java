package codename.controller;

import codename.model.Game;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void handleCardClick(int cardIndex) {
        if (game.getClicksRemaining() <= 0) {
            System.out.println("Pas de clics restants. Passez au tour suivant.");
            game.switchTurn();
            return;
        }

        try {
            game.revealCard(cardIndex);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return;
        }

        // Décrémenter les clics restants
        game.clicksRemaining--;

        // Vérifier si la partie est terminée
        if (game.isGameOver()) {
            System.out.println("L'équipe " + game.getCurrentTurn().getColor() + " a gagné !");
            return;
        }

        // Si clics épuisés, changer de tour
        if (game.getClicksRemaining() == 0) {
            System.out.println("Clics maximum atteints. Tour terminé.");
            game.switchTurn();
        }
    }
}

