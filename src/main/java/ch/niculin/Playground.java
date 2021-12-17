package ch.niculin;

import java.util.LinkedList;
import java.util.List;

public class Playground {
    private final List<Point> playground;
    private final int size;

    public Playground(int size) {
        this.size = size;
        this.playground = initialisePlayground();

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
            p1.setFieldStatus(FieldStatus.EMPTY);
            playground.add(p1);
        }
    }

    public int getSize() {
        return size;
    }

    public List<String> playgroundGetPointX() {
        List<String> xList = new LinkedList<>();
        char x;
        for (int i = 0; i <= size - 1; i++) {
            x = (char) ('A' + i);
            String sx = Character.toString(x);
            xList.add(sx);
        }
        return xList;
    }

    public List<Point> getPlayground() {
        return playground;
    }
}
