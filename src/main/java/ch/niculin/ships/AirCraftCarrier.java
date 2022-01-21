package ch.niculin.ships;

import ch.niculin.Direction;

import java.util.LinkedList;

public class AirCraftCarrier extends MainShip {

    public AirCraftCarrier(Direction dircetion){
        super.setDirection(dircetion);
        super.setSize(3);
    }

    @Override
    public String toString() {
        return "AirCraftCarrier{" +
                "size=" + getSize() +
                '}';
    }
}
