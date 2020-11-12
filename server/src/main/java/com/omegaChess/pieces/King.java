package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class King extends ChessPiece {
    private ChessPiece checkingPiece = null;

    public King(ChessBoard board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    public King(ChessBoard board) {
        super(board);
    }

    public ChessPiece getCheckingPiece() {
        return checkingPiece;
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
    public LegalMoves legalMoves(Boolean firstPass)
    {
        ArrayList<String> legalMoves = new ArrayList<>();
        boolean isCastle = false;

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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
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
            if((tmp_piece == null || tmp_piece.getColor() != this.color) &&
                    !willKingBeInCheck(tmp_str))
            {
                legalMoves.add(tmp_str);
            }
        }

        // castling
        if(!this.isMoved()) //check if the king has moved yet
        {
            //castle king's side
            //get pieces from spaces involved in the castle
            ChessPiece bishop = null;
            ChessPiece knight = null;
            ChessPiece rook = null;
            String bishop_str = board.reverseParse(row, column+1);
            String knight_str = board.reverseParse(row, column+2);
            String rook_str = board.reverseParse(row, column+3);
            try {
                bishop = board.getPiece(bishop_str);
                knight = board.getPiece(knight_str);
                rook = board.getPiece(rook_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // check if the castle is legal
            boolean okToCastle = rook instanceof Rook && !rook.isMoved(); //rook is there and hasn't moved
            okToCastle = okToCastle && bishop == null && knight == null; //bishop and knight spots are both empty
            okToCastle = okToCastle && !willKingBeInCheck(bishop_str) && !willKingBeInCheck(knight_str); //king is not in check in either spot

            // if legal, add move to list
            if(okToCastle) {
                legalMoves.add(knight_str);
                isCastle = true;
            }

            // castle queen's side
            // get pieces from spaces involved in the castle
            ChessPiece queen = null;
            bishop = null;
            knight = null;
            rook = null;
            String queen_str = board.reverseParse(row, column-1);
            bishop_str = board.reverseParse(row, column-2);
            knight_str = board.reverseParse(row, column-3);
            rook_str = board.reverseParse(row, column-4);
            try {
                queen = board.getPiece(queen_str);
                bishop = board.getPiece(bishop_str);
                knight = board.getPiece(knight_str);
                rook = board.getPiece(rook_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // check if the castle is legal
            okToCastle = rook instanceof Rook && !rook.isMoved(); //rook is there and hasn't moved
            okToCastle = okToCastle && queen == null && bishop == null && knight == null; //queen, bishop, and knight spots are all empty
            okToCastle = okToCastle && !willKingBeInCheck(queen_str) && !willKingBeInCheck(bishop_str); //king is not in check in either spot

            // if legal, add move to list
            if(okToCastle) {
                legalMoves.add(bishop_str);
                isCastle = true;
            }
        }

        return new LegalMoves(legalMoves, false, isCastle);
    }

    private boolean willKingBeInCheck(String new_pos)
    {
        ArrayList<ChessPiece> pieces;
        if( this.color == Color.BLACK )
        {
            pieces = board.get_white_pieces();
        }
        else
        {
            pieces = board.get_black_pieces();
        }

        // go through opposing pieces
        for (ChessPiece piece : pieces) {
            // If the piece is a pawn, checks if new_pos is one of it's capturing diagonals
            if (piece instanceof Pawn){
                int increment = 0;
                if (piece.getColor() == Color.BLACK)
                    increment = -1;
                else
                    increment = 1;
                String diagLeft = board.reverseParse(piece.row + increment, piece.column - 1),
                        diagRight = board.reverseParse(piece.row + increment, piece.column + 1);
                if (new_pos.equals(diagLeft) || new_pos.equals(diagRight))
                    return true;
            // new_pos is somewhere a white piece can move, return true that king is in check
            }else if (!(piece instanceof King) && piece.legalMoves(false).getListOfMoves().contains(new_pos)) {
                return true;
            }
            // handle king separately otherwise recursion and stackoverflow error occurs
            else if(piece instanceof King)
            {
                String kingPos = piece.getPosition();
                int[] pos = null;
                try {
                    pos = board.parsePosition(new_pos);
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }

                int r = pos[0];
                int c = pos[1];

                // check if opponent king is in one of the newPos moves
                if(     kingPos.equals(board.reverseParse(r, c-1)) ||
                        kingPos.equals(board.reverseParse(r+1, c-1)) ||
                        kingPos.equals(board.reverseParse(r+1, c)) ||
                        kingPos.equals(board.reverseParse(r+1, c+1)) ||
                        kingPos.equals(board.reverseParse(r, c+1)) ||
                        kingPos.equals(board.reverseParse(r-1, c+1)) ||
                        kingPos.equals(board.reverseParse(r-1, c)) ||
                        kingPos.equals(board.reverseParse(r-1, c-1)))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isKingInCheck() {
        String myPos = this.getPosition();

        ArrayList<ChessPiece> opponentPieces;
        if (color == Color.WHITE) {
            opponentPieces = board.get_black_pieces();
        }
        else {
            opponentPieces = board.get_white_pieces();
        }

        for (ChessPiece piece : opponentPieces) {
            if (!(piece instanceof King) && piece.legalMoves(false).getListOfMoves().contains(myPos)) {
                checkingPiece = piece;
                return true;
            }
        }

        checkingPiece = null;
        return false;
    }

    //The King will never use this function since the king can never check the other king
    public LegalMoves movesToBlockCheckingPiece(String kingPos) {
        return null;
    }
}
