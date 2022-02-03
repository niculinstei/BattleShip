package ch.niculin;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        String winnerName = game.play();
        System.out.println(winnerName + " won the game!");
    }
}
