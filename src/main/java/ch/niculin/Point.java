package ch.niculin;

import java.util.Objects;

public class Point {
    private final char x;
    private final int y;
    private ShotStatus shotStatus;

    public Point(char x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ShotStatus getShotStatus() {
        return shotStatus;
    }

    public void setFieldStatus(ShotStatus shotStatus) {
        this.shotStatus = shotStatus;
    }
}
