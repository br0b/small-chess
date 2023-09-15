package players;

import logic.Coordinates;
import logic.Move;
import logic.Position;
import pieces.Piece;

public class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Move getMove(Position position) {
        Coordinates[] myPieces = position.getCurrentPlayerPiecesWithPossibleMoves();

        if (myPieces.length == 0)
            return null;

        int pieceChoice = (int) (Math.random() * myPieces.length);

        Piece piece = position.getPiece(myPieces[pieceChoice]);

        Move[] possibleMoves = piece.getPossibleMoves(myPieces[pieceChoice], position);

        assert possibleMoves.length != 0;

        int moveChoice = (int) (Math.random() * possibleMoves.length);

        return possibleMoves[moveChoice];
    }
}
