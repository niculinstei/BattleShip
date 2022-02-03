package ch.niculin.ships;

import ch.niculin.Direction;

public class RubberBoat extends MainShip {

    public RubberBoat(){
        super.setSize(1);
        super.setDirection(Direction.SENKRECHT);

    }

    @Override
    public String toString() {
        return "RubberBoat{" +
                "size=" + getSize() +
                '}';
    }
}
