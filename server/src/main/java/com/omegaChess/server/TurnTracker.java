package com.omegaChess.server;

import com.omegaChess.pieces.ChessPiece;

public class TurnTracker {
    private final String player1;
    private final String player2;
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
}
