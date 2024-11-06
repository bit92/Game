package se.bithun.game.model;

public class Resident extends Entity {
    public boolean hasWeapon;

    public Resident() {
        super("Resident", 100, 10); // Starting health and base damage
        this.hasWeapon = false;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public void findWeapon() {
        System.out.println("You found a frying pan! Your damage increases.");
        this.hasWeapon = true;
    }

    @Override
    public int getDamage() {
        return hasWeapon ? super.getDamage() + 10 : super.getDamage();
    }
}
