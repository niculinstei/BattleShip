package ch.niculin.player;

import ch.niculin.*;
import ch.niculin.ships.AirCraftCarrier;
import ch.niculin.ships.RubberBoat;
import ch.niculin.ships.SailingBoat;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Computer extends Player {

    public Computer(Playground playground) {
        super("Computer1", playground);
    }

    public void generateShips(int shipQuantity) {
        placeShip(shipQuantity);
    }

    public Shot getRandomShot() {
        return new Shot(getRandomChar(), getRandomInt());
    }

    private void placeShip(int shipQuantity) {
        int ships = 0;
        while (ships < shipQuantity) {

            //rubberboat
            ships = placeRubberboat(ships);
            if (ships > shipQuantity) {
                break;
            }
            //saillingboat
            ships = placeSailingBoat(ships);
            if (ships > shipQuantity) {
                break;
            }
            //aircraftcarrier
            ships = placeAirCraftCarier(ships);
        }
    }

    private int placeAirCraftCarier(int ships) {
        AirCraftCarrier airCraftCarrier = new AirCraftCarrier(getRandomDirection());
        Direction direction = airCraftCarrier.getDirection();
        Point firstPoint = getRandomPointsAirCraftcarrier(direction);
        Optional<Point> optionalPoint = getPlaygroundPoint(firstPoint);
        Point originalPoint;
        if (optionalPoint.isPresent()) {
            originalPoint = optionalPoint.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }
        airCraftCarrier.addShipPositionPoint(originalPoint);
        getMainShips().add(airCraftCarrier);
        originalPoint.setFieldStatus(ShotStatus.SHIP);
        if (airCraftCarrier.getDirection() == Direction.WAAGRECHT) {
            Point rightPointOfOrgiginalPoint = getRightPoint(originalPoint);
            airCraftCarrier.addShipPositionPoint(rightPointOfOrgiginalPoint);
            rightPointOfOrgiginalPoint.setFieldStatus(ShotStatus.SHIP);

            Point rightOfRight = getRightPoint(rightPointOfOrgiginalPoint);
            airCraftCarrier.addShipPositionPoint(rightOfRight);
            rightOfRight.setFieldStatus(ShotStatus.SHIP);
            ships++;
        } else {
            Point lowerPointOfOrgiginalPoint = getLowerPoint(originalPoint);
            airCraftCarrier.addShipPositionPoint(lowerPointOfOrgiginalPoint);
            lowerPointOfOrgiginalPoint.setFieldStatus(ShotStatus.SHIP);

            Point lowerOfLower = getLowerPoint(lowerPointOfOrgiginalPoint);
            airCraftCarrier.addShipPositionPoint(lowerOfLower);
            lowerOfLower.setFieldStatus(ShotStatus.SHIP);
            ships++;
        }
        return ships;
    }

    private int placeSailingBoat(int ships) {
        SailingBoat sailingBoat = new SailingBoat(getRandomDirection());
        Direction direction = sailingBoat.getDirection();
        Point point1created = getRandomPointsSailingBoat(direction);
        Optional<Point> point2 = getPlaygroundPoint(point1created);
        Point point1;
        if (point2.isPresent()) {
            point1 = point2.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }
        if (sailingBoat.getDirection() == Direction.WAAGRECHT) {
            sailingBoat.addShipPositionPoint(point1);
            getMainShips().add(sailingBoat);
            point1.setFieldStatus(ShotStatus.SHIP);
            Point rightPoint = getRightPoint(point1);
            sailingBoat.addShipPositionPoint(rightPoint);
            rightPoint.setFieldStatus(ShotStatus.SHIP);
            ships++;
        } else {
            sailingBoat.addShipPositionPoint(point1);
            getMainShips().add(sailingBoat);
            point1.setFieldStatus(ShotStatus.SHIP);
            Point lowerPoint = getLowerPoint(point1);
            sailingBoat.addShipPositionPoint(lowerPoint);
            lowerPoint.setFieldStatus(ShotStatus.SHIP);
            ships++;
        }
        return ships;
    }

    private int placeRubberboat(int ships) {
        Point createdPoint = getRandomPoint();
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
        point.setFieldStatus(ShotStatus.SHIP);
        ships++;
        return ships;
    }
//Zuerst das grösste schiff
//die schiffplatzieren in methoden machen damit mehr übersicht
//das problem isst das nur der erste punkt geprüft wird und somit die schiffe sich überscheiden, darum auch das grösste zuerst,

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

    private Point getRandomPoint() {
        Point point;
        do {
            point = new Point(getRandomChar(), getRandomInt());
        } while (hasAlreadyShip(point));

        return point;
    }

    private Point getRandomPointsAirCraftcarrier(Direction direction) {
        Point point;
        Point Point1;
        Point Point2;
        if (direction == Direction.WAAGRECHT) {
            do {
                point = new Point(getRandomChar(), getRandomInt());
                Point1 = getRightPoint(point);
                Point2 = getRightPoint(Point1);
            } while (hasAlreadyShip(point) || hasAlreadyShip(Point1) || hasAlreadyShip(Point2));
            return point;
        } else {
            do {
                point = new Point(getRandomChar(), getRandomInt());
                Point1 = getLowerPoint(point);
                Point2 = getLowerPoint(Point1);
            } while (hasAlreadyShip(point) || hasAlreadyShip(Point1) || hasAlreadyShip(Point2));
            return point;
        }
    }

    private Point getRandomPointsSailingBoat(Direction direction) {
        Point point;
        Point Point1;
        if (direction == Direction.WAAGRECHT) {
            do {
                point = new Point(getRandomChar(), getRandomInt());
                Point1 = getRightPoint(point);

            } while (hasAlreadyShip(point) || hasAlreadyShip(Point1));
            return point;
        } else {
            do {
                point = new Point(getRandomChar(), getRandomInt());
                Point1 = getLowerPoint(point);
            } while (hasAlreadyShip(point) || hasAlreadyShip(Point1));
            return point;
        }
    }

    private Direction getRandomDirection() {
        int min = 1;
        int max = 2;
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (randomNumber == 1) {
            return Direction.WAAGRECHT;
        } else {
            return Direction.SENKRECHT;
        }

    }
}

