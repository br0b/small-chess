package logic;

import pieces.Piece;

import java.util.Objects;

public class Move {
    private final Coordinates start;
    private final Coordinates end;

    public Move(Coordinates start, Coordinates end) {
        this.start = start;
        this.end = end;
    }

    public Coordinates start() {
        return start;
    }

    public Coordinates end() {
        return end;
    }

    public boolean isWinning(Position position) {
        if (!position.isSquareOccupied(end))
            return false;

        Piece pieceTaken = position.getPiece(end);

        return Objects.equals(pieceTaken.getName(), "king");
    }

    public String toString(Position position) {
        StringBuilder moveString = new StringBuilder();

        Piece piece = position.getPiece(start);

        moveString.append(piece.getNameAndColor()).append(" from ").append(start);

        if (position.isSquareOccupied(end)) {
            Piece pieceTaken = position.getPiece(end);

            moveString.append(" takes ").append(pieceTaken.getNameAndColor()).append(" on ").append(end);
        }
        else moveString.append(" moves to ").append(end);

        return moveString.toString();
    }
}
