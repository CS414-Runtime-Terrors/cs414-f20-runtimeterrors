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

    public LegalMoves movesToBlockCheckingPiece(String kingPos) {
        int[] kPos = new int[2];
        try {
            kPos = board.parsePosition(kingPos);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        int kr = kPos[0];
        int kc = kPos[1];
        int r = row;
        int c = column;

        ArrayList<String> validMoves = new ArrayList<>();
        String pos = this.getPosition();
        validMoves.add(pos);

        //conditions to see if king is within the sliding move-set
        if (r < kr && c == kc) {
            while (r < (kr - 1)) {
                r += 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (r > kr && c == kc) {
            while (r > (kr + 1)) {
                r -= 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (c < kc && r == kr) {
            while (c < (kc - 1)) {
                c += 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (c > kc && r == kr) {
            while (c > (kc + 1)) {
                c -= 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }

        return new LegalMoves(validMoves, false, false);
    }
}