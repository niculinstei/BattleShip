package ch.niculin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static ch.niculin.Direction.SENKRECHT;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Playground playground;

    @BeforeEach
    void init(){
        playground = new Playground(10);
        player = new Player("Hans", playground);
    }

    @Test
    void testInvalidShipPlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        assertThrows(Exception.class, () -> player.setMainShip(mainShip));
    }

    @Test
    void testInvalidDoublePlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        assertThrows(Exception.class, () -> player.setMainShip(mainShip));
    }

    @Test
    void getName() {
        String name = "Max";
        Player player = new Player(name, playground);
        boolean condition = player.getName().equals("Max");
        assertTrue(condition);

    }
}
