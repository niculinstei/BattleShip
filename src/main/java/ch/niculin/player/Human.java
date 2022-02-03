package ch.niculin.player;

import ch.niculin.*;
import ch.niculin.ships.MainShip;

import java.util.List;

public class Human extends Player {
    Controll controll = new Controll();

    public Human(String name, Playground playground) {
        super(name, playground);
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

    private List<Point> getValidStartPoint(MainShip mainShip) {
        Point point;
        do {
            point = controll.getPoint(mainShip);
        } while (!getPlaygroundAsPlaygroundObject().arePointsInField(point, mainShip.getDirection(), mainShip.getSize()));
        return getPlaygroundAsPlaygroundObject().getListOfValidPoints(point, mainShip.getDirection(), mainShip.getSize());
    }
}
