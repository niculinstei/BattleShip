package ch.niculin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static ch.niculin.Direction.SENKRECHT;
import static org.junit.jupiter.api.Assertions.*;

class PlaygroundTest {
    Playground playground;

    @BeforeEach
    void init(){
        playground = new Playground(4, "Hans");
    }


   @Test
     void testValidShipPlacement(){
       MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        playground.setMainShip(mainShip);
    }

    @Test
    void testInvalidShipPlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        assertThrows(IllegalArgumentException.class, () -> playground.setMainShip(mainShip));
    }

    @Test
    void testInvalidDoublePlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        playground.setMainShip(mainShip);
        assertThrows(IllegalArgumentException.class, () -> playground.setMainShip(mainShip));
    }


    @Test
    public void testToString() {
        var actual = playground.toString();
        String excpectet = """
                Playground{playground=
                    [A, B, C, D]
                [1
                , 2
                , 3
                , 4
                ]
                ships=[]}""";
        assertEquals(excpectet, actual);
    }


    @Test
    void testGetSize(){
        var size = playground.getSize();
    }

    @Test
    void testPlayGroundGetX(){

    }


}
