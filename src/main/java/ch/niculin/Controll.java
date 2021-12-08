package ch.niculin;

import java.util.Locale;
import java.util.Scanner;

public class Controll {

    Ship makeShip() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Buchstabe: ");
            char letter = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
            System.out.println("Zahl: ");
            int number = scanner.nextInt();
            return new Ship(letter, number);
    }

    Shot makeShot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buchstabe: ");
        char letter = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        System.out.println("Zahl: ");
        int number = scanner.nextInt();
        return new Shot(letter, number);
    }
}
