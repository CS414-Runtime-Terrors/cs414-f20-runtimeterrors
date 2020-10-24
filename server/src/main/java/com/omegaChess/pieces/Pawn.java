package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    /*
     * This is an abstract function that will be implemented in the
     * concrete subclasses corresponding to each chess piece. It returns
     * a one character String that corresponds to the type of the piece. In
     * the Unicode6 character encoding scheme, there are characters that represet
     * each chess piece.
     */
    public String toString()
    {
        if( this.color == Color.BLACK)
        {
            return "\u265F";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2659";
        }

        return "";
    }

    /*
     * To be implemented in the concrete subclasses corresponding to each
     * chess piece. This method returns all the legal moves that a piece
     * can make based on the rules described above. Each string in the ArrayList
     * should be the position of a possible destination for the piece (in the same
     * format described above). If there are multiple legal moves, the order of
     * moves in the ArrayList does not matter. If there are no legal moves, return
     * return an empty ArrayList, i.e., the size should be zero.
     */
    public LegalMoves legalMoves(Boolean firstPass)
    {
        ArrayList<String> validMoves = new ArrayList<>();

        //check if leaving position puts own king in check on first call
        if (firstPass) {
            if (this.willLeaveKingInCheck()) {
                return new LegalMoves(validMoves, false, false);
            }
        }

        boolean isEnPessant = false;

        ChessPiece p1 = null;
        int increment;
        if (this.getColor() == Color.WHITE) {
            increment = 1;
        }
        else {
            increment = -1;
        }

        // insert the base set of moves, i.e. diagLeft/Right if there is enemy piece there. Also moving the
        // piece forward 1, 2, or 3 depending on position
        validMoves = baseMoves(increment);

        //check en pessant possibility
        if (!board.moves.isEmpty()) {
            ChessPiece lastMovePiece = board.moves.get(0).getMovedPiece();
            if (lastMovePiece.getClass() == Pawn.class) {
                try {
                    int pos[] = board.parsePosition(board.moves.get(0).getMovedToPosition());
                    if (pos[1] == column + 1) {
                        String moveStr = board.reverseParse(row + increment, column + 1);
                        if (!validMoves.contains(moveStr)) {
                            validMoves.add(moveStr);
                            isEnPessant = true;
                        }
                    } else if (pos[1] == column - 1) {
                        String moveStr = board.reverseParse(row + increment, column - 1);
                        if (!validMoves.contains(moveStr)) {
                            validMoves.add(moveStr);
                            isEnPessant = true;
                        }
                    }
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }
            }
        }

        return new LegalMoves(validMoves, isEnPessant, false);
    }

    private ArrayList<String> baseMoves(int increment){
        ArrayList<String> moves = new ArrayList<>();
        String ml = board.reverseParse(row+increment, column-1), mr = board.reverseParse(row+increment, column+1),
                m1 = board.reverseParse(row+increment,column), m2 = board.reverseParse(row+(2*increment), column),
                m3 = board.reverseParse(row+(3*increment), column);
        ChessPiece p1 = null, p2 = null, p3 = null, pl = null, pr = null;
        // need to make sure diagLeft doesn't go out of bounds
        try {
            pl = this.board.getPiece(ml);
        }catch (IllegalPositionException e){ e.printStackTrace(); }
        // need to make sure diagRight doesn't go out of bounds
        try {
            pr = this.board.getPiece(mr);
            //System.out.println(this.getColor());
        }catch (IllegalPositionException e){ e.printStackTrace(); }
        try {
            p1 = this.board.getPiece(m1);
        }catch (IllegalPositionException e){ e.printStackTrace(); }
        // if a chess piece is here and it is an opponent's piece
        if (pl != null)
            if( pl.getColor() != this.getColor()) moves.add(ml);
        if (pr != null)
            if ( pr.getColor() != this.getColor()) moves.add(mr);
        // if the spot 1 forward to move is null, add it to legal moves
        if (p1 == null) moves.add(m1); else return moves;
        // a pawn is in the initial position
        if (!this.isMoved()) {
            // pawn in initial can move 1, 2, or 3 squares vertically up to an empty
            // square but cannot leap over anything
            try {
                p2 = this.board.getPiece(m2);
            }catch (IllegalPositionException e){ e.printStackTrace(); }
            try {
                p3 = this.board.getPiece(m3);
            }catch (IllegalPositionException e){ e.printStackTrace(); }
            // if the spot 2 forward to move is null, add it to legal moves
            if (p2 == null) moves.add(m2); else return moves;
            // if the spot 3 forward to move is null, add it to legal moves
            if (p3 == null) moves.add(m3);
        }
        return moves;
    }

    public LegalMoves movesToBlockCheckingPiece(String kinPos) {
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add(this.getPosition());
        return new LegalMoves(validMoves, false, false);
    }
}

