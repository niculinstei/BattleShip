package ch.niculin;

import static ch.niculin.PlaygroundPrinter.print;

public class Game {
    Controll controll = new Controll();
    Player player;
    Player player1;

    public String play() {
        createPlayground();
        startGame(player, player1);
        return getWinner().getName();
    }

    private void createPlayground() {
        Playground playground = new Playground(10);
        Playground playground2 = new Playground(10);
        createPlayer(playground, playground2);
        fillPlayground(player, player1);
        print(playground);
    }

    private void fillPlayground(Player player, Player player1) {
        player.setMainShip(new RubberBoat());
        player1.setMainShip(new RubberBoat());
    }

    private void startGame(Player player, Player player1) {
        boolean playerToggle = true;
        while (!player1.mainShips.isEmpty() && !player.mainShips.isEmpty()) {
            Player currentPlayer;
            if (playerToggle) {
                playerToggle = false;
                currentPlayer = player;
            } else {
                playerToggle = true;
                currentPlayer = player1;
            }
            if (currentPlayer.shootShip(controll.makeShot())) {
                System.out.println("Hitt!!!");
            } else {
                System.out.println("Faded!!!");
            }
        }
    }

    private void createPlayer(Playground playground, Playground playground2) {
        player = new Player("Hans", playground);
        player1 = new Player("Max", playground2);
    }

    private Player getWinner(){
        print(player1.playground);
        if (player.mainShips.isEmpty()){
            return player;
        }else{
            return player1;
        }
    }
}
