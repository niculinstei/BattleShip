package ch.niculin;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Playground {
    private final List<Point> playground;
    private final int size;

    public Playground(int size) {
        this.size = size;
        this.playground = initialisePlayground();

    }

    public int getSize() {
        return size;
    }

    public List<Point> getPlayground() {
        return playground;
    }

    /**
     * Return playground point from every point.
     *
     * @param inputPoint any point
     * @return corresponding within a playground field
     */
    public Optional<Point> getPlaygroundPoint(Point inputPoint){
        for (Point point: playground){
            if (point.equals(inputPoint)){
                return Optional.of(point);
            }
        }
        return Optional.empty();
    }

    public List<Point> getLine(int lineNumber){
        List<Point> line = new LinkedList<>();
        if (lineNumber < 0 || lineNumber > 11){
            throw new IllegalArgumentException();
        }
        int offset = (lineNumber - 1) * size;
        for (int i = offset; i < offset + size; i++){
            Point point = playground.get(i);
            line.add(point);
        }
        return line;
    }

    private List<Point> initialisePlayground() {
        List<Point> playground = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            fill(playground, i);
        }
        return playground;
    }

    private void fill(List<Point> playground, int number) {
        char letter = 'A';
        for (int i = letter; i < size + letter; i++) {
            Point p1 = new Point((char) i, number);
            p1.setFieldStatus(ShotStatus.EMPTY);
            playground.add(p1);
        }
    }

}
