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
        originalPoint.setShipKind(ShipKind.AIRCRAFTCARRIER);
        if (airCraftCarrier.getDirection() == Direction.WAAGRECHT) {
            Point rightPointOfOrgiginalPoint = getRightPoint(originalPoint);
            airCraftCarrier.addShipPositionPoint(rightPointOfOrgiginalPoint);
            rightPointOfOrgiginalPoint.setFieldStatus(ShotStatus.SHIP);
            rightPointOfOrgiginalPoint.setShipKind(ShipKind.AIRCRAFTCARRIER);

            Point rightOfRight = getRightPoint(rightPointOfOrgiginalPoint);
            airCraftCarrier.addShipPositionPoint(rightOfRight);
            rightOfRight.setFieldStatus(ShotStatus.SHIP);
            rightOfRight.setShipKind(ShipKind.AIRCRAFTCARRIER);
            ships++;
        } else {
            Point lowerPointOfOrgiginalPoint = getLowerPoint(originalPoint);
            airCraftCarrier.addShipPositionPoint(lowerPointOfOrgiginalPoint);
            lowerPointOfOrgiginalPoint.setFieldStatus(ShotStatus.SHIP);
            lowerPointOfOrgiginalPoint.setShipKind(ShipKind.AIRCRAFTCARRIER);

            Point lowerOfLower = getLowerPoint(lowerPointOfOrgiginalPoint);
            airCraftCarrier.addShipPositionPoint(lowerOfLower);
            lowerOfLower.setFieldStatus(ShotStatus.SHIP);
            lowerOfLower.setShipKind(ShipKind.AIRCRAFTCARRIER);
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
            point1.setShipKind(ShipKind.SAILINGBOAT);
            Point rightPoint = getRightPoint(point1);
            sailingBoat.addShipPositionPoint(rightPoint);
            rightPoint.setFieldStatus(ShotStatus.SHIP);
            rightPoint.setShipKind(ShipKind.SAILINGBOAT);
            ships++;
        } else {
            sailingBoat.addShipPositionPoint(point1);
            getMainShips().add(sailingBoat);
            point1.setFieldStatus(ShotStatus.SHIP);
            point1.setShipKind(ShipKind.SAILINGBOAT);
            Point lowerPoint = getLowerPoint(point1);
            sailingBoat.addShipPositionPoint(lowerPoint);
            lowerPoint.setFieldStatus(ShotStatus.SHIP);
            lowerPoint.setShipKind(ShipKind.SAILINGBOAT);
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
        point.setShipKind(ShipKind.RUBBERBOAT);
        ships++;
        return ships;
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
        Point point1;
        Point point2;

        if (direction == Direction.WAAGRECHT) {
            do {
                do{
                        point = new Point(getRandomChar(), getRandomInt());
                } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, direction, 3));
                point1 = getRightPoint(point);
                point2 = getRightPoint(point1);
            } while (hasAlreadyShip(point) || hasAlreadyShip(point1) || hasAlreadyShip(point2));
            return point;
        } else {
            do {
                do {
                    point = new Point(getRandomChar(), getRandomInt());
                } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, direction, 3));
                point1 = getLowerPoint(point);
                point2 = getLowerPoint(point1);
            } while (hasAlreadyShip(point) || hasAlreadyShip(point1) || hasAlreadyShip(point2));
            return point;
        }
    }

    private Point getRandomPointsSailingBoat(Direction direction) {
        Point point;
        Point point1;
        if (direction == Direction.WAAGRECHT) {
            do {
                do {
                    point = new Point(getRandomChar(), getRandomInt());
                } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, direction, 2));
                point1 = getRightPoint(point);
            } while (hasAlreadyShip(point) || hasAlreadyShip(point1));
            return point;
        } else {
            do {
                do {
                    point = new Point(getRandomChar(), getRandomInt());
                } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, direction, 2));
                point1 = getLowerPoint(point);
            } while (hasAlreadyShip(point) || hasAlreadyShip(point1));
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

