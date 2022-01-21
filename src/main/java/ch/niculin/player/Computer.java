package ch.niculin.player;

import ch.niculin.*;
import ch.niculin.ships.RubberBoat;
import ch.niculin.ships.SailingBoat;

import java.util.Optional;
import java.util.Random;

public class Computer extends Player {

    public Computer(Playground playground) {
        super("Computer1", playground);
    }

    public void generateShips() {
        placeShip();
    }

    public Shot getRandomShot() {
        return new Shot(getRandomChar(), getRandomInt());
    }

    private void placeShip() {
        Point createdPoint = new Point('D', 4);
        Optional<Point> playgroundPoint = getPlaygroundPoint(createdPoint);
        Point point;
        if (playgroundPoint.isPresent()) {
            point = playgroundPoint.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }
        RubberBoat rubberBoat = new RubberBoat();
        rubberBoat.addShipPositionPoint(point);
        getMainShips().add(rubberBoat);
        point.setFieldStatus(FieldStatus.SHIP);

        SailingBoat sailingBoat = new SailingBoat(Direction.WAAGRECHT);
        Point point1created = new Point('B', 2);
        Optional<Point> point2 = getPlaygroundPoint(point1created);
        Point point1;
        if (point2.isPresent()) {
            point1 = point2.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }
        sailingBoat.addShipPositionPoint(point1);
        getMainShips().add(sailingBoat);
        point1.setFieldStatus(FieldStatus.SHIP);
        Point rightPoint = getRightPoint(point1);
        sailingBoat.addShipPositionPoint(rightPoint);
        rightPoint.setFieldStatus(FieldStatus.SHIP);

    }

    private Point getLowerPoint(Point point) {
        int y = point.getY();
        y++;
        Point point1 = new Point(point.getX(), y);
        Optional<Point> point2 = getPlaygroundPoint(point1);
        Point pointToReturn;
        if (point2.isPresent()) {
            pointToReturn = point2.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }

        return pointToReturn;
    }


    private Point getRightPoint(Point point) {
        char x = point.getX();
        x++;
        Point point1 = new Point(x, point.getY());
        Optional<Point> point2 = getPlaygroundPoint(point1);
        Point pointToReturn;
        if (point2.isPresent()) {
            pointToReturn = point2.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }

        return pointToReturn;

    }

    private char getRandomChar() {
        Random r = new Random();
        return (char) (r.nextInt(getPlaygroundSize()) + 'A');
    }

    private int getRandomInt() {
        Random number = new Random();
        return number.nextInt((getPlaygroundSize() - 1) + 1) + 1;
    }
}
