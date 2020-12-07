package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Wizard extends ChessPiece {

    public Wizard(ChessBoard board, ChessPiece.Color color){super(board, color);}

    public Wizard() {
        super();
    }

    @Override
    public String toString(){
        if (this.color == Color.WHITE)
            return "whiteWizard.gif";
        else
            return "blackWizard.gif";
    }

    @Override
    public LegalMoves legalMoves(Boolean firstPass, Boolean protectedPieceChecking){
        ArrayList<String> validMoves = new ArrayList<>();

        for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            if (row+i > 11 || column-1 < 0)
                break;
            String loc = board.reverseParse(row+i, column-1);
            ChessPiece piece = null;
            try{
                piece = board.getPiece(loc);
            }catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                validMoves.add(loc);
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            if (row+1 > 11 || column+i > 11)
                break;
            String loc = board.reverseParse(row+1, column+i);
            ChessPiece piece = null;
            try{
                piece = board.getPiece(loc);
            }catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                validMoves.add(loc);
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            if (row-i < 0 || column+1 > 11)
                break;
            String loc = board.reverseParse(row-i, column+1);
            ChessPiece piece = null;
            try{
                piece = board.getPiece(loc);
            }catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                validMoves.add(loc);
        } for(int i = 1; i <= 3; i++){
            if (i == 2) continue;
            if (row-1 < 0 || column-i < 0)
                break;
            String loc = board.reverseParse(row-1, column-i);
            ChessPiece piece = null;
            try{
                piece = board.getPiece(loc);
            }catch (IllegalPositionException e){
                e.printStackTrace();
            }
            if (((piece != null && piece.getColor() != this.color)
                    && !(piece instanceof InvalidSpace)) || piece == null
                    || (piece != null && !(piece instanceof InvalidSpace)
                    && piece.getColor() == this.color && protectedPieceChecking))
                validMoves.add(loc);
        }
        ArrayList<String> diagStr = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (row + 3 > 11 || column + 1 > 11)
                        break;
                    diagStr.add(board.reverseParse(row + 3, column + 1));
                    break;
                case 1:
                    if (row - 1 < 0 || column + 3 > 11)
                        break;
                    diagStr.add(board.reverseParse(row - 1, column + 3));
                    break;
                case 2:
                    if (row - 3 < 0 || column - 1 < 0)
                        break;
                    diagStr.add(board.reverseParse(row - 3, column - 1));
                    break;
                case 3:
                    if (row + 1 > 11 || column - 3 < 0)
                        break;
                    diagStr.add(board.reverseParse(row + 1, column - 3));
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

    public LegalMoves movesToBlockCheckingPiece(String kinPos) {
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add(this.getPosition());
        return new LegalMoves(validMoves, false, false);
    }

    @Override
    public ArrayList<String> movesToCaptureWhileBlocking(String oppPos) {
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add(oppPos);
        return validMoves;
    }
}