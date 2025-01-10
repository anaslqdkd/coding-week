package codename.model;

public class Card implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final String word;    // Le mot de la carte
    private final String color;  // Rouge, Bleu, Neutre, Assassin
    private boolean revealed;    // Indique si la carte a été révélée

    public Card(String word, String color) {
        this.word = word;
        this.color = color;
        this.revealed = false;
    }

    public String getWord() {
        return word;
    }

    public String getColor() {
        return color;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    @Override
    public String toString() {
        return "Card{" +
                "word='" + word + '\'' +
                ", color='" + color + '\'' +
                ", revealed=" + revealed +
                '}';
    }
}
