package com.omegaChess.server;

import com.omegaChess.board.*;
import com.omegaChess.pieces.*;
import com.omegaChess.exceptions.*;
import com.omegaChess.server.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class Match {

    private ChessBoard board;
    private String profile1, profile2;
    private TurnTracker turn;
    private static int matchCount = 0;
    private int matchID;

    // Profile 1 should be the profile that sent an invite
    public Match(String profile1, String profile2){
        this.profile1 = profile1;
        this.profile2 = profile2;
        board = new ChessBoard();
        turn = null;
//        matchID = ++matchCount;
        matchID = 1;
    }

    // storage constructor
    public Match() {

    }

    public int getMatchID() {
        return matchID;
    }

    public void initialize(){
        board.initialize();
        turn = new TurnTracker(profile1, profile2);
    }

    public boolean checkCheckmate(){
        ArrayList<ChessPiece> currentPieces = new ArrayList<>();
        // a boolean to track if the opponents king is in check, and if they have any piece to block the check.
        boolean check = true, noBlock = true;
        switch (turn.getCurrentTurnColor()){
            case WHITE:
                currentPieces = board.get_black_pieces();
                break;
            case BLACK:
                currentPieces = board.get_white_pieces();
                break;
        }

        // Moves king to the front of current pieces, ensures if the king is not in check it fails the test immediately
        int count = 0;
        for (ChessPiece piece : currentPieces){
            if (piece instanceof King){
                if (count == 0)
                    break;
                ChessPiece first = currentPieces.get(0);
                currentPieces.set(0, piece);
                currentPieces.set(count, first);
                break;
            }
            count++;
        }

        King king = null;

        for (ChessPiece piece : currentPieces) {
            if (piece instanceof King) {
                king = (King) piece;
                check = king.isKingInCheck();
                if (!check)
                    return check;
            }
            while (noBlock){
                noBlock = piece.getNormalOrCheckMoves().getListOfMoves().isEmpty();
                if (noBlock)
                    break;
            }
        }

        return (check && noBlock);
    }

    // forfeit a match
    public GameRecord endMatch(String loser, String winner, int moves){ return new GameRecord(winner, loser, moves, false); }

    public ChessBoard getBoard() { return board; }

    public void setBoard(ChessBoard board) { this.board = board; }

    public String getProfile1() { return profile1; }

    public String getProfile2() { return profile2; }

    public TurnTracker getTurn() { return turn; }

    public void setTurn(TurnTracker turn) { this.turn = turn; }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        final String matchSaveLocation = saveLocation + profile1 + "-" + profile2 + "/";

        createDirectoryIfNonExistent(matchSaveLocation);

        final String boardSaveLocation = matchSaveLocation + "board/";

        // save primitives to match save location in primitives.txt
        try {
            File saveFile = new File(matchSaveLocation + "primitives.txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(profile1 + "\n");
            saveWriter.write(profile2 + "\n");
            saveWriter.write(matchID + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save board
        board.save(boardSaveLocation);

        // save turn tracker in turn.txt
        turn.save(matchSaveLocation);
    }

    public void load(String saveLocation) {

        final String matchSaveLocation = saveLocation + profile1 + "-" + profile2 + "/";

        // load primitives
        try {
            File loadFile = new File(saveLocation + "primitives.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                profile1 = loadReader.nextLine();
            }
            if (loadReader.hasNextLine()) {
                profile2 = loadReader.nextLine();
            }
            if (loadReader.hasNextLine()) {
                matchID = Integer.parseInt(loadReader.nextLine());
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }

        // load board
        board = new ChessBoard();
        board.load(matchSaveLocation);

        // load turn
        turn = new TurnTracker(profile1, profile2);
        turn.load(matchSaveLocation);
    }
}
