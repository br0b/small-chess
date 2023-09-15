package pieces;

import logic.Coordinates;
import logic.Move;
import logic.Position;

public abstract class Piece {
    protected final PieceColor color;
    protected MoveGenerator moveGenerator;

    protected interface MoveGenerator {
        Move[] generate(int row, int column, Position position);
    }

    public Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract char getSymbol();

    public abstract String getName();

    public String getNameAndColor() {
        return color.toString() + " " + getName();
    }

    public Move[] getPossibleMoves(int row, int column, Position position) {
        return moveGenerator.generate(row, column, position);
    }

    public Move[] getPossibleMoves(Coordinates coordinates, Position position) {
        return getPossibleMoves(coordinates.row(), coordinates.column(), position);
    }
}
