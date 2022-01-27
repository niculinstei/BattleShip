package ch.niculin;

public class GameMode{
    public static int shipQuantity(int plagroundSize) {
        switch (plagroundSize){
            case 5 : {
                return 2;
            }
            case 8 : {
                return 4;
            }
            case 10 : {
                return 6;
            }
            default: throw new IllegalArgumentException("invalid Playground Size");
        }
    }
}
