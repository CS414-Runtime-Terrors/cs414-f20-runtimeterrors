package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public abstract class ChessPiece {
    public enum Color {WHITE, BLACK}

    // The board the piece belongs to.
    protected ChessBoard board;

    // Horizontal position 0-11
    protected int row;

    // Vertical position 0-11
    protected int column;

    // Color of piece
    protected Color color;

    protected boolean moved;

    // Sets board and color attributes
    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
        this.moved = false;
        if(color == Color.BLACK)
            board.black_pieces.add(this);
        if(color == Color.WHITE)
            board.white_pieces.add(this);
    }

    // Returns the color of the current piece
    public Color getColor()
    {
        return this.color;
    }

    // Returns the position of the piece in a two-character string. (1st char: a-h, 2nd char: 1-8)
    public String getPosition(){

        String pos = new String();

        pos = board.reverseParse(row, column);

        return pos;
    }

    /* Sets the position of the piece based on the argument. Throws exception if the position
    * contains illegal characters or represents a position outside the board. */
    public void setPosition(String position) throws IllegalPositionException{
        int pos[] = board.parsePosition(position);
        column = pos[1];
        row = pos[0];
    }

    public void setMoved(boolean isMoved) {
        this.moved = isMoved;
    }

    public boolean isMoved() {
        return this.moved;
    }

    public LegalMoves getNormalOrCheckMoves() {
        LegalMoves nonCheckLegal = this.legalMoves();

        if (!(this instanceof King)) {
            King myKing = null;
            if (this.color == Color.WHITE) {
                ArrayList<ChessPiece> myPieces = this.board.get_white_pieces();
                for (ChessPiece piece : myPieces) {
                    if (piece instanceof King) {
                        myKing = (King) piece;
                        break;
                    }
                }
            } else {
                ArrayList<ChessPiece> myPieces = this.board.get_black_pieces();
                for (ChessPiece piece : myPieces) {
                    if (piece instanceof King) {
                        myKing = (King) piece;
                        break;
                    }
                }
            }

            if (myKing.isKingInCheck()) {
                LegalMoves inCheckLegal = myKing.getCheckingPiece().movesToBlockCheckingPiece(myKing.getPosition());
                ArrayList<String> checkerLegal = inCheckLegal.getListOfMoves();
                ArrayList<String> allLegal = nonCheckLegal.getListOfMoves();
                ArrayList<String> legalMoves = new ArrayList<>();

                for (String al : allLegal) {
                    if (checkerLegal.contains(al)) {
                        legalMoves.add(al);
                    }
                }

                return new LegalMoves(legalMoves, false, false);
            }
        }

        return nonCheckLegal;
    }

    /* To be implemented in the concrete subclasses. Returns a one-character piece corresponding
    * to the type of the piece. */
    abstract public String toString();

    /* To be implemented in the concrete subclasses. Returns a list of all legal moves that
    * piece can make. Each string in the arraylist should be the position of a possible destination
    * for the piece. Order of legal moves is irrelevant. Return an empty list if no moves are
    * available. */
    abstract public LegalMoves legalMoves();

    /* To be implemented in concrete subclasses. Returns the list of possible locations that the
    * piece checking the king can be at in between itself and the king, including its current
    * position. Assumed that kingPos is within piece's valid move-set, since only called after
    * the king's isKingInCheck method. */
    abstract public LegalMoves movesToBlockCheckingPiece(String kingPos);
}
