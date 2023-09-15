package logic;

public class Coordinates {
    private final int row;
    private final int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    @Override
    public String toString() {
        return (char) (65 + column) + Integer.toString(8 - row);
    }
}
