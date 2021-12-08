package ch.niculin;

public class Main {
    public static void main(String[] args) {

        Controll controll = new Controll();
        Playground playground = new Playground();
        while (playground.countShips() != 4) {
            Ship ship = controll.makeShip();
           try {
               playground.setShip(ship);
           } catch (IllegalArgumentException e){
               System.err.println(e.getMessage());
           }
        }

        System.out.println(playground);
        while (playground.hasShips()) {
            Shot shot = controll.makeShot();
            if (playground.shootShip(shot)){
                System.out.println("Getroffen!!!!");
            } else {
                System.out.println("Verschossen!!!!");
            }

        }
    }
}
