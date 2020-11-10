package com.omegaChess.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class GameRecord {
    private static int numInstances = 0;
    private int ID;
    private String winner;
    private String loser;
    private ArrayList<String> players;
    private int numMoves;
    private boolean draw;

    //constructor
    public GameRecord(String winner, String loser, int moves, boolean tie) {
        numInstances++;
        setPlayers(winner, loser);
        setDraw(tie);
        setNumMoves(moves);
        if (!tie) {
            setWinner(winner);
            setLoser(loser);
        }
        ID = numInstances;
    }

    // storage constructor
    public GameRecord() {

    }

    //getters and setters
    public void setWinner(String newWinner) { winner = newWinner; }

    public String getWinner() { return winner; }

    public void setLoser(String newLoser) { loser = newLoser; }

    public String getLoser() { return loser; }

    public void setDraw(boolean tie) { draw = tie; }

    public boolean isDraw() { return draw; }

    public void setNumMoves(int moves) { numMoves = moves; }

    public int getNumMoves() { return numMoves; }

    public void setPlayers(String player1, String player2) {
        players = new ArrayList<>(Arrays.asList(player1, player2));
    }

    public ArrayList<String> getPlayers() { return players; }

    public int getID() {
        return ID;
    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        final String gameRecordSaveLocation = saveLocation + winner + "-" + loser + "-" + numMoves + "-" + draw + "/";

        createDirectoryIfNonExistent(gameRecordSaveLocation);

// save primitives to game record save folder in primitives.txt
        try {
            File saveFile = new File(gameRecordSaveLocation + "primitives.txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(ID + "\n");
            saveWriter.write(winner + "\n");
            saveWriter.write(loser + "\n");
            saveWriter.write(players.get(0) + "\n");
            saveWriter.write(players.get(1) + "\n");
            saveWriter.write(numMoves + "\n");
            saveWriter.write(draw + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveLocation) {
        // load primitives
        try {
            File loadFile = new File(saveLocation);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                ID = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setWinner(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setLoser(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                players.add(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                players.add(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setNumMoves(Integer.parseInt(loadReader.nextLine()));
            }
            if (loadReader.hasNextLine()) {
                setDraw(Boolean.parseBoolean(loadReader.nextLine()));
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }
    }

}