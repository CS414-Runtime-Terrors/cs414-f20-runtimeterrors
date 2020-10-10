package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    public Knight(ChessBoard board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    /*
     * This is an abstract function that will be implemented in the
     * concrete subclasses corresponding to each chess piece. It returns
     * a one character String that corresponds to the type of the piece. In
     * the Unicode6 character encoding scheme, there are characters that represent
     * each chess piece.
     */
    public String toString()
    {
        if( this.color == Color.BLACK)
        {
            return "\u265E";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2658";
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
    public LegalMoves legalMoves()
    {
        ArrayList<String> legalMoves = new ArrayList<>();

        ChessPiece tmp_piece = null;
        String tmp_str;

        // row+1, col-2
        if( row+1 <= 10 && column-2 >= 1)
        {
            tmp_str = board.reverseParse(row+1, column-2);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+2, col-1
        if(row+2 <= 10 && column-1 >= 1 )
        {
            tmp_str = board.reverseParse(row+2, column-1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+2, col+1
        if(row+2 <= 10 && column+1 <= 10 )
        {
            tmp_str = board.reverseParse(row+2, column+1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+1, col+2
        if(row+1 <= 10 && column+2 <= 10)
        {
            tmp_str = board.reverseParse(row+1, column+2);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-1, col+2
        if(row-1 >= 1 && column+2 <= 10 )
        {
            tmp_str = board.reverseParse(row-1, column+2);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-2, col+1
        if(row-2 >= 1 && column+1 <= 10 )
        {
            tmp_str = board.reverseParse(row-2, column+1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-2, col-1
        if(row-2 >= 1 && column-1 >= 1 )
        {
            tmp_str = board.reverseParse(row-2, column-1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-1, col-2
        if(row-1 >= 1 && column-2 >= 1)
        {
            tmp_str = board.reverseParse(row-1, column-2);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece
            if(tmp_piece == null || (tmp_piece != null && tmp_piece.getColor() != this.color))
            {
                legalMoves.add(tmp_str);
            }
        }

        return new LegalMoves(legalMoves, false);
    }
}

