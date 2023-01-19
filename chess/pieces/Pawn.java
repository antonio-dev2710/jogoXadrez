package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // duas linhas a frent
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(p.getRow() - 1, p.getColumn());
            if (getBoard().positonExist(p) && !getBoard().thereAPiece(p) && getBoard().positonExist(p2)
                    && !getBoard().thereAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // diagonal
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positonExist(p) && isThereOppentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positonExist(p) && isThereOppentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // spaceialmove en passant-white
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positonExist(left) && isThereOppentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() - 1][left.getColumn()] = true;

                }

                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positonExist(right) && isThereOppentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() - 1][right.getColumn()] = true;

                }
            }
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positonExist(p) && !getBoard().thereAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // duas linhas a frent
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(p.getRow() + 1, p.getColumn());
            if (getBoard().positonExist(p) && !getBoard().thereAPiece(p) && getBoard().positonExist(p2)
                    && !getBoard().thereAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // diagonal
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positonExist(p) && isThereOppentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positonExist(p) && isThereOppentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // spaceialmove en passant-black
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positonExist(left) && isThereOppentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() + 1][left.getColumn()] = true;

                }

                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positonExist(right) && isThereOppentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() + 1][right.getColumn()] = true;

                }
            }

        }

        return mat;
    }

    @Override
    public String toString() {
        return "p";
    }

}
