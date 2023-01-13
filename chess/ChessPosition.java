package chess;

import boardGame.Position;

public class ChessPosition {
    private char column;
    private int row;


    
    public ChessPosition(char column, int row) {
        if(column<'a'|| column>'h'||row<1||row>8){
            throw new ChessExeption("Error intantiating ChessePosition.Valid values are from a1 to h8");
        }

        this.column = column;
        this.row = row;
    }
    public char getColumn() {
        return column;
    }
   
    public int getRow() {
        return row;
    }
    
    protected Position toPosition(){
        return new Position(8-row, column-'a');
    }

    protected static ChessPosition ChessPosition(Position position){
        return new ChessPosition((char) ('a'- position.getColumn()),(8-position.getRow()));
    }
    @Override
    public String toString() {
        return "column=" + column + ", row=" + row ;
    }

    

    
    
}