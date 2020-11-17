package com.omegaChess.board;

import com.omegaChess.pieces.ChessPiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static com.omegaChess.board.ChessBoard.getPieceByType;
import static com.omegaChess.board.ChessBoard.getType;
import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class Move {
    private String piece;
    private String fromPosition;
    private String toPosition;
    private boolean firstMove;

    private static int numInstances = 0;
    private int ID;

    public Move(ChessPiece piece, String from, String to, boolean first) {
        numInstances++;

        this.piece = getType(piece);
        this.fromPosition = from;
        this.toPosition = to;
        this.firstMove = first;

        ID = numInstances;
    }

    // storage constructor
    public Move() {
        numInstances++;
    }

    // only has getters since nothing should be changed after initialization
    public ChessPiece getMovedPiece() { return getPieceByType(this.piece); }

    public String getMovedFromPosition() {
        return this.fromPosition;
    }

    public String getMovedToPosition() {
        return this.toPosition;
    }

    public boolean isFirstMove() { return firstMove; }

    public int getID() {
        return ID;
    }

    public void save(String saveFolder) {

        try {
            File saveFile = new File(saveFolder + getID() + ".txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            // save piece type
            saveWriter.write(piece + "\n");

            // save from
            saveWriter.write(fromPosition + "\n");

            // save to
            saveWriter.write(toPosition + "\n");

            // save first move
            saveWriter.write(firstMove + "\n");

            // save ID
            saveWriter.write(ID + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void load(String filename) {

        try {
            File loadFile = new File(filename);
            Scanner loadReader = new Scanner(loadFile);

            // load piece type
            if (loadReader.hasNextLine()) {
                piece = loadReader.nextLine();
            }

            // load from
            if (loadReader.hasNextLine()) {
                fromPosition = loadReader.nextLine();
            }

            // load to
            if (loadReader.hasNextLine()) {
                toPosition = loadReader.nextLine();
            }

            // load first move
            if (loadReader.hasNextLine()) {
                firstMove = Boolean.parseBoolean(loadReader.nextLine());
            }

            if (loadReader.hasNextLine()) {
                ID = Integer.parseInt(loadReader.nextLine());
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found");
        }
    }
}
