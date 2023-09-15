package pieces;


import logic.Coordinates;
import logic.Move;
import logic.Position;

import java.util.ArrayDeque;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor);

        moveGenerator = new KingMoveGenerator();
    }

    private static class KingMoveGenerator implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            ArrayDeque<Move> moves = new ArrayDeque<>();

            Coordinates coordinates = new Coordinates(row, column);

            for (int potentialRow = row - 1; potentialRow <= row + 1; potentialRow++) {
                for (int potentialColumn = column - 1; potentialColumn <= column + 1; potentialColumn++) {
                    Coordinates potentialSquare = new Coordinates(potentialRow, potentialColumn);

                    if (position.isOnBoard(potentialSquare) &&
                            (!position.isSquareOccupied(potentialSquare) || position.isTakeLegal(potentialSquare)))
                        moves.push(new Move(coordinates, potentialSquare));
                }
            }

            return moves.toArray(new Move[]{});
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♔';
        else return '♚';
    }

    @Override
    public String getName() {
        return "king";
    }
}
