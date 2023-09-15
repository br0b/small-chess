package pieces;

import logic.Coordinates;
import logic.Move;
import logic.Position;

import java.util.ArrayDeque;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor);

        moveGenerator = new KnightMoveGenerator();
    }

    private static class KnightMoveGenerator implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            ArrayDeque<Move> moves = new ArrayDeque<>();

            Coordinates coordinates = new Coordinates(row, column);

            final Coordinates[] potentialSquares = {
                    new Coordinates(row - 2, column + 1),
                    new Coordinates(row - 1, column + 2),
                    new Coordinates(row + 1, column + 2),
                    new Coordinates(row + 2, column + 1),
                    new Coordinates(row + 2, column - 1),
                    new Coordinates(row + 1, column - 2),
                    new Coordinates(row - 1, column - 2),
                    new Coordinates(row - 2, column - 1)
            };

            for (Coordinates potentialSquare : potentialSquares) {
                if (position.isOnBoard(potentialSquare) &&
                        (!position.isSquareOccupied(potentialSquare) || position.isTakeLegal(potentialSquare))) {
                    moves.push(new Move(coordinates, potentialSquare));
                }
            }

            return moves.toArray(new Move[]{});
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♘';
        else return '♞';
    }

    @Override
    public String getName() {
        return "knight";
    }
}
