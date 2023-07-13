public class Rock {

    // Represents how many hits are needed to reveal whats underneath.
    private int health;

    private boolean hasTreasure;

    public Rock(int health) {
        this.health = health;
    }

    public int getHealth() { return health; }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public boolean isBroken() {
        return health == 0;
    }

    public boolean containsTreasure() {
        return hasTreasure;
    }
}
