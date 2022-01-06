package ch.niculin;

import java.util.Optional;

public class Computer extends Player {
    Playground playground;

    public Computer(Playground playground) {
        super("Computer1", playground);
        this.playground = new Playground(playground.getSize());
    }

    @Override
    public boolean shootShip(Shot shot) {
        return hasAlreadyShipShot(shot);
    }

    private boolean hasAlreadyShipShot(Shot shot) {
        Point point = shot.getPoint();
        for (MainShip mainShip : getMainShips()) {
            if (mainShip.shipPosition.contains(point)) {
                mainShip.shipPosition.remove(point);
                point.setFieldStatus(FieldStatus.DESTROYED);
                if (mainShip.shipPosition.isEmpty()) {
                    getMainShips().remove(mainShip);
                }
                return true;
            }
        }
        point.setFieldStatus(FieldStatus.FAILEDSHOT);
        return false;
    }

    public void generateShips() {
        placeShip();
    }

    private void placeShip() {
        Point createdPoint = new Point(getRandomChar(), getRandomInt());
        Optional<Point> playgroundPoint = getPlayground().getPlaygroundPoint(createdPoint);
        Point point;
        if (playgroundPoint.isPresent()) {
            point = playgroundPoint.get();
        } else {
            throw new IllegalArgumentException("Invalid inpunt");
        }
        RubberBoat rubberBoat = new RubberBoat();
        rubberBoat.addShipPositionPoint(point);
        getMainShips().add(rubberBoat);
        point.setFieldStatus(FieldStatus.SHIP);
    }
}
