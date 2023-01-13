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
    private void placeNewPiece(char column, int row, ChessPiece pice){
        board.placePiece(pice, new ChessPosition(column, row).toPosition());
    }
    private void initialSetup(){
        placeNewPiece('b',6,new Rook(board, Color.WHITE));
        placeNewPiece('e',8,new King(board, Color.BLACK));

        placeNewPiece('e',1,new King(board, Color.WHITE));
    }
}
