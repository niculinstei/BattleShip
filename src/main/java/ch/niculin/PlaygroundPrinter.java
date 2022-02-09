package ch.niculin;

import java.util.List;

public class PlaygroundPrinter {

    public static void print(Playground playground, List<Point> hitOpponentPoints, List<Point> destroyedOpponentShips, List<Point> playerFailedShots) {
        printXAxis(playground.getSize());
        printLines(playground, hitOpponentPoints, destroyedOpponentShips, playerFailedShots);
    }

    private static void printXAxis(int size) {
        char x;
        for (int i = 0; i < size; i++) {
            x = (char) ('A' + i);
            String sx = Character.toString(x);
            System.out.print(" " + sx + " ");
        }
        System.out.println();
    }

    private static void printLines(Playground playground, List<Point> hittedPoints, List<Point> destoryedShips, List<Point> faildShots) {
        int size = playground.getSize();
        for (int j = 1; j <= size; j++) {
            printLine(playground, j, hittedPoints, destoryedShips, faildShots);
            System.out.println(j + " ");
        }
    }

    private static void printLine(Playground playground, int lineNumber, List<Point> hittedPoints, List<Point> destoryedShips, List<Point> faildShots) {
        List<Point> line = playground.getLine(lineNumber);
        for (Point point : line) {
            printField(point, hittedPoints, destoryedShips, faildShots);
        }
    }

    private static void printField(Point point, List<Point> hittedPoints, List<Point> destoryedShips, List<Point> faildShots) {
        if (destoryedShips.contains(point)) {
            System.out.print("|‡_");
        } else if (hittedPoints.contains(point)&& !destoryedShips.contains(point)) {
            System.out.print("|†_");
        } else if (faildShots.contains(point)) {
            System.out.print("|≠_");
        } else {
            System.out.print("|__");
        }
    }
}

