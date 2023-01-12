package boardGame;

public class Piece {
    //posição simples de matriz
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        //a psoiçaõ de uma peça recem criada é nula
        position = null;

    }
    //apenas o metodo get, impedindo que o tabu seja alterado
    //protected:somente classes do mesmo pacote e subclasses vão ter acesso

    protected Board getBoard() {
        return board;
    }

    
    
}
