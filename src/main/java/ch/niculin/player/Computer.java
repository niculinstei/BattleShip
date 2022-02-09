package ch.niculin.player;

import ch.niculin.Direction;
import ch.niculin.Playground;
import ch.niculin.Point;
import ch.niculin.Shot;
import ch.niculin.ships.MainShip;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Computer extends Player {

    public Computer(Playground playground) {
        super("Computer1", playground);
    }

    public void setMainShip(MainShip mainShip) {
        List<Point> validPoints;
        switch (mainShip.getSize()) {
            case 1 -> {
                validPoints = getValidStartPoint(mainShip);
                setShipKind(validPoints, mainShip.getSize());
                mainShip.addShipPositionPoint(validPoints);
                getMainShips().add(mainShip);
            }
            case 2 -> {
                do {
                    validPoints = getValidStartPoint(mainShip);
                } while (hasAlreadyShip(validPoints));
                mainShip.addShipPositionPoint(validPoints);
                mainShip.addShipPositionPoint(validPoints);
                setShipKind(validPoints, mainShip.getSize());
                getMainShips().add(mainShip);
            }
            case 3 -> {
                do {
                    validPoints = getValidStartPoint(mainShip);
                } while (hasAlreadyShip(validPoints));
                mainShip.addShipPositionPoint(validPoints);
                setShipKind(validPoints, mainShip.getSize());
                getMainShips().add(mainShip);
            }
        }
    }

    public Direction getRandomDirection() {
        int min = 1;
        int max = 2;
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (randomNumber == 1) {
            return Direction.HORIZONTAL;
        } else {
            return Direction.VERTICAL;
        }
    }

    public Shot getRandomShot() {
        return new Shot(getRandomChar(), getRandomInt());
    }

    private List<Point> getValidStartPoint(MainShip mainShip) {
        Point point;
        do {
            point = getRandomPoint();
        } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, mainShip.getDirection(), mainShip.getSize()));
        return getPlaygroundAsPlaygroundObject().getListOfValidPoints(point, mainShip.getDirection(), mainShip.getSize());
    }


    private char getRandomChar() {
        Random r = new Random();
        return (char) (r.nextInt(getPlaygroundSize()) + 'A');
    }

    private int getRandomInt() {
        Random number = new Random();
        return number.nextInt((getPlaygroundSize() - 1) + 1) + 1;
    }

    private Point getRandomPoint() {
        Point point;
        do {
            point = new Point(getRandomChar(), getRandomInt());
        } while (hasAlreadyShip(point));

        return point;
    }
}

