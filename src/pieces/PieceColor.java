package pieces;

public enum PieceColor {
    WHITE, BLACK;

    public PieceColor getOpposite() {
        if (this == WHITE)
            return BLACK;
        else return WHITE;
    }

    @Override
    public String toString() {
        return (this == WHITE) ? "white" : "black";
    }
}
