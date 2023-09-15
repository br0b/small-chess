package pieces;

import logic.Coordinates;
import logic.Move;
import logic.Position;

import java.util.ArrayDeque;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor) {
        super(pieceColor);

        moveGenerator = new RookMoveGenerator();
    }

    public static class RookMoveGenerator implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            ArrayDeque<Move> moves = new ArrayDeque<>();

            Coordinates coordinates = new Coordinates(row, column);

            int potentialRow;
            int potentialColumn;

            // up
            potentialRow = row - 1;

            while (potentialRow >= 0 && !position.isSquareOccupied(potentialRow, column)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, column)));
                potentialRow--;
            }

            if (potentialRow >= 0 && position.isTakeLegal(potentialRow, column))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, column)));

            // right
            potentialColumn = column + 1;

            while (potentialColumn < 8 && !position.isSquareOccupied(row, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(row, potentialColumn)));
                potentialColumn++;
            }

            if (potentialColumn < 8 && position.isTakeLegal(row, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(row, potentialColumn)));

            // down
            potentialRow = row + 1;

            while (potentialRow < 8 && !position.isSquareOccupied(potentialRow, column)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, column)));
                potentialRow++;
            }

            if (potentialRow < 8 && position.isTakeLegal(potentialRow, column))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, column)));

            // left
            potentialColumn = column - 1;

            while (potentialColumn >= 0 && !position.isSquareOccupied(row, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(row, potentialColumn)));
                potentialColumn--;
            }

            if (potentialColumn >= 0 && position.isTakeLegal(row, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(row, potentialColumn)));

            return moves.toArray(new Move[]{});
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♖';
        else return '♜';
    }

    @Override
    public String getName() {
        return "rook";
    }
}
