package ch.niculin;

import java.util.List;

public class PlaygroundPrinter {

    public static void print(Playground playground){
        printXAxis(playground.getSize());
        printLines(playground);
    }

    private static void printXAxis(int size){
        char x;
        for (int i = 0; i < size; i++) {
            x = (char) ('A' + i);
            String sx = Character.toString(x);
            System.out.print(" " + sx + " ");
        }
        System.out.println();
    }

    private static void printLines(Playground playground){
        int size = playground.getSize();
        for(int j = 1; j <= size; j++){
            printLine(playground, j);
            System.out.println(j + " ");
        }
    }

    private static void printLine(Playground playground, int lineNumber){
        List<Point> line = playground.getLine(lineNumber);
        for(Point point : line){
            printField(point);
        }
    }

    private static void printField(Point point){
        switch (point.getFieldStatus()){
            case EMPTY -> System.out.print("|__");
            case SHIP -> System.out.print("|Δ_");
            case DESTROYED -> System.out.print("|†_");
            case FAILEDSHOT -> System.out.print("|≠_");
        }
    }
}
