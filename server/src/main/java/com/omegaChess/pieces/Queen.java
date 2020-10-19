package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Queen extends ChessPiece {
    public Queen(ChessBoard board, Color color) {
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
            return "\u265B";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2655";
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
        ArrayList<String> validMoves;

        // Queen moves are combination of bishop and rook
        validMoves = rookMoves();
        validMoves.addAll(bishopMoves());

        return new LegalMoves(validMoves, false, false);
    }

    public ArrayList<String> rookMoves()
    {
        ArrayList<String> validMoves = new ArrayList<>();
        // handle forward vertical pieces
        int tmp_row = row+1;
        ChessPiece tmp_piece = null;
        String tmp_str = board.reverseParse(tmp_row, column);

        while( tmp_row < 11 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_row += 1;
            tmp_str = board.reverseParse(tmp_row, column);
        }

        // handle backwards vertical pieces
        tmp_row = row-1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, column);

        while( tmp_row > 0 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_row -= 1;
            tmp_str = board.reverseParse(tmp_row, column);
        }

        // handle forward horizontal pieces
        int tmp_col = column+1;
        tmp_piece = null;
        tmp_str = board.reverseParse(row, tmp_col);

        while( tmp_col < 11 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_col += 1;
            tmp_str = board.reverseParse(row, tmp_col);

        }

        // handle backward horizontal pieces
        tmp_col = column-1;
        tmp_piece = null;
        tmp_str = board.reverseParse(row, tmp_col);

        while( tmp_col > 0 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_col -= 1;
            tmp_str = board.reverseParse(row, tmp_col);

        }

        return validMoves;
    }

    public ArrayList<String> bishopMoves()
    {
        ArrayList<String> validMoves = new ArrayList<>();

        // handle forward up diag pieces
        int tmp_row = row+1;
        int tmp_col = column+1;
        ChessPiece tmp_piece = null;
        String tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_row < 11 && tmp_col < 11 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_row += 1;
            tmp_col += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);
        }

        // handle forwards down diag pieces
        tmp_row = row-1;
        tmp_col = column +1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_row > 0 && tmp_col < 11 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_row -= 1;
            tmp_col += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);
        }

        // handle backwards diag up pieces
        tmp_col = column-1;
        tmp_row = row+1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_col > 0 && tmp_row < 11 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_col -= 1;
            tmp_row += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);

        }

        // handle backward diag down pieces
        tmp_col = column-1;
        tmp_row = row-1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_col > 0 && tmp_row > 0 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                break;
            }
            // if same color piece - can't move here or in this direction anymore
            else if( tmp_piece.getColor() == this.color )
            {
                break;
            }

            tmp_col -= 1;
            tmp_row -= 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);
        }

        return validMoves;
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

        int rIncrement = 0;
        int cIncrement = 0;
        if (r > kr) {
            rIncrement = -1;
        } else if (r < kr) {
            rIncrement = 1;
        }
        if (c > kc) {
            cIncrement = -1;
        } else if (c < kc) {
            cIncrement = 1;
        }

        String pos = this.getPosition();
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add(pos);

        while (r != (kr - rIncrement) || c != (kc - cIncrement)) {
            r += rIncrement;
            c += cIncrement;
            pos = board.reverseParse(r, c);
            validMoves.add(pos);
        }

        return new LegalMoves(validMoves, false, false);
    }
}

