package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
        
    }

    @Override
    public String toString() {
        return "N";
    }

    private boolean canMove(Position positon) {
        ChessPiece p = (ChessPiece) getBoard().piece(positon);
        return p == null || p.getColor() != getColor();
    }

    @Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
		
		Position p = new Position(0, 0);
		
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positonExist(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
