package ch.niculin;

public class Shot {
    private final Point point;


    public Shot(char letter, int number){
        this.point = new Point(letter, number);
    }

    public Point getPoint() {
        return point;
    }

}
