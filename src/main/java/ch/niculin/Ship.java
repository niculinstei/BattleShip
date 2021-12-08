package ch.niculin;

public class Ship {
    private final Point point;

    public Ship(char letter, int number){
        this.point = new Point(letter, number);
    }


    public Point getPoint() {
        return point;
    }

}
