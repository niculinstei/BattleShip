package ch.niculin;

public class SailingBoat extends MainShip {

    public SailingBoat(Direction dircetion){
        super.direction = dircetion;
        super.size = 2;
    }

    @Override
    public String toString() {
        return "SailingBoat{" +
                "size=" + size +
                '}';
    }
}
