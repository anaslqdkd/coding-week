package codename.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final List<Card> cards;

    public Board(List<String> words) {
        this.cards = new ArrayList<>();
        initializeBoard(words);
    }

    private void initializeBoard(List<String> words) {
        if (words.size() < 25) {
            throw new IllegalArgumentException("La liste de mots doit contenir au moins 25 mots.");
        }

        Collections.shuffle(words);

        // Ajouter les cartes selon les couleurs
        for (int i = 0; i < 25; i++) {
            String color;
            if (i < 9) color = "Red";         // 9 mots rouges
            else if (i < 17) color = "Blue";  // 8 mots bleus
            else if (i < 24) color = "Neutral"; // 7 mots neutres
            else color = "Assassin";          // 1 mot assassin

            cards.add(new Card(words.get(i), color));
        }

        // Mélanger les cartes pour leur emplacement final
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void revealCard(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IllegalArgumentException("Index de carte invalide.");
        }
        cards.get(index).reveal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
}
