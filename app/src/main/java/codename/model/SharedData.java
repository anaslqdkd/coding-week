package codename.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SharedData {
    private final ObjectProperty<Clue> clue = new SimpleObjectProperty<>();

    public Clue getClue() {
        return clue.get();
    }

    public void setClue(Clue clue) {
        this.clue.set(clue);
    }

    public ObjectProperty<Clue> clueProperty() {
        return clue;
    }

    public void clearClue() {
        clue.set(null);
    }
}
