public class Rock {

    // Represents how many hits are needed to reveal whats underneath.
    private int health;

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
}
