package codename.model;

public class Player {
    private final String name;
    private final boolean isSpymaster;

    public Player(String name, boolean isSpymaster) {
        this.name = name;
        this.isSpymaster = isSpymaster;
    }

    public String getName() {
        return name;
    }

    public boolean isSpymaster() {
        return isSpymaster;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", isSpymaster=" + isSpymaster +
                '}';
    }
}
