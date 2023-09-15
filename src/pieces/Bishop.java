package pieces;

import logic.Coordinates;
import logic.Move;
import logic.Position;

import java.util.ArrayDeque;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);

        moveGenerator = new BishopMoveGenerator();
    }

    public static class BishopMoveGenerator implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            ArrayDeque<Move> moves = new ArrayDeque<>();

            Coordinates coordinates = new Coordinates(row, column);

            int potentialRow;
            int potentialColumn;

            // up-right
            potentialRow = row - 1;
            potentialColumn = column + 1;

            while (potentialRow >= 0 && potentialColumn < 8 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));
                potentialRow--;
                potentialColumn++;
            }

            if (potentialRow >= 0 && potentialColumn < 8 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));

            // down-right
            potentialRow = row + 1;
            potentialColumn = column + 1;

            while (potentialRow < 8 && potentialColumn < 8 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));
                potentialRow++;
                potentialColumn++;
            }

            if (potentialRow < 8 && potentialColumn < 8 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));

            // down-left
            potentialRow = row + 1;
            potentialColumn = column - 1;

            while (potentialRow < 8 && potentialColumn >= 0 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));
                potentialRow++;
                potentialColumn--;
            }

            if (potentialRow < 8 && potentialColumn >= 0 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));

            // up-left
            potentialRow = row - 1;
            potentialColumn = column - 1;

            while (potentialRow >= 0 && potentialColumn >= 0 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn)) {
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));
                potentialRow--;
                potentialColumn--;
            }

            if (potentialRow >= 0 && potentialColumn >= 0 &&
                    !position.isSquareOccupied(potentialRow, potentialColumn))
                moves.push(new Move(coordinates, new Coordinates(potentialRow, potentialColumn)));

            return moves.toArray(new Move[]{});
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♗';
        else return '♝';
    }

    @Override
    public String getName() {
        return "bishop";
    }
}
