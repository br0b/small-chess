package logic;

import pieces.*;

import java.util.ArrayDeque;

public class Position {
    private final Piece[][] board;

    private final PieceColor turn;

    private final int turnNumber;

    public Position() {
        this.board = new Piece[][]{
                {
                    new Rook(PieceColor.BLACK), new Knight(PieceColor.BLACK), new Bishop(PieceColor.BLACK),
                    new Queen(PieceColor.BLACK), new King(PieceColor.BLACK),
                    new Bishop(PieceColor.BLACK), new Knight(PieceColor.BLACK), new Rook(PieceColor.BLACK)
                },
                {
                    new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK)
                },
                { null, null, null, null, null, null, null, null },
                { null, null, null, null, null, null, null, null },
                { null, null, null, null, null, null, null, null },
                { null, null, null, null, null, null, null, null },
                {
                    new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE)
                },
                {
                    new Rook(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Bishop(PieceColor.WHITE),
                    new Queen(PieceColor.WHITE), new King(PieceColor.WHITE),
                    new Bishop(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Rook(PieceColor.WHITE)
                }
        };
        this.turn = PieceColor.WHITE;
        this.turnNumber = 1;
    }

    public Position(Piece[][] board, PieceColor turn, int turnNumber) {
        this.board = board;
        this.turn = turn;
        this.turnNumber = turnNumber;
    }

    public Position makeMove(Move move) {
        int nextTurnNumber = (turn == PieceColor.WHITE) ? turnNumber : (turnNumber + 1);

        Coordinates start = move.start();
        Coordinates end = move.end();

        Piece[][] nextBoard = board;
        nextBoard[end.row()][end.column()] = board[start.row()][start.column()];
        nextBoard[start.row()][start.column()] = null;

        return new Position(nextBoard, turn.getOpposite(), nextTurnNumber);
    }

    public boolean isTakeLegal(Coordinates coordinates) {
        return isTakeLegal(coordinates.row(), coordinates.column());
    }

    public boolean isTakeLegal(int row, int column) {
        return isSquareOccupied(row, column) && (getPiece(row, column).getColor() != turn);
    }

    public PieceColor getTurn() {
        return turn;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Piece getPiece(Coordinates coordinates) {
        return board[coordinates.row()][coordinates.column()];
    }

    public Piece getPiece(int row, int column) {
        return board[row][column];
    }

    // Returns an array of pieces of current turn's player that have at least one legal move.
    public Coordinates[] getCurrentPlayerPiecesWithPossibleMoves() {
        ArrayDeque<Coordinates> currentPlayerPieces = new ArrayDeque<>();

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = getPiece(row, column);
                if (piece != null && piece.getColor() == turn) {
                    if (piece.getPossibleMoves(row, column, this).length > 0)
                        currentPlayerPieces.push(new Coordinates(row, column));
                }
            }
         }

        return currentPlayerPieces.toArray(new Coordinates[]{});
    }

    public boolean isSquareOccupied(int row, int column) {
        return getPiece(row, column) != null;
    }

    public boolean isSquareOccupied(Coordinates coordinates) {
        return getPiece(coordinates) != null;
    }

    public String getTurnAsString() {
        return "Turn: " + turnNumber;
    }

    public String boardToString() {
        StringBuilder boardString = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            boardString.append("\n").append(8 - row).append(": ");

            for (int column = 0; column < 8; column++) {
                Piece piece = getPiece(row, column);

                if (piece == null)
                    boardString.append('.');
                else boardString.append(getPiece(row, column).getSymbol());
            }
        }

        return boardString.toString();
    }

    public boolean isOnBoard(Coordinates coordinates) {
        return coordinates.row() >= 0 &&
                coordinates.row() < 8 &&
                coordinates.column() >= 0 &&
                coordinates.column() < 8;
    }
}
