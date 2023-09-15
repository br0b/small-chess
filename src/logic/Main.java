package logic;

import players.*;

public class Main {
    public static void main(String[] args) {
        Player white = new RandomPlayer("White");
        Player black = new RandomPlayer("Black");

        Game game = new Game();
        game.play(white, black);
    }
}