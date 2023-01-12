import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;

public class Programs {
    public static void main(String[] args) {
        System.out.println("Jogo de xadez");
        //nova partida xadrez
        ChessMatch chessMatch = new ChessMatch();
        //cirar uma interface para vizualizar o tabuleiro
        UI.printBoard(chessMatch.getPices());

        
        
    }
}
