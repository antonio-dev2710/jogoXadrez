package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;

    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position positon) {
        ChessPiece p = (ChessPiece) getBoard().piece(positon);
        return p == null || p.getColor() != getColor();
    }

    // testar se a torre está apta para o roque
    private boolean testRookCastling(Position position) {
        // apta para roque quando a quantidade de moviemntos é igual a 0
        ChessPiece p = (ChessPiece) getBoard().piece(position);

        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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


        //#specialmove castling
        if(getMoveCount() ==0 && !chessMatch.getCheck()){
            //specialmove castling kingside rook
            Position posT1 = new Position(position.getRow(),position.getColumn()+3);
            if(testRookCastling(posT1)){
                Position p1 =  new Position(position.getRow(),position.getColumn()+1);
                Position p2 =  new Position(position.getRow(),position.getColumn()+2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn()+2]=true;
                }
            }
            //specialmove castling Queenside rook
            Position posT2 = new Position(position.getRow(),position.getColumn()-4);
            if(testRookCastling(posT2)){
                Position p1 =  new Position(position.getRow(),position.getColumn()-1);
                Position p2 =  new Position(position.getRow(),position.getColumn()-2);
                Position p3 =  new Position(position.getRow(),position.getColumn()-3);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColumn()-2]=true;
                }
            }
        }
        return mat;
    }

}
