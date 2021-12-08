package ch.niculin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaygroundTest {
    Playground playground;

    @BeforeEach
    void init(){
        playground = new Playground();
    }


   @Test
     void testValidShipPlacement(){
        Ship ship = new Ship('A', 1);
        playground.setShip(ship);
    }

    @Test
    void testInvalidShipPlacement(){
        Ship ship = new Ship('A', 5);
        assertThrows(IllegalArgumentException.class, () -> playground.setShip(ship));
    }

    @Test
    void testInvalidDoublePlacement(){
        Ship ship = new Ship('A', 1);
        playground.setShip(ship);
        assertThrows(IllegalArgumentException.class, () -> playground.setShip(ship));
    }

    @Test
    void testHitShot(){
        playground.setShip(new Ship('A', 1));
        Shot shot = new Shot('A', 1);
        var condition = playground.shootShip(shot);
        assertTrue(condition);
    }

    @Test
    void testFadedShot(){
        Shot shot = new Shot('A', 1);
        var condition = playground.shootShip(shot);
        assertFalse(condition);
    }

    @Test
    public void testToString() {
        var actual = playground.toString();
        assertEquals("Playground{playground=[Point{x=A, y=1}, Point{x=A, y=2}, Point{x=A, y=3}, Point{x=A, y=4}, Point{x=B, y=1}, Point{x=B, y=2}, Point{x=B, y=3}, Point{x=B, y=4}, Point{x=C, y=1}, Point{x=C, y=2}, Point{x=C, y=3}, Point{x=C, y=4}, Point{x=D, y=1}, Point{x=D, y=2}, Point{x=D, y=3}, Point{x=D, y=4}]\n" +
                "ships=[]}", actual);
    }

    @Test
    public void testHasShipsTrue(){
        playground.setShip(new Ship('A', 1));
        var condition = playground.hasShips();
        assertTrue(condition);
    }

    @Test
    public void testHasShipsFalse(){
        var condition = playground.hasShips();
        assertFalse(condition);
    }

    @Test
    void testCountShips() {
        playground.countShips();
    }
}
