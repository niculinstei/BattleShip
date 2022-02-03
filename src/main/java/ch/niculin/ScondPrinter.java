package ch.niculin;

import java.util.List;

public class ScondPrinter {
    public static void secondPrint(Playground playground, List<Point> destroyedShips) {
        printXAxis(playground.getSize());
        printLines(playground, destroyedShips);
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

    private static void printLines(Playground playground, List<Point> destroyedShips) {
        int size = playground.getSize();
        for (int j = 1; j <= size; j++) {
            printLine(playground, j, destroyedShips);
            System.out.println(j + " ");
        }
    }

    private static void printLine(Playground playground, int lineNumber, List<Point> destroyedShips) {
        List<Point> line = playground.getLine(lineNumber);
        for (Point point : line) {
            printSecondField(point, destroyedShips);
        }
    }

    private static void printSecondField(Point point, List<Point> destroyedShips) {
        if (point.getShotStatus() == ShotStatus.SHIP && point.getShipKind() == ShipKind.RUBBERBOAT) {
            System.out.print("|¤_");
        } else if (point.getShotStatus() == ShotStatus.SHIP && point.getShipKind() == ShipKind.SAILINGBOAT) {
            System.out.print("|¥_");
        } else if (point.getShotStatus() == ShotStatus.SHIP && point.getShipKind() == ShipKind.AIRCRAFTCARRIER) {
            System.out.print("|Δ_");
        } else if (destroyedShips.contains(point)) {
            System.out.print("|‡_");
        } else if (point.getShotStatus() == ShotStatus.SHOT) {
            System.out.print("|†_");
        } else {
            System.out.print("|__");
        }
    }
}
