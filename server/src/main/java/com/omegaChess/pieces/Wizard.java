public class Wizard extends ChessPiece {

    public Wizard(ChessBoard board, ChessPiece.Color color){super(board, color);}

    @Override
    public String toString(){
        if (getColor == Color.WHITE)
            return "\u26AA";
        else
            return "\u26BB";
    }

    @Override
    public ArrayList<String> legalMoves(){
        ArrayList<String> moves = new ArrayList<>();
        String pos = this.getPosition();
        int[] rc = new int[2];
        try{
            rc = parsePosition(pos);
        }catch (IllegalPositionException e){
            System.out.println("Exception thrown: " + e);
        }
        moves.add(reverseParse(rc[0]+1, rc[1]-1)); moves.add(reverseParse(rc[0]+1, rc[1]+1));
        moves.add(reverseParse(rc[0]-1, rc[1]+1)); moves.add(reverseParse(rc[0]-1, rc[1]-1));
    }

}