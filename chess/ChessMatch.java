package chess;

import boardGame.Board;
import boardGame.Piece;
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
    //movimentacao da peça~
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPositon){
        Position source =sourcePosition.toPosition();
        Position target = targetPositon.toPosition();

        validateSourcePositon(source);
        Piece capturedPiece =makeMove(source, target);

        return (ChessPiece) capturedPiece;
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturePiece = board.removePiece(target);

        board.placePiece(p, target);
        return capturePiece;
    }
    private void validateSourcePositon(Position position){
        if(!board.thereAPiece(position)){
            throw new ChessExeption("There is no piece on source position");
        }

    }
    private void placeNewPiece(char column, int row, ChessPiece pice){
        board.placePiece(pice, new ChessPosition(column, row).toPosition());
    }
    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
