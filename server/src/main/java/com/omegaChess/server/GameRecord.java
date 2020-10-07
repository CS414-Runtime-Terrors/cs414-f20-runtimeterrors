package com.omegaChess.server;

import java.util.ArrayList;
import java.util.Arrays;

public class GameRecord {
    private String winner;
    private String loser;
    private ArrayList<String> players;
    private int numMoves;
    private boolean draw;

    //constructor
    public GameRecord(String winner, String loser, int moves, boolean tie) {
        setPlayers(winner, loser);
        setDraw(tie);
        setNumMoves(moves);
        if (!tie) {
            setWinner(winner);
            setLoser(loser);
        }
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

}