package boardGame;

public abstract class Piece {
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

    public abstract boolean[][]possibleMoves();
    //metodo concreto que esta reutilizando metodo abstrato
    //hookMethods: um metodo que faz um gancho com a subclasse
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    //verificar se pelo menos há uma posição verdadeira

    public boolean isThereAnyPossibleMove(){
        boolean [][] mat = possibleMoves();

        for(int i =0; i<mat.length;i++){
            for(int j =0; j<mat.length;j++){
                if(mat[i][j]){
                    return true;
                }
                
            }
        }
        return false;
    }
    
    
}
