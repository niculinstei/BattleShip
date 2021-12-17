package ch.niculin;

import java.util.LinkedList;
import java.util.List;

public abstract class MainShip {
    Direction direction;
    int size;
    List<Point> shipPosition = new LinkedList<>();

    public int getSize() {
        return size;
    }
//Fehler
    public void addShipPositionPoint(Point point){
        if ( shipPosition.size() >= size){
            throw new IllegalArgumentException("Ship already placed completly!");
        }else {
            shipPosition.add(point);
        }
    }

    public Direction getDirection() {
        return direction;
    }
}
