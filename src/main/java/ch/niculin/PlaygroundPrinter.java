package ch.niculin;

import ch.niculin.ships.MainShip;

import java.util.List;

public class PlaygroundPrinter {

    public static void print(Playground playground, List<Point> hittedPoints, List<MainShip> destoryedShips, List<Point> faildShots) {
        printXAxis(playground.getSize());
        printLines(playground, hittedPoints, destoryedShips, faildShots);
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

    private static void printLines(Playground playground, List<Point> hittedPoints, List<MainShip> destoryedShips, List<Point> faildShots) {
        int size = playground.getSize();
        for (int j = 1; j <= size; j++) {
            printLine(playground, j, hittedPoints, destoryedShips, faildShots);
            System.out.println(j + " ");
        }
    }

    private static void printLine(Playground playground, int lineNumber, List<Point> hittedPoints, List<MainShip> destoryedShips, List<Point> faildShots) {
        List<Point> line = playground.getLine(lineNumber);
        for (Point point : line) {
            printField(point, hittedPoints, destoryedShips, faildShots);
        }
    }

    private static void printField(Point point, List<Point> hittedPoints, List<MainShip> destoryedShips, List<Point> faildShots) {
        if (checkDestruction(point, destoryedShips)) {
            System.out.print("|‡_");
        } else if (hittedPoints.contains(point)) {
            System.out.print("|†_");
        } else if (faildShots.contains(point)) {
            System.out.print("|≠_");
        } else {
            System.out.print("|__");
        }
    }

    private static boolean checkDestruction (Point point, List < MainShip > destoryedShips){
        for (MainShip mainShip : destoryedShips) {
            return mainShip.getShipPosition().contains(point);
        }
        return false;
    }
}

    /*        switch (point.getFieldStatus()){
            case EMPTY -> System.out.print("|__");
            case SHIP -> System.out.print("|Δ_");
            //case DESTROYED -> System.out.print("|†_");
            case FAILEDSHOT -> System.out.print("|≠_");
        }*/
