package ch.niculin;

import ch.niculin.player.Computer;
import ch.niculin.player.Human;
import ch.niculin.player.Player;
import ch.niculin.ships.RubberBoat;

import static ch.niculin.PlaygroundPrinter.print;
import static ch.niculin.secondPrinter.secondPrint;

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
                secondPrint(player.getPlaygroundAsPlaygroundObject());
                secondPrint(computer.getPlaygroundAsPlaygroundObject());
                startGame(player, computer);
                return getWinner(player, computer);
            }
            case 2 -> {
                Playground playground = new Playground(MAP_SIZE);
                Playground playground2 = new Playground(MAP_SIZE);
                Human player = new Human("Hans", playground);
                Human player1 = new Human("Max", playground2);
                fillPlayground(player, player1);
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
        while (player1.hasShipsAlive() && player.hasShipsAlive()) {
            Player currentPlayer;
            Player otherPlayer;
            if (playerToggle) {
                playerToggle = false;
                currentPlayer = player;
                otherPlayer = player1;
            } else {
                playerToggle = true;
                currentPlayer = player1;
                otherPlayer = player;
            }
            shotControll(currentPlayer, otherPlayer);
            System.out.println(player.getName() + " Playgrounds");
            print(player.getPlaygroundAsPlaygroundObject(), player.getHittedShipPoints(), player.getDestroyedShips(), player.getFaildShots());
            secondPrint(player.getPlaygroundAsPlaygroundObject());
            System.out.println(player1.getName() + " Playgrounds");
            print(player1.getPlaygroundAsPlaygroundObject(), player1.getHittedShipPoints(), player1.getDestroyedShips(), player1.getFaildShots());
            secondPrint(player1.getPlaygroundAsPlaygroundObject());
        }
    }

    private void shotControll(Player currentPlayer, Player otherPlaer) {
        Shot shot;
        if (currentPlayer instanceof Computer) {
            shot = ((Computer) currentPlayer).getRandomShot();
        } else {
            shot = controll.makeShot();
        }
        if (currentPlayer.shootShip(shot, otherPlaer.getPlaygroundAsPlaygroundObject(), otherPlaer.getMainShips())) {
            System.out.println(currentPlayer.getName() + " Hit!!!");
        } else {
            System.out.println(currentPlayer.getName() + " Missed!!!");
        }
    }

    private String getWinner(Player player, Player player1) {
        if (!player.hasShipsAlive()) {
            return player1.getName();
        } else {
            return player.getName();
        }
    }
}
