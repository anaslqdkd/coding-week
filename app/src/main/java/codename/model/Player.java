package codename.model;

public class Player {
    private final String name;
    private boolean isSpymaster;

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

    public void setSpymaster(boolean isSpymaster) {
        this.isSpymaster = isSpymaster;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", isSpymaster=" + isSpymaster +
                '}';
    }
}
