package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

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

    protected boolean moved, firstMove;

    // Sets board and color attributes
    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
        this.moved = false;
        this.firstMove = true;
        if(color == Color.BLACK)
            board.black_pieces.add(this);
        if(color == Color.WHITE)
            board.white_pieces.add(this);
    }

    // storage constructor
    public ChessPiece() {

    }

    // default constructor

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

    public void setFirstMove(boolean isFirstMove) { this.firstMove = isFirstMove; }

    public boolean isFirstMove() { return firstMove; }

    public LegalMoves getNormalOrCheckMoves() {

        if (!(this instanceof King)) {
            King myKing = this.getMyKing();

            if (myKing.isKingInCheck()) {
                LegalMoves inCheckLegal = myKing.getCheckingPiece().movesToBlockCheckingPiece(myKing.getPosition());
                ArrayList<String> checkerLegal = inCheckLegal.getListOfMoves();
                ArrayList<String> allLegal = this.legalMoves(false).getListOfMoves();
                ArrayList<String> legalMoves = new ArrayList<>();

                for (String al : allLegal) {
                    if (checkerLegal.contains(al)) {
                        legalMoves.add(al);
                    }
                }

                return new LegalMoves(legalMoves, false, false);
            }
            else {
                return this.legalMoves(true);
            }
        }

        return this.legalMoves(true);
    }

    public King getMyKing() {
        if (this.color == Color.WHITE) {
            ArrayList<ChessPiece> myPieces = this.board.get_white_pieces();
            for (ChessPiece piece : myPieces) {
                if (piece instanceof King) {
                    return (King) piece;
                }
            }
        } else {
            ArrayList<ChessPiece> myPieces = this.board.get_black_pieces();
            for (ChessPiece piece : myPieces) {
                if (piece instanceof King) {
                    return (King) piece;
                }
            }
        }
        return null;
    }

    // used to check if moving the selected piece from it's current position will put it's king in check
    public boolean willLeaveKingInCheck() {
        King myKing = this.getMyKing();

        String myPos = this.getPosition();
        this.board.placePiece(null, myPos);
        if (myKing.isKingInCheck()) {
            this.board.placePiece(this, myPos);
            return true;
        }
        this.board.placePiece(this, myPos);
        return false;
    }

    /* To be implemented in the concrete subclasses. Returns a one-character piece corresponding
    * to the type of the piece. */
    abstract public String toString();

    /* To be implemented in the concrete subclasses. Returns a list of all legal moves that
    * piece can make. Each string in the arraylist should be the position of a possible destination
    * for the piece. Order of legal moves is irrelevant. Return an empty list if no moves are
    * available. */
    abstract public LegalMoves legalMoves(Boolean firstPass);

    /* To be implemented in concrete subclasses. Returns the list of possible locations that the
    * piece checking the king can be at in between itself and the king, including its current
    * position. Assumed that kingPos is within piece's valid move-set, since only called after
    * the king's isKingInCheck method. */
    abstract public LegalMoves movesToBlockCheckingPiece(String kingPos);

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        // save primitives to save location
        try {
            File saveFile = new File(saveLocation + getPosition() + ".txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            // save row
            saveWriter.write(row + "\n");

            // save column
            saveWriter.write(column + "\n");

            // save moved
            saveWriter.write(moved + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveLocation, ChessPiece.Color c, ChessBoard board) {

        this.board = board;

        try {
            File loadFile = new File(saveLocation);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading

            // set row
            if (loadReader.hasNextLine()) {
                row = Integer.parseInt(loadReader.nextLine());
            }

            // set column
            if (loadReader.hasNextLine()) {
                column = Integer.parseInt(loadReader.nextLine());
            }

            // set moved
            if (loadReader.hasNextLine()) {
                setMoved(Boolean.parseBoolean(loadReader.nextLine()));
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }

        // set color
        color = c;

        // add to board
        if(color == Color.BLACK)
            board.black_pieces.add(this);
        if(color == Color.WHITE)
            board.white_pieces.add(this);
    }
}
