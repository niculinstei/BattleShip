package ch.niculin;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Player {
    private final String name;
    Playground playground;
    List<Point> battleground;
    Controll controll = new Controll();
    List<MainShip> mainShips;


    public Player(String name, Playground playground){
        this.name = name;
        this.playground = playground;
        battleground = playground.getPlayground();
        mainShips = new LinkedList<>();
    }

    public void setMainShip(MainShip mainShip) {
        switch (mainShip.getSize()) {
            case 1 -> {
                Point inputPoint = controll.getPoint(mainShip);
                Optional<Point> playgroundPoint = playground.getPlaygroundPoint(inputPoint);
                Point point;
                if (playgroundPoint.isPresent()){
                    point = playgroundPoint.get();
                }else{
                    throw new IllegalArgumentException("Invalid inpunt");
                }
                if (battleground.contains(point) && !hasAlreadyShip(point)) {
                    point.setFieldStatus(FieldStatus.SHIP);
                    mainShip.addShipPositionPoint(point);
                    mainShips.add(mainShip);
                } else if (hasAlreadyShip(point)) {
                    throw new IllegalArgumentException("There is already a ship!");
                } else {
                    throw new IllegalArgumentException("Not inside!");
                }
            }
            case 2 -> {
                Point point = controll.getPoint(mainShip);
                switch (mainShip.getDirection()) {
                    case WAAGRECHT -> {
                        if (battleground.contains(point) && battleground.contains(getRightPoint(point)) && !hasAlreadyShip(point) && !hasAlreadyShip(getRightPoint(point))) {
                            mainShip.addShipPositionPoint(point);
                            point.setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(getRightPoint(point));
                            getRightPoint(point).setFieldStatus(FieldStatus.SHIP);
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(getRightPoint(point))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                    case SENKRECHT -> {
                        if (battleground.contains(point) && battleground.contains(getLowerPoint(point)) && !hasAlreadyShip(point) && !hasAlreadyShip(getLowerPoint(point))) {
                            mainShip.addShipPositionPoint(point);
                            point.setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(getLowerPoint(point));
                            getLowerPoint(point).setFieldStatus(FieldStatus.SHIP);
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(getLowerPoint(point))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                }
            }
            case 3 -> {
                Point point = controll.getPoint(mainShip);
                switch (mainShip.getDirection()) {
                    case WAAGRECHT -> {
                        Point rightPoint = getRightPoint(point);
                        if (battleground.contains(point) && battleground.contains(rightPoint) && battleground.contains(getRightPoint(rightPoint)) && !hasAlreadyShip(point) && !hasAlreadyShip(rightPoint) && !hasAlreadyShip(getRightPoint(rightPoint))) {
                            mainShip.addShipPositionPoint(point);
                            point.setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(getRightPoint(rightPoint));
                            getRightPoint(rightPoint).setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(rightPoint);
                            rightPoint.setFieldStatus(FieldStatus.SHIP);
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(getRightPoint(point)) || hasAlreadyShip(getRightPoint(rightPoint))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                    case SENKRECHT -> {
                        Point lowerPoint = getLowerPoint(point);
                        if (battleground.contains(point) && battleground.contains(lowerPoint) && battleground.contains(getLowerPoint(lowerPoint)) && !hasAlreadyShip(point) && !hasAlreadyShip(getLowerPoint(point)) && !hasAlreadyShip(getLowerPoint(lowerPoint))) {
                            mainShip.addShipPositionPoint(point);
                            point.setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(lowerPoint);
                            lowerPoint.setFieldStatus(FieldStatus.SHIP);
                            mainShip.addShipPositionPoint(getLowerPoint(lowerPoint));
                            getLowerPoint(lowerPoint).setFieldStatus(FieldStatus.SHIP);
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(lowerPoint) || hasAlreadyShip(getLowerPoint(lowerPoint))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                }
            }
        }
    }
    public boolean shootShip(Shot shot){
        return hasAlreadyShipShot(shot);
    }

    public String getName() {
        return name;
    }

    private Point getRightPoint(Point point) {
        char x = point.getX();
        x++;
        return new Point(x, point.getY());
    }

    private Point getLowerPoint(Point point) {
        int y = point.getY();
        y++;
        return new Point(point.getX(), y);
    }

    private boolean hasAlreadyShip(Point point){
        for (MainShip mainShip : mainShips){
            if (mainShip.shipPosition.contains(point)){
                return true;
            }
        }
        return false;
    }

    private boolean hasAlreadyShipShot(Shot shot){
        Point point = shot.getPoint();
        for (MainShip mainShip : mainShips){
            if (mainShip.shipPosition.contains(point)){
                mainShip.shipPosition.remove(point);
                point.setFieldStatus(FieldStatus.DESTROYED);
                if (mainShip.shipPosition.isEmpty()){
                    mainShips.remove(mainShip);
                }
                return true;
            }
        }
        point.setFieldStatus(FieldStatus.FAILEDSHOT);
        return false;
    }
}
