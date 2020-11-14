package com.omegaChess.board;

import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;
import com.omegaChess.server.OCMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class ChessBoard {
    private ChessPiece[][] board;

    public ArrayList<ChessPiece> black_pieces;
    public ArrayList<ChessPiece> white_pieces;
    public ArrayList<Move> moves;


    // Create a class constructor for the ChessBoard.java class
    public ChessBoard() {
        // create 12x12 board and initialize to all nulls
        createAndInitializeToNulls();

        black_pieces = new ArrayList<>();
        white_pieces = new ArrayList<>();
        moves = new ArrayList<>();

    }

    private void createAndInitializeToNulls() {
        board = new ChessPiece[12][12];
        for( ChessPiece[] array : board )
        {
            Arrays.fill(array, null);
        }
    }

    public void initialize()
    {
        initialize_white_pieces();
        initialize_black_pieces();
        initializeInvalidSpaces();
    }

    public ArrayList<ChessPiece> get_white_pieces()
    {
        return white_pieces;
    }

    public ArrayList<ChessPiece> get_black_pieces()
    {
        return black_pieces;
    }

    public ArrayList<Move> getMoves() { return moves; }

    private void initialize_black_pieces() {
        Champion bChampion = new Champion(this, ChessPiece.Color.BLACK);
        placePiece(bChampion, "a10");

        Rook bRook = new Rook(this, ChessPiece.Color.BLACK);
        placePiece(bRook, "b10");

        Knight bKnight = new Knight(this, ChessPiece.Color.BLACK);
        placePiece(bKnight, "c10");

        Bishop bBishop = new Bishop(this, ChessPiece.Color.BLACK);
        placePiece(bBishop, "d10");

        Queen bQueen = new Queen(this, ChessPiece.Color.BLACK);
        placePiece(bQueen, "e10");

        King bKing = new King(this, ChessPiece.Color.BLACK);
        placePiece(bKing, "f10");

        bBishop = new Bishop(this, ChessPiece.Color.BLACK);
        placePiece(bBishop, "g10");

        bKnight = new Knight(this, ChessPiece.Color.BLACK);
        placePiece(bKnight, "h10");

        bRook = new Rook(this, ChessPiece.Color.BLACK);
        placePiece(bRook, "i10");

        bChampion = new Champion(this, ChessPiece.Color.BLACK);
        placePiece(bChampion, "j10");

        Pawn bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "a9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "b9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "c9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "d9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "e9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "f9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "g9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "h9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "i9");

        bPawn = new Pawn(this, ChessPiece.Color.BLACK);
        placePiece(bPawn, "j9");

        Wizard bWizard = new Wizard(this, ChessPiece.Color.BLACK);
        placePiece(bWizard, "w3");

        bWizard = new Wizard(this, ChessPiece.Color.BLACK);
        placePiece(bWizard, "w4");
    }

    private void initialize_white_pieces() {
        Champion wChampion = new Champion(this, ChessPiece.Color.WHITE);
        placePiece(wChampion, "a1");

        Rook wRook = new Rook(this, ChessPiece.Color.WHITE);
        placePiece(wRook, "b1");

        Knight wKnight = new Knight(this, ChessPiece.Color.WHITE);
        placePiece(wKnight, "c1");

        Bishop wBishop = new Bishop(this, ChessPiece.Color.WHITE);
        placePiece(wBishop, "d1");

        Queen wQueen = new Queen(this, ChessPiece.Color.WHITE);
        placePiece(wQueen, "e1");

        King wKing = new King(this, ChessPiece.Color.WHITE);
        placePiece(wKing, "f1");

        wBishop = new Bishop(this, ChessPiece.Color.WHITE);
        placePiece(wBishop, "g1");

        wKnight = new Knight(this, ChessPiece.Color.WHITE);
        placePiece(wKnight, "h1");

        wRook = new Rook(this, ChessPiece.Color.WHITE);
        placePiece(wRook, "i1");

        wChampion = new Champion(this, ChessPiece.Color.WHITE);
        placePiece(wChampion, "j1");

        Pawn wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "a2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "b2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "c2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "d2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "e2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "f2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "g2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "h2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "i2");

        wPawn = new Pawn(this, ChessPiece.Color.WHITE);
        placePiece(wPawn, "j2");

        Wizard wWizard = new Wizard(this, ChessPiece.Color.WHITE);
        placePiece(wWizard, "w1");

        wWizard = new Wizard(this, ChessPiece.Color.WHITE);
        placePiece(wWizard, "w2");
    }

    public void initializeInvalidSpaces(){
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                int pos[] = {i, j};
                ChessPiece piece = null;
                try {
                    piece = getPiece(reverseParse(i, j));
                }catch (IllegalPositionException e){
                    e.printStackTrace();
                }

                if (piece == null && ((i == 0 || i == 11) || (j == 0 || j == 11))) {
                    piece = new InvalidSpace(this, null);
                    placePiece(piece, reverseParse(i, j));
                }
            }
        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        int[] pos;
        pos = parsePosition(position);
        if ((pos[0] < 0 || pos[0] > 11 || pos[1] < 0 || pos[1] > 11))
            throw new IllegalPositionException("Invalid position!");

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
        //System.out.println(piece);
        LegalMoves listOfMoves = piece.getNormalOrCheckMoves();
        ArrayList<String> validMoves = listOfMoves.getListOfMoves();

        boolean isEnPessant = listOfMoves.isEnPessant();
        boolean isCastle = listOfMoves.isCastle();

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

            // if move is a castle, move associated rook
            if (isCastle) {
                String rookFromPosition = null;
                String rookToPosition = null;
                ChessPiece rook = null;

                // white queen side castle
                if (toPosition.equals("d1")) {
                    rookFromPosition = "b1";
                    rookToPosition = "e1";
                }
                // white king side castle
                if (toPosition.equals("h1")) {
                    rookFromPosition = "i1";
                    rookToPosition = "g1";
                }
                // black queen side castle
                if (toPosition.equals("d10")) {
                    rookFromPosition = "b10";
                    rookToPosition = "e10";
                }
                // black king side castle
                if (toPosition.equals("h10")) {
                    rookFromPosition = "i10";
                    rookToPosition = "g10";
                }

                // get associated rook
                try {
                    rook = this.getPiece(rookFromPosition);
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }

                // place rook at new position and mark as moved
                this.placePiece(rook, rookToPosition);
                rook.setMoved(true);
                // make old position null
                this.placePiece(null, rookFromPosition);
            }

            // if pawn moves to last rank, prompt promotion
            String newRow = toPosition.substring(1);
            boolean isLastRow = newRow.equals("1") || newRow.equals("10");
            if (piece instanceof Pawn && isLastRow) {
                ChessPiece newPiece = null;

                // request promotion from user
                System.out.print("Please enter which piece you want the pawn to be promoted to: ");
                Scanner input = new Scanner(System.in);
                while (true) {
                    String promotion = input.nextLine();
                    if (promotion.equalsIgnoreCase("queen")) {
                        newPiece = new Queen(this, piece.getColor());
                        break;
                    } else if (promotion.equalsIgnoreCase("bishop")) {
                        newPiece = new Bishop(this, piece.getColor());
                        break;
                    } else if (promotion.equalsIgnoreCase("knight")) {
                        newPiece = new Knight(this, piece.getColor());
                        break;
                    } else if(promotion.equalsIgnoreCase("rook")) {
                        newPiece = new Rook(this, piece.getColor());
                        break;
                    } else if(promotion.equalsIgnoreCase("champion")) {
                        newPiece = new Champion(this, piece.getColor());
                        break;
                    } else if(promotion.equalsIgnoreCase("wizard")) {
                        newPiece = new Wizard(this, piece.getColor());
                        break;
                    } else
                        System.out.print("Not a valid piece, please enter valid omegachess piece: ");
                }
                input.close();

                // replace pawn with the promoted piece and mark as moved
                this.placePiece(null, toPosition);
                this.placePiece(newPiece, toPosition);
                newPiece.setMoved(true);
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
        for (int i = 0; i<11; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<11; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 11; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 12; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<11; i++){
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

    public String boardString(){
        OCMessage message = new OCMessage();
        int r = 0;
        for (ChessPiece[] row: board){
            int c = 0;
            for (ChessPiece piece: row){
                if (piece == null){
                    message.put(reverseParse(r, c), null);
                }else {
                    message.put(reverseParse(r, c), piece.toString());
                }
                c++;
            }
            r++;
        }

        return message.toString();
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

            if(pos[0] < 0 || pos[0] > 11 )
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
                case 'x':
                    pos[1] = 0;
                    break;
                case 'y':
                    pos[1] = 11;
                    break;
                default:
                    throw new IllegalPositionException("Illegal Row Position: " + pos[1]);
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
                case 0:
                    colRow = "x";
                    break;
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
                case 11:
                    colRow = "y";
                    break;
            }
            colRow += String.valueOf(r);
        }

        return colRow;
    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        final String blackPiecesSaveLocation = saveLocation + "black-pieces/";
        final String whitePiecesSaveLocation = saveLocation + "white-pieces/";
        final String movesSaveLocation = saveLocation + "moves/";

        // save file names and associated types of black pieces
        try {
            File saveFile = new File(blackPiecesSaveLocation + "file-names-and-associated-types.txt");

            createDirectoryIfNonExistent(blackPiecesSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (ChessPiece c : black_pieces) {
                saveWriter.write(getType(c) + "\n");
                saveWriter.write(c.getPosition() + ".txt" + "\n");
            }

            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // save black pieces in black-pieces directory
        for (ChessPiece p : black_pieces) {
            p.save(blackPiecesSaveLocation);
        }

        // save file names and associated types of white pieces
        try {
            File saveFile = new File(whitePiecesSaveLocation + "file-names-and-associated-types.txt");

            createDirectoryIfNonExistent(whitePiecesSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (ChessPiece c : white_pieces) {
                saveWriter.write(getType(c) + "\n");
                saveWriter.write(c.getPosition() + ".txt" + "\n");
            }

            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // save white pieces in white-pieces directory
        for (ChessPiece p : white_pieces) {
            p.save(whitePiecesSaveLocation);
        }

        // save moves filenames
        try {
            File saveFile = new File(movesSaveLocation + "filenames.txt");

            createDirectoryIfNonExistent(movesSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Move m : moves) {
                saveWriter.write(m.getID() + ".txt" + "\n");
            }

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save moves in moves directory
        for (Move m : moves) {
            m.save(movesSaveLocation);
        }
    }

    public static String getType(ChessPiece piece) {
        if (piece instanceof Bishop) {
            return "bishop";
        }
        if (piece instanceof Champion) {
            return "champion";
        }
        if (piece instanceof InvalidSpace) {
            return "invalid";
        }
        if (piece instanceof King) {
            return "king";
        }
        if (piece instanceof Knight) {
            return "knight";
        }
        if (piece instanceof Pawn) {
            return "pawn";
        }
        if (piece instanceof Queen) {
            return "queen";
        }
        if (piece instanceof Rook) {
            return "rook";
        }
        if (piece instanceof Wizard) {
            return "wizard";
        }
        return null;
    }

    public void load(String saveLocation) {
        final String blackPiecesSaveLocation = saveLocation + "black-pieces/";
        final String whitePiecesSaveLocation = saveLocation + "white-pieces/";
        final String movesSaveLocation = saveLocation + "moves/";

        // load black pieces
        black_pieces = new ArrayList<>();
        try {
            File loadFile = new File(blackPiecesSaveLocation + "file-names-and-associated-types.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextType = loadReader.nextLine();
                String nextFilename = loadReader.nextLine();
                ChessPiece temp = getPieceByType(nextType);
                temp.load(blackPiecesSaveLocation + nextFilename, ChessPiece.Color.BLACK, this);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + blackPiecesSaveLocation);
        }

        // load white pieces
        white_pieces = new ArrayList<>();
        try {
            File loadFile = new File(whitePiecesSaveLocation + "file-names-and-associated-types.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextType = loadReader.nextLine();
                String nextFilename = loadReader.nextLine();
                ChessPiece temp = getPieceByType(nextType);
                temp.load(whitePiecesSaveLocation + nextFilename, ChessPiece.Color.WHITE, this);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + whitePiecesSaveLocation);
        }

        // initialize board to nulls
        createAndInitializeToNulls();

        // place pieces onto board
        for (ChessPiece p : black_pieces) {
            placePiece(p, p.getPosition());
        }
        for (ChessPiece p : white_pieces) {
            placePiece(p, p.getPosition());
        }

        // load moves
        moves = new ArrayList<>();
        try {
            File loadFile = new File(movesSaveLocation + "filenames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextFilename = loadReader.nextLine();
                Move temp = new Move();
                temp.load(movesSaveLocation + nextFilename);
                moves.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + movesSaveLocation);
        }
    }

    public static ChessPiece getPieceByType(String type) {
        switch(type) {
            case "bishop":
                return new Bishop();
            case "champion":
                return new Champion();
            case "invalid":
                return new InvalidSpace();
            case "king":
                return new King();
            case "knight":
                return new Knight();
            case "pawn":
                return new Pawn();
            case "queen":
                return new Queen();
            case "rook":
                return new Rook();
            case "wizard":
                return new Wizard();
            default:
                return null;
        }
    }
}
