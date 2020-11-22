package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Champion extends ChessPiece{

    public Champion(ChessBoard board, ChessPiece.Color color){super(board, color);}

    public Champion() {
        super();
    }

    @Override
    public String toString(){
        if (this.color == Color.WHITE)
            return "whiteChampion.png";
        else
            return "blackChampion.png";
    }

    @Override
    public LegalMoves legalMoves(Boolean firstPass, Boolean protectedPieceChecking){
        ArrayList<String> moves = new ArrayList<>();

        //check if leaving position puts own king in check on first call
        if (firstPass) {
            if (this.willLeaveKingInCheck()) {
                return new LegalMoves(moves, false, false);
            }
        }

        for (int i = 1; i <= 2; i++){
            ChessPiece piece = null;
            if (row + i > 11)
                break;
            String loc = board.reverseParse(row + i, column);
            try {
                piece = board.getPiece(loc);
            } catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace) && piece.getColor() == this.color && protectedPieceChecking))
                moves.add(loc);
        } for (int i = 1; i <= 2; i++){
            ChessPiece piece = null;
            if (row - i < 0)
                break;
            String loc = board.reverseParse(row - i, column);
            try {
                piece = board.getPiece(loc);
            } catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                moves.add(loc);
        } for (int i = 1; i <= 2; i++){
            ChessPiece piece = null;
            if (column + i > 11)
                break;
            String loc = board.reverseParse(row, column + i);
            try {
                piece = board.getPiece(loc);
            } catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                moves.add(loc);
        } for (int i = 1; i <= 2; i++){
            ChessPiece piece = null;
            if (column - i < 0)
                break;
            String loc = board.reverseParse(row, column - i);
            try {
                piece = board.getPiece(loc);
            } catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                moves.add(loc);
        }
        ArrayList<String>  diagStr = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            switch (i){
                case 0:
                    if (row+2 > 11 || column-2 < 0)
                        break;
                    diagStr.add(board.reverseParse(row+2, column-2));
                    break;
                case 1:
                    if (row+2 > 11 || column+2 > 11)
                        break;
                    diagStr.add(board.reverseParse(row+2, column+2));
                    break;
                case 2:
                    if (row-2 < 0 || column+2 > 11)
                        break;
                    diagStr.add(board.reverseParse(row-2, column+2));
                    break;
                case 3:
                    if (row-2 < 0 || column-2 < 0)
                        break;
                    diagStr.add(board.reverseParse(row-2, column-2));
                    break;
            }
        }
        ArrayList<ChessPiece> diags = new ArrayList<>();
        try{
            for (String str : diagStr)
                diags.add(board.getPiece(str));
        }catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < diags.size(); i++){
            ChessPiece piece = diags.get(i);
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                moves.add(diagStr.get(i));
        }
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