package ch.niculin;

import ch.niculin.ships.MainShip;

import java.util.Locale;
import java.util.Scanner;

public class Controll extends MainShip {

    public Point getPoint(MainShip mainShip) {

        System.out.println("Where you want to place your " + mainShip);
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

    public int chooseGameMode(){
        System.out.println("Which mode do you want to play?\nSingleplayer 1\nMultyplayer 2");
        return new Scanner(System.in).nextInt();
    }

    public int chooseSize(){
        System.out.println("Choose the size of the field:\nBig 1\nMiddle 2\nLittle 3");
        int input = new Scanner(System.in).nextInt();
        if (input == 1){
            return 10;
        } else if (input == 2){
            return 8;
        } else return 5;
    }
}
