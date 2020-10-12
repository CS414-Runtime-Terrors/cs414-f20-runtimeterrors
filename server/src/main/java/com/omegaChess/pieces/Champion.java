package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Champion extends ChessPiece{

    public Champion(ChessBoard board, ChessPiece.Color color){super(board, color);}

    @Override
    public String toString(){
        if (this.color == Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
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

        for (int i = 1; i <= 2; i++){
            moves.add(board.reverseParse(rc[0]+i, rc[1]));
        } for (int i = 1; i <= 2; i++){
            moves.add(board.reverseParse(rc[0]-i, rc[1]));
        } for (int i = 1; i <= 2; i++){
            moves.add(board.reverseParse(rc[0], rc[1]+i));
        } for (int i = 1; i <= 2; i++){
            moves.add(board.reverseParse(rc[0], rc[1]-i));
        }
        moves.add(board.reverseParse(rc[0]+2, rc[1]-2)); moves.add(board.reverseParse(rc[0]+2, rc[1]+2));
        moves.add(board.reverseParse(rc[0]-2, rc[1]+2)); moves.add(board.reverseParse(rc[0]-2, rc[1]-2));
        return new LegalMoves(moves, false, false);
    }

}