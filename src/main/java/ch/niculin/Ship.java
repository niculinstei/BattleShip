package ch.niculin;

public class Ship {
    private final Point point1;

    public Ship(char letter, int number) {
        this.point1 = new Point(letter, number);
    }

    public Point getPoint1() {
        return point1;
    }
}
