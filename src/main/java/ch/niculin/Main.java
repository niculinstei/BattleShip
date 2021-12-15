package ch.niculin;

import static ch.niculin.Direction.SENKRECHT;

public class Main {
    public static void main(String[] args) {

        Controll controll = new Controll();

        AirCraftCarrier airCraftCarrier = new AirCraftCarrier(SENKRECHT);
        Playground playground = new Playground(6);

        playground.setMainShip(airCraftCarrier);
        System.out.println(playground);
        Shot shot = controll.makeShot();
        System.out.println(playground.shootShip(shot));
        Shot shot2 = controll.makeShot();
        System.out.println(playground.shootShip(shot2));
        playground.checkEnd();
        Shot shot3 = controll.makeShot();
        System.out.println(playground.shootShip(shot3));
        playground.checkEnd();



    }
}

