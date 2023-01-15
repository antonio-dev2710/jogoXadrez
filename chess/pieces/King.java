package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position positon) {
        ChessPiece p = (ChessPiece) getBoard().piece(positon);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // nw(noroeste)
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // ne(nordeste)
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // sw(sudoeste)
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positonExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

         // se(sudeste)
         p.setValues(position.getRow() + 1, position.getColumn() + 1);
         if (getBoard().positonExist(p) && canMove(p)) {
             mat[p.getRow()][p.getColumn()] = true;
         }
        return mat;
    }

}
