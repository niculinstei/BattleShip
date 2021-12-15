package ch.niculin;

import java.util.Locale;
import java.util.Scanner;

public class Controll extends MainShip{

    Point getPoint(){

        System.out.println("Where you want to place your ship? ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buchstabe: ");
        char letter = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        System.out.println("Zahl: ");
        int number = scanner.nextInt();
        return new Point(letter, number);
    }

    Shot makeShot() {
        System.out.println("Where do you want to shoot? ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buchstabe: ");
        char letter = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        System.out.println("Zahl: ");
        int number = scanner.nextInt();
        return new Shot(letter, number);
    }

    int makePlayground() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("size of playground? ");
        return scanner.nextInt();

    }
}
