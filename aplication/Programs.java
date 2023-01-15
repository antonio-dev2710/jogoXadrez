import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Programs {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Jogo de xadez");
        //nova partida xadrez
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(true){
            //cirar uma interface para vizualizar o tabuleiro
            UI.clearScreen();
            UI.printMatch(chessMatch, captured);
            System.out.println();
            System.out.println("Source: ");

            ChessPosition source = UI.readChessPosition(sc);

            boolean[][ ]possibleMoves=chessMatch.possiblesMoves(source);
            UI.clearScreen();
            UI.printBoard(chessMatch.getPices(),possibleMoves);


            System.out.println();
            System.out.println("Target: ");

            ChessPosition target = UI.readChessPosition(sc);

            ChessPiece capturPiece = chessMatch.performChessMove(source, target);

            if(capturPiece!=null){
                captured.add(capturPiece);
            }
        }
       

        
        
    }
}
