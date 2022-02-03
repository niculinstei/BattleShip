package ch.niculin.player;

import ch.niculin.Direction;
import ch.niculin.Playground;
import ch.niculin.Point;
import ch.niculin.Shot;
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

    public boolean shootShip(Shot shot, Playground playgrounOfOtherPlayer, List<MainShip> mainShipList) {
        Optional<Point> playgroundPoint = playgrounOfOtherPlayer.getPlaygroundPoint(shot.getPoint());

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

    public Point getLowerPoint(Point point) {
        int y = point.getY();
        y++;
        Point point1 = new Point(point.getX(), y);
        Optional<Point> point2 = getPlaygroundPoint(point1);
        Point pointToReturn;
        if (point2.isPresent()) {
            pointToReturn = point2.get();
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }

        return pointToReturn;
    }


    public Point getRightPoint(Point point) {
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

    public Point getNextPoint (Point point ,Direction direction){
        if (direction == Direction.WAAGRECHT) {
            return getRightPoint(point);
        } else {
            return getLowerPoint(point);
        }
    }

    public String getName() {
        return name;
    }

    public List<Point> getPlayground() {
        return playground.getPlayground();
    }

    public Playground getPlaygroundAsPlaygroundObject() {
        return playground;
    }

    public Optional<Point> getPlaygroundPoint(Point inputPoint) {
        return playground.getPlaygroundPoint(inputPoint);
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
