package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;

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
    public ArrayList<String> legalMoves()
    {
        ArrayList<String> legalMoves = new ArrayList<String>();

        // row-1, col-1
        if( row-1 >= 0 && column-1 >= 0)
        {
            legalMoves.add(board.reverseParse(row-1, column-1));
        }

        // row, col-1
        if(column-1 >= 0 )
        {
            legalMoves.add(board.reverseParse(row, column-1));
        }

        // row+1, col-1
        if(row+1 <= 7 && column-1 >= 0 )
        {
            legalMoves.add(board.reverseParse(row+1, column-1));
        }

        // row+1, col
        if(row+1 <= 7 )
        {
            legalMoves.add(board.reverseParse(row+1, column));
        }

        // row+1, col+1
        if(row+1 <= 7 && column+1 <= 7 )
        {
            legalMoves.add(board.reverseParse(row+1, column+1));
        }

        // row, col+1
        if(column+1 <= 7 )
        {
            legalMoves.add(board.reverseParse(row, column+1));
        }

        // row-1, col+1
        if(row-1 >= 0 && column+1 <= 7 )
        {
            legalMoves.add(board.reverseParse(row-1, column+1));
        }

        // row-1, col
        if(row-1 >= 0 )
        {
            legalMoves.add(board.reverseParse(row-1, column));
        }


        return legalMoves;
    }

}
