public class Champion extends ChessPiece{

    public Champion(ChessBoard board, ChessPiece.Color color){super(board, color);}

    @Override
    public String toString(){
        if (getColor == Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
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
        for (int i = 1; i <= 2; i++){
            moves.add(reverseParse(rc[0]+i, rc[1]));
        } for (int i = 1; i <= 2; i++){
            moves.add(reverseParse(rc[0]-i, rc[1]));
        } for (int i = 1; i <= 2; i++){
            moves.add(reverseParse(rc[0], rc[1]+i));
        } for (int i = 1; i <= 2; i++){
            moves.add(reverseParse(rc[0], rc[1]-i));
        }
        moves.add(reverseParse(rc[0]+2, rc[1]-2)); moves.add(reverseParse(rc[0]+2, rc[1]+2));
        moves.add(reverseParse(rc[0]-2, rc[1]+2)); moves.add(reverseParse(rc[0]-2, rc[1]-2));
        return moves;
    }

}