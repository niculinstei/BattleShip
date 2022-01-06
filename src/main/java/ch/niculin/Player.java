package ch.niculin;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Player {
    private final Playground playground;
    private final String name;

    private final List<MainShip> mainShips;

    public Player(String name, Playground playground) {
        this.name = name;
        mainShips = new LinkedList<>();
        this.playground = new Playground(playground.getSize());
    }

    public abstract boolean shootShip(Shot shot);

    public String getName() {
        return name;
    }

    protected char getRandomChar() {
        Random r = new Random();
        return (char) (r.nextInt(playground.getSize()) + 'A');
    }

    protected int getRandomInt() {
        Random number = new Random();
        return number.nextInt((playground.getSize() - 1) + 1) + 1;
    }
    protected Shot getRandomShot() {
        return new Shot(getRandomChar(), getRandomInt());
    }
    public Playground getPlayground() {
        return playground;
    }

    public List<MainShip> getMainShips() {
        return mainShips;
    }

}
