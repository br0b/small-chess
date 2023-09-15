package pieces;

import logic.Move;
import logic.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor);

        moveGenerator = new QueenMoveGenerator();
    }

    private static class QueenMoveGenerator implements MoveGenerator {
        @Override
        public Move[] generate(int row, int column, Position position) {
            Move[] bishopMoves = new Bishop.BishopMoveGenerator().generate(row, column, position);
            Move[] rookMoves = new Rook.RookMoveGenerator().generate(row, column, position);

            Move[] moves = new Move[bishopMoves.length + rookMoves.length];

            System.arraycopy(bishopMoves, 0, moves, 0, bishopMoves.length);
            System.arraycopy(rookMoves, 0, moves, bishopMoves.length, rookMoves.length);

            return moves;
        }
    }

    @Override
    public char getSymbol() {
        if (color == PieceColor.BLACK)
            return '♕';
        else return '♛';
    }

    @Override
    public String getName() {
        return "queen";
    }
}
