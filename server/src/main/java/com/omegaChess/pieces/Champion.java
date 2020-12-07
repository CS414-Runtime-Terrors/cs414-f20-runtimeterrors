package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Champion extends ChessPiece{

    public Champion(ChessBoard board, ChessPiece.Color color){super(board, color);}

    public Champion() {
        super();
    }

    @Override
    public String toString(){
        if (this.color == Color.WHITE)
            return "whiteChampion.gif";
        else
            return "blackChampion.gif";
    }

    @Override
    public LegalMoves legalMoves(Boolean firstPass, Boolean protectedPieceChecking){
        ArrayList<String> validMoves = new ArrayList<>();

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
                validMoves.add(loc);
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
                validMoves.add(loc);
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
                validMoves.add(loc);
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
                validMoves.add(loc);
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
                validMoves.add(diagStr.get(i));
        }

        //check if leaving position puts own king in check on first call
        if (firstPass) {
            if (this.willLeaveKingInCheck(validMoves)) {
                if (this.captureWhileBlocking) {
                    ArrayList<String> block = this.movesToCaptureWhileBlocking(this.getMyKing().getCheckingPiece().getPosition());
                    validMoves = (ArrayList)(validMoves.stream().distinct().filter(block::contains).collect(Collectors.toList()));
                }
                else {
                    validMoves.clear();
                }
            }
        }

        return new LegalMoves(validMoves, false, false);
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

    @Override
    public ArrayList<String> movesToCaptureWhileBlocking(String oppPos) {
        int[] oPos = new int[2];
        try {
            oPos = board.parsePosition(oppPos);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        int or = oPos[0];
        int oc = oPos[1];
        int r = row;
        int c = column;

        ArrayList<String> validMoves = new ArrayList<>();
        String pos = this.getPosition();
        validMoves.add(pos);
        validMoves.add(oppPos);

        //conditions to see if king is within the sliding move-set
        if (r < or && c == oc) {
            while (r < (or - 1)) {
                r += 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (r > or && c == oc) {
            while (r > (or + 1)) {
                r -= 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (c < oc && r == or) {
            while (c < (oc - 1)) {
                c += 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }
        else if (c > oc && r == or) {
            while (c > (oc + 1)) {
                c -= 1;
                pos = board.reverseParse(r, c);
                validMoves.add(pos);
            }
        }

        return validMoves;
    }
}