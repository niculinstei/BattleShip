package ch.niculin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static ch.niculin.Direction.SENKRECHT;
import static org.junit.jupiter.api.Assertions.*;

class PlaygroundTest {
    Playground playground;
    Player player;

    @BeforeEach
    void init(){
        playground = new Playground(4);
    }


   @Test
     void testValidShipPlacement(){
       MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        player.setMainShip(mainShip);
    }

    @Test
    void testInvalidShipPlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        assertThrows(IllegalArgumentException.class, () -> player.setMainShip(mainShip));
    }

    @Test
    void testInvalidDoublePlacement(){
        MainShip mainShip = new AirCraftCarrier(SENKRECHT);
        player.setMainShip(mainShip);
        assertThrows(IllegalArgumentException.class, () -> player.setMainShip(mainShip));
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
