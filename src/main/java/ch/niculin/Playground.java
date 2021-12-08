package ch.niculin;

import java.util.LinkedList;
import java.util.List;

public class Playground {
    private final List<Point> playground;
    private final List<Point> ships;

    public Playground() {
        this.playground = initialisePlayground();
        ships = new LinkedList<>();
    }

    public void setShip(Ship ship) {
        if (playground.contains(ship.getPoint())) {
            if (!ships.contains(ship.getPoint())) {
                ships.add(ship.getPoint());

            } else {
                throw new IllegalArgumentException("There is already a ship!");
            }
        } else {
            throw new IllegalArgumentException("is not inside!");
        }
    }


    public boolean shootShip(Shot shot) {
        if (playground.contains(shot.getPoint())) {
            if (ships.contains(shot.getPoint())) {
                ships.remove(shot.getPoint());
                return true;
            }
        }
        return false;
    }

    private List<Point> initialisePlayground() {
        List<Point> playground = new LinkedList<>();
        fill(playground, 'A');
        fill(playground, 'B');
        fill(playground, 'C');
        fill(playground, 'D');
        return playground;
    }

    private void fill(List<Point> playground, char letter) {
        for (int i = 1; i <= 4; i++) {
            Point p1 = new Point(letter, i);
            playground.add(p1);
        }
    }

    public boolean hasShips() {
        return ships.size() != 0;
    }
    public int countShips(){
        return ships.size();
    }

    @Override
    public String toString() {
        return "Playground{" +
                "playground=" + playground +
                "\nships=" + ships +
                '}';
    }
}
