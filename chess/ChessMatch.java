package chess;

import boardGame.Board;

public class ChessMatch {
    //vai ter as regras do jogo
    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
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
}
