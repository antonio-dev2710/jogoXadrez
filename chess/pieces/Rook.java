package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    // torre
    public Rook(Board board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }
        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }
        // below
        p.setValues(position.getRow() + 1, position.getColumn());

        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }

}
