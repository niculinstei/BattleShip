package ch.niculin;

public class RubberBoat extends MainShip {

    public RubberBoat(){
        super.size = 1;
    }

    @Override
    public String toString() {
        return "RubberBoat{" +
                "size=" + size +
                '}';
    }
}
