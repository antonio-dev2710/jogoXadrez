import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardGame.Board;
import boardGame.Position;
import chess.ChessExeption;
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


        while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possiblesMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPices(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}

				if(chessMatch.getPromoted()!=null){
					System.out.print("Enter piece for promotion (Q/N/R): ");

					String type = sc.nextLine().toUpperCase();
					while(!type.equals("B")&&!type.equals("N")&&!type.equals("R")&&!type.equals("Q")){
						System.out.print("Invalid value! Enter piece for promotion (Q/N/R): ");

						 type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);

				}
			}
			catch (ChessExeption e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
       

        
        
    }
}
