package chess;

import boardGame.Board;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    //vai ter as regras do jogo
    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
    }
    public ChessPiece[][] getPices(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getRows()];

        for(int i =0; i<board.getRows();i++){
            for(int j =0; j<board.getRows();j++){
                //downcast
                mat[i][j]= (ChessPiece) board.piece(i, j);
            }
           
        }
        return mat;

    }
    private void initialSetup(){
        board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
        board.placePiece(new King(board, Color.BLACK), new Position(0,4));

    }
}
