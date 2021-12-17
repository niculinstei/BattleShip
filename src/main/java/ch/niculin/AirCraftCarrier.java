package ch.niculin;

import java.util.LinkedList;

public class AirCraftCarrier extends MainShip {



    public AirCraftCarrier(Direction dircetion){
        super.direction = dircetion;
        super.size = 3;
        super.shipPosition = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "AirCraftCarrier{" +
                "size=" + size +
                '}';
    }
}
