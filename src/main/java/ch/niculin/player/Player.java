package ch.niculin.player;

import ch.niculin.FieldStatus;
import ch.niculin.Playground;
import ch.niculin.Point;
import ch.niculin.Shot;
import ch.niculin.ships.MainShip;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Player {
    private final Playground playground;
    private final String name;
    private final List<MainShip> mainShips;
    private final List<Point> hittedShipPoints;
    private final List<MainShip> destroyedShips;
    private final List<Point> faildShots;

    public Player(String name, Playground playground) {
        this.playground = playground;
        this.name = name;
        mainShips = new LinkedList<>();
        hittedShipPoints = new LinkedList<>();
        destroyedShips = new LinkedList<>();
        faildShots = new LinkedList<>();
    }

    public boolean shootShip(Shot shot, Playground playgrounOfOtherPlayer, List<MainShip> mainShipList){
        Optional<Point> playgroundPoint = playgrounOfOtherPlayer.getPlaygroundPoint(shot.getPoint());

        if (playgroundPoint.isEmpty()){
            return false;
        }else{
            for (MainShip mainShip : mainShipList) {
                if (mainShip.getShipPosition().contains(playgroundPoint.get())) {
                    mainShip.getShipPosition().remove(playgroundPoint.get());
                    if (mainShip.getShipPosition().isEmpty()) {
                        mainShipList.remove(mainShip);
                        destroyedShips.add(mainShip);
                    }
                    hittedShipPoints.add(playgroundPoint.get());
                    return true;
                }
            }
            faildShots.add(playgroundPoint.get());


            return false;
        }
    }


    public String getName() {
        return name;
    }

    public List<Point> getPlayground() {
        return playground.getPlayground();
    }

    public Playground getPlaygroundAsPlaygroundObject() {
        return playground;
    }

    public Optional<Point> getPlaygroundPoint(Point inputPoint){
        return playground.getPlaygroundPoint(inputPoint);
    }


        public List<MainShip> getMainShips() {
        return mainShips;
    }

    public int getPlaygroundSize(){
        return playground.getSize();
    }

    public List<Point> getHittedShipPoints() {
        return hittedShipPoints;
    }

    public List<MainShip> getDestroyedShips() {
        return destroyedShips;
    }

    public List<Point> getFaildShots() {
        return faildShots;
    }
}
