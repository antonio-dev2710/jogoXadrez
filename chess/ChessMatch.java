package chess;

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

    public boolean [][] possiblesMoves(ChessPosition sourcePosition){
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
        nextTurn();
       

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturePiece = board.removePiece(target);

        board.placePiece(p, target);
        return capturePiece;
    }

    private void validateSourcePositon(Position position) {
        if (!board.thereAPiece(position)) {
            throw new ChessExeption("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()){
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
    private void nextTurn(){
        turn++;
        currentPlayer =(currentPlayer==Color.WHITE)?Color.BLACK:Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece pice) {
        board.placePiece(pice, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
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
