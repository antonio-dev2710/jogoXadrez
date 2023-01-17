package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    // torre
    public Bishop(Board board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];

        Position p = new Position(0, 0);

        // nw
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }

        // ne
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }
        // se
        p.setValues(position.getRow() +1, position.getColumn() + 1);
        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()+1,p.getColumn() + 1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }
        // sw
        p.setValues(position.getRow() + 1, position.getColumn()-1);

        while (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1,p.getColumn()-1);
        }
        if (getBoard().positonExist(p) && isThereOppentPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }

}
