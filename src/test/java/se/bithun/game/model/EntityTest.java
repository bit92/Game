package se.bithun.game.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResidentTest {

    @Test
    void testHasWeaponInitiallyFalse() {
        Resident resident = new Resident();
        assertFalse(resident.hasWeapon(), "Resident should not have a weapon initially.");
    }

    @Test
    void testFindWeaponSetsHasWeaponTrue() {
        Resident resident = new Resident();
        resident.findWeapon();  // Resident finds a weapon
        assertTrue(resident.hasWeapon(), "Resident should have a weapon after finding it.");
    }
    @Test
    void testTakeDamageDecreaseHealth(){
        Resident resident = new Resident();
        resident.takeDamage(10);
        assertEquals(90, resident.getHealth(), "The health Value shoukd decrease");
    }
}