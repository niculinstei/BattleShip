package ch.niculin.ships;

public class RubberBoat extends MainShip {

    public RubberBoat(){
        super.setSize(1);
    }

    @Override
    public String toString() {
        return "RubberBoat{" +
                "size=" + getSize() +
                '}';
    }
}
