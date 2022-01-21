package ch.niculin.ships;

import ch.niculin.Direction;
import ch.niculin.Point;

import java.util.LinkedList;
import java.util.List;

public abstract class MainShip {
    private Direction direction;
    private int size;
    private final List<Point> shipPosition = new LinkedList<>();

    public int getSize() {
        return size;
    }

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

    public List<Point> getShipPosition() {
        return shipPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
