package ch.niculin.player;

import ch.niculin.*;
import ch.niculin.ships.MainShip;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Player {
    private final Playground playground;
    private final String name;
    private final List<MainShip> mainShips;
    private final List<Point> hittedShipPoints;
    private final List<Point> destroyedShips;
    private final List<Point> failedShots;

    public Player(String name, Playground playground) {
        this.playground = playground;
        this.name = name;
        mainShips = new LinkedList<>();
        hittedShipPoints = new LinkedList<>();
        destroyedShips = new LinkedList<>();
        failedShots = new LinkedList<>();
    }

    public boolean shootShip(Shot shot, Playground playgroundOfOtherPlayer, List<MainShip> mainShipList) {
        Optional<Point> playgroundPoint = playgroundOfOtherPlayer.getPlaygroundPoint(shot.getPoint());

        if (playgroundPoint.isEmpty()) {
            return false;
        } else {
            for (MainShip mainShip : mainShipList) {
                if (mainShip.getShipPosition().contains(playgroundPoint.get())) {
                    mainShip.markPointAsShot(playgroundPoint.get());
                    if (mainShip.isDistroyed()) {
                        destroyedShips.addAll(mainShip.getShipPosition());
                    }
                    hittedShipPoints.add(playgroundPoint.get());
                    return true;
                }
            }
            failedShots.add(playgroundPoint.get());
            return false;
        }
    }

    public boolean hasShipsAlive() {
        for (MainShip mainShip : mainShips) {
            if (!mainShip.isDistroyed()) {
                return true;
            }
        }
        return false;
    }

     boolean hasAlreadyShip(Point point) {
        for (MainShip mainShip : getMainShips()) {
            if (mainShip.getShipPosition().contains(point)) {
                return true;
            }
        }
        return false;
    }

    boolean hasAlreadyShip(List<Point> points) {
        for (Point point : points){
            if (hasAlreadyShip(point)){
                return true;
            }
        }
        return false;
    }

    public void setShipKind(List<Point> points, int mainShipSize) {

        for (Point point : points){
            point.setFieldStatus(ShotStatus.SHIP);
            if (mainShipSize == 1) {
                point.setShipKind(ShipKind.RUBBERBOAT);
            } else if (mainShipSize == 2) {
                point.setShipKind(ShipKind.SAILINGBOAT);
            } else if (mainShipSize == 3) {
                point.setShipKind(ShipKind.AIRCRAFTCARRIER);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Playground getPlaygroundAsPlaygroundObject() {
        return playground;
    }

    public List<MainShip> getMainShips() {
        return mainShips;
    }

    public int getPlaygroundSize() {
        return playground.getSize();
    }

    public List<Point> getHittedShipPoints() {
        return hittedShipPoints;
    }

    public List<Point> getDestroyedShips() {
        return destroyedShips;
    }
    public List<Point> getFailedShots() {
        return failedShots;
    }
}
