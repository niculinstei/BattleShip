package ch.niculin;

import static ch.niculin.PlaygroundPrinter.print;

public class Game {
    Controll controll = new Controll();
    private final static int MAP_SIZE = 4;
    int mode;

    public String play() {
        mode = controll.chooseGameMode();
        return createPlayground(mode);
    }

    private String createPlayground(int mode) {
        switch (mode) {
            case 1 -> {
                Playground playground = new Playground(MAP_SIZE);
                Playground playground1 = new Playground(MAP_SIZE);
                Human player = new Human("Peter", playground);
                Computer computer = new Computer(playground1);
                player.setMainShip(new RubberBoat());
                computer.generateShips();
                startGame(player, computer);
                return getWinner(player, computer);
            }
            case 2 -> {
                Playground playground = new Playground(MAP_SIZE);
                Playground playground2 = new Playground(MAP_SIZE);
                Human player = new Human("Hans", playground);
                Human player1 = new Human("Max", playground2);
                fillPlayground(player, player1);
                print(playground);
                startGame(player, player1);
                return getWinner(player, player1);
            }
        }
        return " ";
    }

    private void fillPlayground(Human player, Human player1) {
        player.setMainShip(new RubberBoat());
        player1.setMainShip(new RubberBoat());
    }

    private void startGame(Player player, Player player1) {
        boolean playerToggle = true;
        while (!player1.getMainShips().isEmpty() && !player.getMainShips().isEmpty()) {
            Player currentPlayer;
            if (playerToggle) {
                playerToggle = false;
                currentPlayer = player;
            } else {
                playerToggle = true;
                currentPlayer = player1;
            }
            if (mode == 2) {
                if (currentPlayer.shootShip(controll.makeShot())) {
                    System.out.println("Hitt!!!");
                } else {
                    System.out.println("Faded!!!");
                }
            } else{
                if (currentPlayer.equals(player1)){
                    if (currentPlayer.shootShip(currentPlayer.getRandomShot())) {
                        System.out.println("\nComputer:\nHitt!!!");
                    } else {
                        System.out.println("\nComputer:\nFaded!!!\n");
                    }
                } else{
                    if (currentPlayer.shootShip(controll.makeShot())) {
                        System.out.println("Hitt!!!");
                    } else {
                        System.out.println("Faded!!!");
                    }
                }
            }

        }
    }


    private String getWinner(Player player, Player player1) {
        print(player1.getPlayground());
        if (player.getMainShips().isEmpty()) {
            return player1.getName();
        } else {
            return player.getName();
        }
    }
}
