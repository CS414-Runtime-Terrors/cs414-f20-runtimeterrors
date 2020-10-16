package com.omegaChess.server;

public class TurnTracker {
    private final String player1;
    private final String player2;
    private String currentTurn;

    public TurnTracker(String p1, String p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentTurn = p1;
    }

    public String getCurrentTurn() {
        return this.currentTurn;
    }

    public void switchTurn() {
        if (this.currentTurn.equals(this.player1)) {
            this.currentTurn = this.player2;
        }
        else {
            this.currentTurn = this.player1;
        }
    }
}
