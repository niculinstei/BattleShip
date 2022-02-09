package ch.niculin;

import ch.niculin.player.Computer;
import ch.niculin.player.Human;
import ch.niculin.player.Player;
import ch.niculin.ships.AirCraftCarrier;
import ch.niculin.ships.RubberBoat;
import ch.niculin.ships.SailingBoat;
import static ch.niculin.PlaygroundPrinter.print;
import static ch.niculin.ScondPrinter.secondPrint;

public class Game {
    Controll controll = new Controll();
    static int MAP_SIZE;
    int mode;

    public String play() {
        mode = controll.chooseGameMode();
        MAP_SIZE = controll.chooseSize();
        return createPlayground(mode);
    }

    private String createPlayground(int mode) {

        switch (mode) {
            case 1 -> {
                Playground humanPlayground = new Playground(MAP_SIZE);
                Playground computerPlayground = new Playground(MAP_SIZE);
                Human player = new Human("You", humanPlayground);
                Computer computer = new Computer(computerPlayground);
                setShipHuman(player);
                setShipComuter(computer);
                secondPrint(player.getPlaygroundAsPlaygroundObject(), player.getDestroyedShips());
                secondPrint(computer.getPlaygroundAsPlaygroundObject(), computer.getDestroyedShips());
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
            if (currentPlayer instanceof Computer) {
                System.out.println(player.getName() + " Playgrounds");
                print(player.getPlaygroundAsPlaygroundObject(), player.getHittedShipPoints(), player.getDestroyedShips(), player.getFailedShots());
                System.out.println(player1.getName() + " Playgrounds");
                print(player1.getPlaygroundAsPlaygroundObject(), player1.getHittedShipPoints(), player1.getDestroyedShips(), player1.getFailedShots());
                secondPrint(player1.getPlaygroundAsPlaygroundObject(), player1.getDestroyedShips());
            }
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

    private void setShipHuman(Human player) {

        if (MAP_SIZE == 5) {
            player.setMainShip(new RubberBoat());
            player.setMainShip(new SailingBoat(controll.getUserDirection("Sailingboat(2)")));
        } else if (MAP_SIZE == 8) {
            player.setMainShip(new RubberBoat());
            player.setMainShip(new RubberBoat());
            player.setMainShip(new SailingBoat(controll.getUserDirection("Sailingboat(2)")));
            player.setMainShip(new AirCraftCarrier(controll.getUserDirection("Aircraftcaririer(3)")));
        } else if (MAP_SIZE == 10) {
            for (int i = 0; i < 2; i++) {
                player.setMainShip(new RubberBoat());
                player.setMainShip(new SailingBoat(controll.getUserDirection("Sailingboat(2)")));
                player.setMainShip(new AirCraftCarrier(controll.getUserDirection("Aircraftcaririer(3)")));
            }
        }
    }

    private void setShipComuter(Computer computer) {

        if (MAP_SIZE == 5) {
            computer.setMainShip(new RubberBoat());
            computer.setMainShip(new SailingBoat(computer.getRandomDirection()));
        } else if (MAP_SIZE == 8) {
            computer.setMainShip(new RubberBoat());
            computer.setMainShip(new RubberBoat());
            computer.setMainShip(new SailingBoat(computer.getRandomDirection()));
            computer.setMainShip(new AirCraftCarrier(computer.getRandomDirection()));
        } else if (MAP_SIZE == 10) {
            for (int i = 0; i < 2; i++) {
                computer.setMainShip(new RubberBoat());
                computer.setMainShip(new SailingBoat(computer.getRandomDirection()));
                computer.setMainShip(new AirCraftCarrier(computer.getRandomDirection()));
            }
        }
    }
}
