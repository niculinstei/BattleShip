package ch.niculin;

import static ch.niculin.Direction.SENKRECHT;
import static ch.niculin.Direction.WAAGRECHT;

public class Main {
    public static void main(String[] args) {
Player player = new Player("Hans");
        Controll controll = new Controll();

        Playground playground = new Playground(10, "Hans");
        Playground playground2 = new Playground(10, "Max");
        playground.setMainShip(new RubberBoat());
        playground2.setMainShip(new RubberBoat());
        boolean playerToggle = true;
        while (!playground2.mainShips.isEmpty() && !playground.mainShips.isEmpty()){
            Playground currentPlayer;
            if (playerToggle){
                playerToggle = false;
                currentPlayer = playground;
            } else{
               playerToggle = true;
               currentPlayer = playground2;
            }
            if (currentPlayer.shootShip(controll.makeShot())){
                System.out.println("Hitt!!!");
            }else{
                System.out.println("Faded!!!");
            }
        }
        if (playground.mainShips.isEmpty()){
            System.out.println(playground2.getName() + " Hat gewonnen!");
        }else{
            System.out.println(playground.getName() + " Hat gewonnen!");
        }
    }
}

