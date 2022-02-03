package ch.niculin.ships;

import ch.niculin.Direction;

public class SailingBoat extends MainShip {

    public SailingBoat(Direction dircetion){
        super.setDirection(dircetion);
        super.setSize(2);

    }

    @Override
    public String toString() {
        return "SailingBoat{" +
                "size=" + getSize() +
                '}';
    }
}
