abstract class Weapon {
    protected double damage;
    public Weapon(double damage) {
    this.damage = damage;
    }
    abstract public String ascii_art();
    }