package com.omegaChess.board;

import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
    private ChessPiece[][] board;
    public ArrayList<Move> moves;

    // Create a class constructor for the ChessBoard.java class
    public ChessBoard() {
        // create 12x12 board and initialize to all nulls
        board = new ChessPiece[12][12];
        for( ChessPiece[] array : board )
        {
            Arrays.fill(array, null);
        }
        moves = new ArrayList<>();
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

        if( piece != null )
        {
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
        }
        else
        {
            board[pos[0]][pos[1]] = null;
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
        LegalMoves listOfMoves = piece.legalMoves();
        ArrayList<String> validMoves = listOfMoves.getListOfMoves();
        boolean isEnPessant = listOfMoves.isEnPessant();

        // check if to position is legal
        boolean found = validMoves.contains(toPosition);

        // if move is legal, move the piece
        if( found == true )
        {
            // place piece at the new position
            this.placePiece(piece, toPosition);

            // make the old position null
            this.placePiece(null, fromPosition);

            //check if en pessant move made
            if (isEnPessant) {
                String pieceCol = toPosition.substring(0, 1);
                String otherPiecePos = moves.get(0).getMovedToPosition();
                String otherPieceCol = otherPiecePos.substring(0, 1);

                if (pieceCol == otherPieceCol) {
                    this.placePiece(null, otherPiecePos);
                }
            }

            piece.setMoved(true);

            //push move to front of list for easier access of most recent move
            moves.add(0, new Move(piece, fromPosition, toPosition));
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
            e.printStackTrace();
        }

        System.out.println(board);
    }

    // Method to convert string position into two integer coordinates
    public int[] parsePosition(String position) throws IllegalPositionException {
        int pos[] = new int[2];

        // handle w squares since they are different
        if (position.equals("w1"))
        {
            pos[0] = 0;
            pos[1] = 0;
        }
        else if(position.equals("w2"))
        {
            pos[0] = 0;
            pos[1] = 11;
        }
        else if(position.equals("w3"))
        {
            pos[0] = 11;
            pos[1] = 11;
        }
        else if(position.equals("w4"))
        {
            pos[0] = 11;
            pos[1] = 0;
        }
        else
        {
            char col = position.charAt(0);
            pos[0] = Integer.valueOf(position.substring(1));

            if(pos[0] < 1 || pos[0] > 10 )
            {
                throw new IllegalPositionException("Illegal Column Position: " + pos[0]);
            }


            switch (col){
                case 'a':
                    pos[1] = 1;
                    break;
                case 'b':
                    pos[1] = 2;
                    break;
                case 'c':
                    pos[1] = 3;
                    break;
                case 'd':
                    pos[1] = 4;
                    break;
                case 'e':
                    pos[1] = 5;
                    break;
                case 'f':
                    pos[1] = 6;
                    break;
                case 'g':
                    pos[1] = 7;
                    break;
                case 'h':
                    pos[1] = 8;
                    break;
                case 'i':
                    pos[1] = 9;
                    break;
                case 'j':
                    pos[1] = 10;
                    break;
                default:
                    throw new IllegalPositionException("Illegal Column Position: " + pos[1]);
            }
        }

        return pos;
    }

    // A method that converts the integer coordinates of a piece to a String
    public String reverseParse(int r, int c){
        String colRow = "";

        // handle w squares since they are different
        if (r == 0 && c == 0)
        {
            colRow = "w1";
        }
        else if(r == 0 && c == 11)
        {
            colRow = "w2";
        }
        else if(r == 11 && c == 11)
        {
            colRow = "w3";
        }
        else if(r == 11 && c == 0)
        {
            colRow = "w4";
        }
        else
        {
            switch (c){
                case 1:
                    colRow = "a";
                    break;
                case 2:
                    colRow = "b";
                    break;
                case 3:
                    colRow = "c";
                    break;
                case 4:
                    colRow = "d";
                    break;
                case 5:
                    colRow = "e";
                    break;
                case 6:
                    colRow = "f";
                    break;
                case 7:
                    colRow = "g";
                    break;
                case 8:
                    colRow = "h";
                    break;
                case 9:
                    colRow = "i";
                    break;
                case 10:
                    colRow = "j";
                    break;
            }
            colRow += String.valueOf(r);
        }

        return colRow;
    }
}
