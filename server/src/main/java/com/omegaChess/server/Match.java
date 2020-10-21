package com.omegaChess.server;

import com.omegaChess.board.*;
import com.omegaChess.pieces.*;
import com.omegaChess.exceptions.*;
import com.omegaChess.server.*;

import javax.swing.*;
import java.util.ArrayList;

public class Match {

    private ChessBoard board;
    private UserProfile profile1, profile2;
    private TurnTracker turn;
    public ArrayList<ChessPiece> player1Pieces, player2Pieces;

    public Match(UserProfile profile1, UserProfile profile2){
        this.profile1 = profile1;
        this.profile2 = profile2;
        board = new ChessBoard();
        turn = null;
        player1Pieces = new ArrayList<>();
        player2Pieces = new ArrayList<>();
    }

    public void initialize(){
        board.initialize();
        turn = new TurnTracker(profile1.getNickname(), profile2.getNickname());
        setPlayer1Pieces(board.get_white_pieces());
        setPlayer2Pieces(board.get_black_pieces());
    }

    public boolean checkCheckmate(){
        ArrayList<ChessPiece> currentPieces = new ArrayList<>();
        // a boolean to track if the opponents king is in check, and if they have any piece to block the check.
        boolean check = true, noBlock = true;
        switch (turn.getCurrentTurnColor()){
            case WHITE:
                currentPieces = player2Pieces;
                break;
            case BLACK:
                currentPieces = player1Pieces;
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
    public GameRecord endMatch(String quitter, String winner, int moves){ return new GameRecord(winner, quitter, moves, false); }

    public ChessBoard getBoard() { return board; }

    public void setBoard(ChessBoard board) { this.board = board; }

    public UserProfile getProfile1() { return profile1; }

    public UserProfile getProfile2() { return profile2; }

    public ArrayList<ChessPiece> getPlayer1Pieces() { return player1Pieces; }

    public ArrayList<ChessPiece> getPlayer2Pieces() { return player2Pieces; }

    public void setPlayer1Pieces(ArrayList<ChessPiece> player1Pieces) { this.player1Pieces = player1Pieces; }

    public void setPlayer2Pieces(ArrayList<ChessPiece> player2Pieces) { this.player2Pieces = player2Pieces; }

    public TurnTracker getTurn() { return turn; }

    public void setTurn(TurnTracker turn) { this.turn = turn; }
}
