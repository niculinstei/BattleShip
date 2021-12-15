package ch.niculin;

import java.util.LinkedList;
import java.util.List;
public class Playground {
    Controll controll = new Controll();
    private final List<Point> playground;
    List<MainShip> mainShips;
    private final int size;

    public Playground(int size) {
        this.size = size;
        this.playground = initialisePlayground();
        mainShips = new LinkedList<>();
    }


    public void setMainShip(MainShip mainShip) {
        switch (mainShip.getSize()) {
            case 1 -> {
                Point point = controll.getPoint();
                if (playground.contains(point) && !hasAlreadyShip(point)) {
                    mainShip.addShipPositionPoint(point);
                } else if (hasAlreadyShip(point)) {
                    throw new IllegalArgumentException("There is already a ship!");
                } else {
                    throw new IllegalArgumentException("Not inside!");
                }
            }
            case 2 -> {
                Point point = controll.getPoint();
                switch (mainShip.getDirection()) {
                    case WAAGRECHT -> {
                        if (playground.contains(point) && playground.contains(getRightPoint(point)) && !hasAlreadyShip(point) && !hasAlreadyShip(getRightPoint(point))) {
                            mainShip.addShipPositionPoint(point);
                            mainShip.addShipPositionPoint(getRightPoint(point));
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(getRightPoint(point))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                    case SENKRECHT -> {
                        if (playground.contains(point) && playground.contains(getLowerPoint(point)) && !hasAlreadyShip(point) && !hasAlreadyShip(getLowerPoint(point))) {
                            mainShip.addShipPositionPoint(point);
                            mainShip.addShipPositionPoint(getLowerPoint(point));
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
                Point point = controll.getPoint();
                switch (mainShip.getDirection()) {
                    case WAAGRECHT -> {
                        Point rightPoint = getRightPoint(point);
                        if (playground.contains(point) && playground.contains(rightPoint) && playground.contains(getRightPoint(rightPoint)) && !hasAlreadyShip(point) && !hasAlreadyShip(rightPoint) && !hasAlreadyShip(getRightPoint(rightPoint))) {
                            mainShip.addShipPositionPoint(point);
                            mainShip.addShipPositionPoint(getRightPoint(rightPoint));
                            mainShip.addShipPositionPoint(rightPoint);
                            mainShips.add(mainShip);
                        } else if (hasAlreadyShip(point) || hasAlreadyShip(getRightPoint(point)) || hasAlreadyShip(getRightPoint(rightPoint))) {
                            throw new IllegalArgumentException("There is already a ship!");
                        } else {
                            throw new IllegalArgumentException("Not inside!");
                        }
                    }
                    case SENKRECHT -> {
                        Point lowerPoint = getLowerPoint(point);
                        if (playground.contains(point) && playground.contains(lowerPoint) && playground.contains(getLowerPoint(lowerPoint)) && !hasAlreadyShip(point) && !hasAlreadyShip(getLowerPoint(point)) && !hasAlreadyShip(getLowerPoint(lowerPoint))) {
                            mainShip.addShipPositionPoint(point);
                            mainShip.addShipPositionPoint(getLowerPoint(lowerPoint));
                            mainShip.addShipPositionPoint(lowerPoint);
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

    public String shootShip(Shot shot){

        if (hasAlreadyShip(shot)){
            return "Hit!!";
        }
        return "Faded!!";
    }

    private boolean hasAlreadyShip(Point point){
        for (MainShip mainShip : mainShips){
            if (mainShip.shipPosition.contains(point)){
                return true;
            }
        }
        return false;
    }

    private boolean hasAlreadyShip(Shot shot){
        Point point = shot.getPoint();
        for (MainShip mainShip : mainShips){
            if (mainShip.shipPosition.contains(point)){
                mainShip.shipPosition.remove(point);
                return true;
            }
        }
        return false;
    }

    public void checkEnd(){
            for (MainShip mainShip: mainShips){
                if (mainShip.shipPosition.isEmpty()){
                    System.out.println("Du hasst ein Schiff zerstört!");
                    mainShips.remove(mainShip);
                    if (mainShips.isEmpty()){
                        System.out.println("Alles Schiffe gefunden!!");
                        System.exit(0);
                    }
                }
            }

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

    private List<Point> initialisePlayground() {
        List<Point> playground = new LinkedList<>();
        char letter = 'A';
        for (int i = 0; i < size; i++) {
            fill(playground, letter);
            letter++;
        }
        return playground;
    }

    private void fill(List<Point> playground, char letter) {
        for (int i = 1; i <= size; i++) {
            Point p1 = new Point(letter, i);
            playground.add(p1);
        }
    }

    @Override
    public String toString() {
        return "Playground{" +
                "playground=\n    " + playgroundGetPointX() +
                "\n" + playgroundGetPointY() +
                "\nships=" + mainShips +
                '}';
    }

    public int getSize() {
        return size;
    }

    private List<String> playgroundGetPointX() {
        List<String> xList = new LinkedList<>();
        char x;
        for (int i = 0; i <= size - 1; i++) {
            x = (char) ('A' + i);
            String sx = Character.toString(x);
            xList.add(sx);
        }
        return xList;
    }

    private List<String> playgroundGetPointY() {
        List<String> yList = new LinkedList<>();
        int subtrahend = size - 1;
        while (subtrahend >= 0) {
            int y = size - subtrahend;
            yList.add(y + "\n");
            subtrahend--;
        }
        return yList;
    }
}
