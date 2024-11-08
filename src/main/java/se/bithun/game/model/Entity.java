package se.bithun.game.model;

public abstract class Entity {
        private String name;   // Variables for models attributes
        private int health;
        private int damage;

        public Entity(String name, int health, int damage) {
            this.name = name; //constructor
            this.health = health;
            this.damage = damage;
        }

        public int getHealth() {
            return health;
        }

        public int getDamage() {
            return damage;
        }

        public void takeDamage(int amount) {
            this.health -= amount; // decreases the entities health.
        }

        public boolean isAlive() {
            return health > 0;
        }
}
