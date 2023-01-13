package boardGame;

import chess.ChessPiece;
import chess.ChessPosition;

public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;
    public Board(int rows, int column) {
        if(rows<1||column<1){
            throw new BoardException("Error creating baord: there must be at least 1 row column");
        }
        this.rows = rows;
        this.columns = column;
        this.pieces = new Piece [rows][column];
    }
    public int getRows() {
        return rows;
    }
   
    public int getColumn() {
        return columns;
    }
    
    
    public Piece piece(int piece, int columns){
        if(!positonExist(piece, columns)){
            throw new BoardException("Position not on the board");
        }
        return pieces[piece][columns];
    }
    public Piece piece(Position position){
        if(!positonExist(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereAPiece(position)){
            throw new BoardException("There is already a piece on position "+position);
        }
        pieces[position.getRow()][position.getColumn()]=piece;
        piece.position=position;
    }

    private boolean positonExist(int row, int column){
       return row>=0 && row<rows && column >=0 &&column<columns;

    }
    public boolean positonExist(Position position){
        return positonExist(position.getRow(),position.getColumn());
    }

    public boolean thereAPiece(Position position){
        if(!positonExist(position)){
            throw new BoardException("Position not on the board");
        }
       return piece(position) !=null;
    }

}
