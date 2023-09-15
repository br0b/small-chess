import logic.Game;
import players.RandomPlayer;

public class Main {
    public static void main(String[] args) {
        RandomPlayer white = new RandomPlayer("player1");
        RandomPlayer black = new RandomPlayer("player2");

        Game game = new Game();
        game.play(white, black);
    }
}
