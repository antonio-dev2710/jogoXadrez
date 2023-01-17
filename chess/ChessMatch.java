package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    // vai ter as regras do jogo
    private Board board;
    private boolean check;
    private boolean checkMate;

    private List<Piece> picesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
    }

   

    public ChessPiece[][] getPices() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getRows()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                // downcast
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }

        }
        return mat;

    }

    public boolean[][] possiblesMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePositon(position);

        return board.piece(position).possibleMoves();
    }

    // movimentacao da peça~
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPositon) {
        Position source = sourcePosition.toPosition();
        Position target = targetPositon.toPosition();
        validateSourcePositon(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessExeption("You can't put yourself in check");
        }

        check= (testCheck(opponent(currentPlayer)))?true:false;

        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }
        else{
            nextTurn();
        }
       
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();

        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            picesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);

        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturePiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        
        board.placePiece(p, source);
        if (capturePiece != null) {
            board.placePiece(capturePiece, target);
            capturedPieces.remove(capturePiece);
            picesOnTheBoard.add(capturePiece);

        }
    }

    private void validateSourcePositon(Position position) {
        if (!board.thereAPiece(position)) {
            throw new ChessExeption("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessExeption("the chosen piece is not your");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessExeption("There is no moves for the chosen piece");
        }

    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessExeption("the chosen piece can't  move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece King(Color color) {
        List<Piece> list = picesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
                .collect(Collectors.toList());

        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("there in no" + color + "king on the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = King(color).getChessPsotion().toPosition();
        List<Piece> opponentPieces = picesOnTheBoard.stream()
                .filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
            
        for(Piece p : opponentPieces){
            boolean [][]mat =p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }

        }
        return false;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());

        picesOnTheBoard.add(piece);
    }

    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }
        List<Piece> list = picesOnTheBoard.stream()
        .filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());

        for(Piece p:list){
            boolean[][] mat = p.possibleMoves();
            for(int i=0;i<board.getRows();i++){
                for(int j =0;j<board.getColumn();j++){
                    if(mat[i][j]){
                        Position source =((ChessPiece)p).getChessPsotion().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source, target);

                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;

    }

    private void initialSetup() {
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
       

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
       
    }
}
