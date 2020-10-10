package com.omegaChess.pieces;

import java.util.ArrayList;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

public abstract class ChessPiece {
    public enum Color {WHITE, BLACK}

    // The board the piece belongs to.
    protected ChessBoard board;

    // Horizontal position 0-7
    protected int row;

    // Vertical position 0-7
    protected int column;

    // Color of piece
    protected Color color;

    protected boolean moved;

    // Sets board and color attributes
    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
        this.moved = false;
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

    public boolean geMoved() {
        return this.moved;
    }

    /* To be implemented in the concrete subclasses. Returns a one-character piece corresponding
    * to the type of the piece. */
    abstract public String toString();

    /* To be implemented in the concrete subclasses. Returns a list of all legal moves that
    * piece can make. Each string in the arraylist should be the position of a possible destination
    * for the piece. Order of legal moves is irrelevant. Return an empty list if no moves are
    * available. Queen and Knight should return empty lists.*/
    abstract public ArrayList<String> legalMoves();
}
