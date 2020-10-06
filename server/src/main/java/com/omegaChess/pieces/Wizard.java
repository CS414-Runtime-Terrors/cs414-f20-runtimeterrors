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
        int[] rc;
        try{
            rc = parsePosition(pos);
        }catch (IllegalPositionException e){
            System.out.println("Exception thrown: " + e);
        }
        for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(reverseParse(rc[0]+i, rc[1]-1));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(reverseParse(rc[0]+1, rc[1]+i));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(reverseParse(rc[0]-i, rc[1]+1));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(reverseParse(rc[0]-1, rc[1]-i));
        }
        moves.add(reverseParse(rc[0]+3, rc[1]+1)); moves.add(reverseParse(rc[0]-1, rc[1]+3));
        moves.add(reverseParse(rc[0]-3, rc[1]-1)); moves.add(reverseParse(rc[0]+1, rc[1]-3));
        return moves;
    }

}