package pieces;

import logic.Coordinates;
import logic.Move;
import logic.Position;

import java.util.ArrayDeque;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor);

        if (pieceColor == PieceColor.WHITE) {
            moveGenerator = new MoveGeneratorForWhite();
        }
        else {
            moveGenerator = new MoveGeneratorForBlack();
        }
    }

    private static class MoveGeneratorForWhite implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            Coordinates coordinates = new Coordinates(row, column);

            Coordinates forward = new Coordinates(row - 1, column);
            Coordinates left = new Coordinates(row - 1, column - 1);
            Coordinates right = new Coordinates(row - 1, column + 1);

            ArrayDeque<Move> moves = new ArrayDeque<>();

            if (position.isOnBoard(forward) && !position.isSquareOccupied(forward))
                moves.push(new Move(coordinates, forward));

            if (position.isOnBoard(left) && position.isSquareOccupied(left) && position.isTakeLegal(left))
                moves.push(new Move(coordinates, left));

            if (position.isOnBoard(right) && position.isSquareOccupied(right) && position.isTakeLegal(right))
                moves.push(new Move(coordinates, right));

            return moves.toArray(new Move[]{});
        }
    }

    private static class MoveGeneratorForBlack implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            Coordinates coordinates = new Coordinates(row, column);

            Coordinates forward = new Coordinates(row + 1, column);
            Coordinates left = new Coordinates(row + 1, column - 1);
            Coordinates right = new Coordinates(row + 1, column + 1);

            ArrayDeque<Move> moves = new ArrayDeque<>();

            if (position.isOnBoard(forward) && !position.isSquareOccupied(forward))
                moves.push(new Move(coordinates, forward));

            if (position.isOnBoard(left) && position.isSquareOccupied(left) && position.isTakeLegal(left)) {
                moves.push(new Move(coordinates, left));
            }

            if (position.isOnBoard(right) && position.isSquareOccupied(right) && position.isTakeLegal(right)) {
                moves.push(new Move(coordinates, right));
            }

            return moves.toArray(new Move[]{});
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♙';
        else return '♟';
    }

    @Override
    public String getName() {
        return "pawn";
    }
}
