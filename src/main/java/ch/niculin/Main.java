package ch.niculin;

public class Main {
    public static void main(String[] args) {
        Controll controll = new Controll();
        Playground playground = new Playground(10);
        Playground playground2 = new Playground(10);
        Player player = new Player("Hans", playground);
        Player player1 = new Player("Max", playground2);
        player.setMainShip(new RubberBoat());
        player1.setMainShip(new RubberBoat());
        boolean playerToggle = true;
        while (!player1.mainShips.isEmpty() && !player.mainShips.isEmpty()){
            Player currentPlayer;
            if (playerToggle){
                playerToggle = false;
                currentPlayer = player;
            } else{
               playerToggle = true;
               currentPlayer = player1;
            }
            if (currentPlayer.shootShip(controll.makeShot())){
                System.out.println("Hitt!!!");
            }else{
                System.out.println("Faded!!!");
            }
        }
        if (player.mainShips.isEmpty()){
            System.out.println(player.getName() + " Hat gewonnen!");
        }else{
            System.out.println(player1.getName() + " Hat gewonnen!");
        }
    }
}

