package com.omegaChess.server;

import com.omegaChess.pieces.ChessPiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class TurnTracker {
    private String player1;
    private String player2;
    private String currentTurnPlayer;
    private ChessPiece.Color currentTurnColor = ChessPiece.Color.WHITE;

    public TurnTracker(String p1, String p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentTurnPlayer = p1;
    }

    public String getCurrentTurnPlayer() {
        return this.currentTurnPlayer;
    }

    public ChessPiece.Color getCurrentTurnColor() { return this.currentTurnColor; }

    public void switchTurn() {
        if (this.currentTurnPlayer.equals(this.player1)) {
            this.currentTurnPlayer = this.player2;
            this.currentTurnColor = ChessPiece.Color.BLACK;
        }
        else {
            this.currentTurnPlayer = this.player1;
            this.currentTurnColor = ChessPiece.Color.WHITE;
        }
    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        // save primitives to turn.txt
        try {
            File saveFile = new File(saveLocation + "turn.txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(player1 + "\n");
            saveWriter.write(player2 + "\n");
            saveWriter.write(currentTurnPlayer + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveLocation) {
        // load primitives from turn.txt
        try {
            File loadFile = new File(saveLocation + "turn.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                player1 = loadReader.nextLine();
            }
            if (loadReader.hasNextLine()) {
                player2 = loadReader.nextLine();
            }

            if (loadReader.hasNextLine()) {
                currentTurnPlayer = loadReader.nextLine();
            }

            // set color
            if (this.currentTurnPlayer.equals(this.player1)) {
                this.currentTurnColor = ChessPiece.Color.BLACK;
            }
            else {
                this.currentTurnColor = ChessPiece.Color.WHITE;
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }
    }
}
