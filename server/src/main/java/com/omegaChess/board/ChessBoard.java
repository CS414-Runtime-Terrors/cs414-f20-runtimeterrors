package com.omegaChess.board;

import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
    private ChessPiece[][] board;
    ArrayList<ChessPiece> black_pieces;
    ArrayList<ChessPiece> white_pieces;

    // Create a class constructor for the ChessBoard.java class
    public ChessBoard() {
        // create 12x12 board and initialize to all nulls
        board = new ChessPiece[12][12];
        for( ChessPiece[] array : board )
        {
            Arrays.fill(array, null);
        }

        black_pieces = new ArrayList<>();
        white_pieces = new ArrayList<>();
    }

    public void initialize()
    {
        initialize_white_pieces();
        initialize_black_pieces();
    }

    public ArrayList<ChessPiece> get_white_pieces()
    {
        return white_pieces;
    }

    public ArrayList<ChessPiece> get_black_pieces()
    {
        return black_pieces;
    }

    private void initialize_black_pieces() {
        Champion bChampion = new Champion(this, ChessPiece.Color.BLACK);
        black_pieces.add(bChampion);
        placePiece(bChampion, "a10");

        Rook bRook = new Rook(this, ChessPiece.Color.BLACK);
        black_pieces.add(bRook);
        placePiece(bRook, "b10");

        Knight bKnight = new Knight(this, ChessPiece.Color.BLACK);
        black_pieces.add(bKnight);
        placePiece(bKnight, "c10");

        Bishop bBishop = new Bishop(this, ChessPiece.Color.BLACK);
        black_pieces.add(bBishop);
        placePiece(bBishop, "d10");

        Queen bQueen = new Queen(this, ChessPiece.Color.BLACK);
        black_pieces.add(bQueen);
        placePiece(bQueen, "e10");

        King bKing = new King(this, ChessPiece.Color.BLACK);
        black_pieces.add(bKing);
        placePiece(bKing, "f10");

        bBishop = new Bishop(this, ChessPiece.Color.BLACK);
        black_pieces.add(bBishop);
        placePiece(bBishop, "g10");

        bKnight = new Knight(this, ChessPiece.Color.BLACK);
        black_pieces.add(bKnight);
        placePiece(bKnight, "h10");

        bRook = new Rook(this, ChessPiece.Color.BLACK);
        black_pieces.add(bRook);
        placePiece(bRook, "i10");

        bChampion = new Champion(this, ChessPiece.Color.BLACK);
        black_pieces.add(bChampion);
        placePiece(bChampion, "j10");

        Pawn bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "a9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "b9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "c9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "d9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "e9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "f9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "g9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "h9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "i9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        black_pieces.add(bPawn);
        placePiece(bPawn, "j9");

        Wizard bWizard = new Wizard(this, ChessPiece.Color.BLACK);
        black_pieces.add(bWizard);
        placePiece(bWizard, "w3");

        bWizard = new Wizard(this, ChessPiece.Color.BLACK);
        black_pieces.add(bWizard);
        placePiece(bWizard, "w4");
    }

    private void initialize_white_pieces() {
        Champion wChampion = new Champion(this, ChessPiece.Color.WHITE);
        white_pieces.add(wChampion);
        placePiece(wChampion, "a1");

        Rook wRook = new Rook(this, ChessPiece.Color.WHITE);
        white_pieces.add(wRook);
        placePiece(wRook, "b1");

        Knight wKnight = new Knight(this, ChessPiece.Color.WHITE);
        white_pieces.add(wKnight);
        placePiece(wKnight, "c1");

        Bishop wBishop = new Bishop(this, ChessPiece.Color.WHITE);
        white_pieces.add(wBishop);
        placePiece(wBishop, "d1");

        Queen wQueen = new Queen(this, ChessPiece.Color.WHITE);
        white_pieces.add(wQueen);
        placePiece(wQueen, "e1");

        King wKing = new King(this, ChessPiece.Color.WHITE);
        white_pieces.add(wKing);
        placePiece(wKing, "f1");

        wBishop = new Bishop(this, ChessPiece.Color.WHITE);
        white_pieces.add(wBishop);
        placePiece(wBishop, "g1");

        wKnight = new Knight(this, ChessPiece.Color.WHITE);
        white_pieces.add(wKnight);
        placePiece(wKnight, "h1");

        wRook = new Rook(this, ChessPiece.Color.WHITE);
        white_pieces.add(wRook);
        placePiece(wRook, "i1");

        wChampion = new Champion(this, ChessPiece.Color.WHITE);
        white_pieces.add(wChampion);
        placePiece(wChampion, "j1");

        Pawn wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "a2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "b2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "c2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "d2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "e2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "f2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "g2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "h2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "i2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        white_pieces.add(wPawn);
        placePiece(wPawn, "j2");

        Wizard wWizard = new Wizard(this, ChessPiece.Color.WHITE);
        white_pieces.add(wWizard);
        placePiece(wWizard, "w1");

        wWizard = new Wizard(this, ChessPiece.Color.WHITE);
        white_pieces.add(wWizard);
        placePiece(wWizard, "w2");
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
                // if opponent piece and black, remove piece from black arraylist
                else if( oldColor == ChessPiece.Color.BLACK )
                {
                    black_pieces.remove(board[pos[0]][pos[1]]);
                }
                // if opponent piece and white, remove piece from white arraylist
                else if( oldColor == ChessPiece.Color.WHITE )
                {
                    white_pieces.remove(board[pos[0]][pos[1]]);
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
