package com.omegaChess.board;

import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
    private ChessPiece[][] board;

    // Create a class constructor for the ChessBoard.java class
    public ChessBoard() {
        // create 9x9 board and initialize to all nulls
        board = new ChessPiece[11][11];
        for( ChessPiece[] array : board )
        {
            Arrays.fill(array, null);
        }
    }

    public void initialize()
    {
        // place all of the white pieces
        placePiece(new Champion(this, ChessPiece.Color.WHITE), "a1");
        placePiece(new Rook(this, ChessPiece.Color.WHITE), "b1");
        placePiece(new Knight(this, ChessPiece.Color.WHITE), "c1");
        placePiece(new Bishop(this, ChessPiece.Color.WHITE), "d1");
        placePiece(new Queen(this, ChessPiece.Color.WHITE), "e1");
        placePiece(new King(this, ChessPiece.Color.WHITE), "f1");
        placePiece(new Bishop(this, ChessPiece.Color.WHITE), "g1");
        placePiece(new Knight(this, ChessPiece.Color.WHITE), "h1");
        placePiece(new Rook(this, ChessPiece.Color.WHITE), "i1");
        placePiece(new Champion(this, ChessPiece.Color.WHITE), "j1");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "a2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "b2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "c2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "d2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "e2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "f2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "g2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "h2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "i2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), "j2");

        // place wizard pieces
        placePiece(new Wizard(this, ChessPiece.Color.WHITE), "w1");
        placePiece(new Wizard(this, ChessPiece.Color.WHITE), "w2");

        // place all of the black pieces
        placePiece(new Champion(this, ChessPiece.Color.BLACK), "a10");
        placePiece(new Rook(this, ChessPiece.Color.BLACK), "b10");
        placePiece(new Knight(this, ChessPiece.Color.BLACK), "c10");
        placePiece(new Bishop(this, ChessPiece.Color.BLACK), "d10");
        placePiece(new Queen(this, ChessPiece.Color.BLACK), "e10");
        placePiece(new King(this, ChessPiece.Color.BLACK), "f10");
        placePiece(new Bishop(this, ChessPiece.Color.BLACK), "g10");
        placePiece(new Knight(this, ChessPiece.Color.BLACK), "h10");
        placePiece(new Rook(this, ChessPiece.Color.BLACK), "i10");
        placePiece(new Champion(this, ChessPiece.Color.BLACK), "j10");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "a9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "b9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "c9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "d9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "e9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "f9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "g9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "h9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "i9");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), "j9");

        // place wizard pieces
        placePiece(new Wizard(this, ChessPiece.Color.BLACK), "w3");
        placePiece(new Wizard(this, ChessPiece.Color.BLACK), "w4");
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        int[] pos = new int[0];
        pos = parsePosition(position);

        return board[pos[0]][pos[1]];
    }

    public boolean placePiece(ChessPiece piece, String position)
    {
        int[] pos = new int[0];
        try {
            pos = parsePosition(position);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // get color of current player
        ChessPiece.Color currentColor = piece.getColor();

        boolean valid = true;
        if( board[pos[0]][pos[1]] != null )
        {
            ChessPiece.Color oldColor = board[pos[0]][pos[1]].getColor();

            if( oldColor == currentColor )
            {
                valid = false;
            }
        }

        // if color is the same as current player or invalid column or row values
        if( valid == false )
        {
            return false;
        }

        // if opponent has piece here, it gets captured. Either way, setPosition
        // gets called and return true.
        board[pos[0]][pos[1]] = piece;
        try {
            board[pos[0]][pos[1]].setPosition(position);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }


        return true;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException
    {
        ChessPiece piece = null;

        // get the piece that is at the fromPosition
        try {
            piece = this.getPiece(fromPosition);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // get the piece's legal moves
        ArrayList<String> validMoves = piece.legalMoves();

        // check if to position is legal
        boolean found = validMoves.contains(toPosition);

        // if move is legal, move the piece
        if( found == true )
        {
            // place piece at the new position
            this.placePiece(piece, toPosition);

            // make the old position null
            this.placePiece(null, fromPosition);
        }
        // otherwise, throw illegal move exception
        else
        {
            throw new IllegalMoveException("Illegal Move from position: "
                    + fromPosition + " to position: " + toPosition);
        }
    }

    /*
     * This function was given in class. I did not write this code.
     */
    public String toString(){
        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<7; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<7; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 7; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 8; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<7; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);

        try {
            board.move("c2", "c4");
        } catch (IllegalMoveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(board);
    }

    // Method to convert string position into two integer coordinates
    public int[] parsePosition(String position) throws IllegalPositionException {
        int pos[] = new int[2];
        char col = position.charAt(0);
        int row = Integer.valueOf(position.substring(1));
        switch (col){
            case 'a':
                pos[1] = 0;
                break;
            case 'b':
                pos[1] = 1;
                break;
            case 'c':
                pos[1] = 2;
                break;
            case 'd':
                pos[1] = 3;
                break;
            case 'e':
                pos[1] = 4;
                break;
            case 'f':
                pos[1] = 5;
                break;
            case 'g':
                pos[1] = 6;
                break;
            case 'h':
                pos[1] = 7;
                break;
            case 'i':
                pos[1] = 8;
                break;
            case 'j':
                pos[1] = 9;
                break;
            case 'w':
                pos[1] = 10;    // todo: decide if 11 is ok?
                break;
            default:
                throw new IllegalPositionException("Illegal Column Position: " + pos[1]);
        }

        switch (row){
            case 0:
                pos[0] = 0;
                break;
            case 1:
                pos[0] = 1;
                break;
            case 2:
                pos[0] = 2;
                break;
            case 3:
                pos[0] = 3;
                break;
            case 4:
                pos[0] = 4;
                break;
            case 5:
                pos[0] = 5;
                break;
            case 6:
                pos[0] = 6;
                break;
            case 7:
                pos[0] = 7;
                break;
            case 8:
                pos[0] = 8;
                break;
            case 9:
                pos[0] = 9;
                break;
            case 10:
                pos[0] = 10;    // todo: decide if 11 is ok??
                break;
            default:
                throw new IllegalPositionException("Illegal Row Position: " + pos[0]);
        }
        return pos;
    }

    // A method that converts the integer coordinates of a piece to a String
    public String reverseParse(int r, int c){
        String pos = new String();
        char colRow[] = new char[2];

        colRow[1] = (char)(r+'0');
        switch (c){
            case 0:
                colRow[0] = 'a';
                break;
            case 1:
                colRow[0] = 'b';
                break;
            case 2:
                colRow[0] = 'c';
                break;
            case 3:
                colRow[0] = 'd';
                break;
            case 4:
                colRow[0] = 'e';
                break;
            case 5:
                colRow[0] = 'f';
                break;
            case 6:
                colRow[0] = 'g';
                break;
            case 7:
                colRow[0] = 'h';
                break;
            case 8:
                colRow[0] = 'i';
                break;
            case 9:
                colRow[0] = 'j';
                break;
            case 11:        // todo: decide if 11 is ok
                colRow[0] = 'w';
                break;
        }
        pos = new String(colRow);

        return pos;
    }
}
