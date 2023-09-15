package logic;

import players.Player;
import pieces.PieceColor;

public class Game {private Position currentPosition;

    public Game() {
        currentPosition = new Position();
    }

    public void play(Player white, Player black) {
        System.out.println("White: " + white.getName() +
                "\nBlack: " + black.getName() + "\n" +
                currentPosition.boardToString());

        PieceColor winner = null;

        while (currentPosition.getTurnNumber() != 51 && winner == null) {
            PieceColor currentTurn = currentPosition.getTurn();

            Move move;

            if (currentTurn == PieceColor.WHITE)
                move = white.getMove(currentPosition);
            else
                move = black.getMove(currentPosition);

            if (move == null) {
                winner = currentTurn.getOpposite();
            }

            assert move != null;

            if (move.isWinning(currentPosition)) {
                winner = currentTurn;
            }

            String moveString = move.toString(currentPosition);

            Position nextPosition = currentPosition.makeMove(move);

            if (currentPosition.getTurn() == PieceColor.WHITE)
                System.out.println('\n' + currentPosition.getTurnAsString());

            System.out.println(currentPosition.boardToString());
            System.out.println(moveString + '\n');

            currentPosition = nextPosition;
        }

        System.out.print('\n');

        if (winner == null)
            System.out.println("Draw.");
        else if (winner == PieceColor.WHITE)
            System.out.println(white.getName() + " wins.");
        else
            System.out.println(black.getName() + " wins.");
    }
}
