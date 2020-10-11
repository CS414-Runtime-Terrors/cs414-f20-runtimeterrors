package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class King extends ChessPiece {
    public King(ChessBoard board, Color color) {
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
            return "\u265A";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2654";
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
        ArrayList<String> legalMoves = new ArrayList<String>();

        ChessPiece tmp_piece = null;
        String tmp_str;

        // row-1, col-1
        if( row-1 >= 1 && column-1 >= 1)
        {
            tmp_str = board.reverseParse(row-1, column-1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color) ||
                    !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row, col-1
        if(column-1 >= 1 )
        {
            tmp_str = board.reverseParse(row, column-1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+1, col-1
        if(row+1 <= 10 && column-1 >= 1 )
        {
            tmp_str = board.reverseParse(row+1, column-1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+1, col
        if(row+1 <= 10 )
        {
            tmp_str = board.reverseParse(row+1, column);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row+1, col+1
        if(row+1 <= 10 && column+1 <= 10 )
        {
            tmp_str = board.reverseParse(row+1, column+1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row, col+1
        if(column+1 <= 10 )
        {
            tmp_str = board.reverseParse(row, column+1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-1, col+1
        if(row-1 >= 1 && column+1 <= 10 )
        {
            tmp_str = board.reverseParse(row-1, column+1);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // row-1, col
        if(row-1 >= 1 )
        {
            tmp_str = board.reverseParse(row-1, column);
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // can't move to a friend piece or somewhere that puts in check
            if((tmp_piece != null && tmp_piece.getColor() != this.color )
                    || !is_king_in_check(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        return new LegalMoves(legalMoves, false);
    }

    // todo: implement function checking if a move will put the king in check
    public boolean is_king_in_check(String new_pos)
    {
        return false;
    }

}
