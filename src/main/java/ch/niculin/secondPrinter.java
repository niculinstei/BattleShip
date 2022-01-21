package ch.niculin;
import java.util.List;

public class secondPrinter {
    public static void secondPrint(Playground playground) {
        printXAxis(playground.getSize());
        printLines(playground);
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

    private static void printLines(Playground playground) {
        int size = playground.getSize();
        for (int j = 1; j <= size; j++) {
            printLine(playground, j);
            System.out.println(j + " ");
        }
    }

    private static void printLine(Playground playground, int lineNumber) {
        List<Point> line = playground.getLine(lineNumber);
        for (Point point : line) {
            printSecondField(point);
        }
    }

    private static void printSecondField (Point point){
        if (point.getFieldStatus() == FieldStatus.SHIP) {
            System.out.print("|Δ_");
        } else {
            System.out.print("|__");
        }
    }
}
