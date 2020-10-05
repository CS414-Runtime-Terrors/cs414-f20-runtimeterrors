public class Champion extends ChessPiece{

    public Champion(ChessBoard board, ChessPiece.Color color){super(board, color);}

    @Override
    public String toString(){
        if (getColor == Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
    }

}