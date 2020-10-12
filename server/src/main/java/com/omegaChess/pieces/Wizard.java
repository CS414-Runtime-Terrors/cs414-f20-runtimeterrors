package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Wizard extends ChessPiece {

    public Wizard(ChessBoard board, ChessPiece.Color color){super(board, color);}

    @Override
    public String toString(){
        if (this.color == Color.WHITE)
            return "\u26AA";
        else
            return "\u26BB";
    }

    @Override
    public LegalMoves legalMoves(){
        ArrayList<String> moves = new ArrayList<>();
        String pos = this.getPosition();
        int[] rc;
        try {
            rc = board.parsePosition(pos);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
            return new LegalMoves(moves, false, false);
        }


        for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(board.reverseParse(rc[0]+i, rc[1]-1));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(board.reverseParse(rc[0]+1, rc[1]+i));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(board.reverseParse(rc[0]-i, rc[1]+1));
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            moves.add(board.reverseParse(rc[0]-1, rc[1]-i));
        }
        moves.add(board.reverseParse(rc[0]+3, rc[1]+1)); moves.add(board.reverseParse(rc[0]-1, rc[1]+3));
        moves.add(board.reverseParse(rc[0]-3, rc[1]-1)); moves.add(board.reverseParse(rc[0]+1, rc[1]-3));
        return new LegalMoves(moves, false, false);
    }

}