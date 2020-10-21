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
        player1Pieces = board.get_white_pieces();
        player2Pieces = board.get_black_pieces();
    }

    public boolean checkCheckmate(){
        ArrayList<ChessPiece> currentPieces = new ArrayList<>();
        boolean check = false, noBlock = true;
        switch (turn.getCurrentTurnColor()){
            case WHITE:
                currentPieces = player2Pieces;
                break;
            case BLACK:
                currentPieces = player1Pieces;
                break;
        }

        for (ChessPiece piece : currentPieces) {
            if (piece instanceof King) {
                check = ((King) piece).isKingInCheck();
                if (!check)
                    break;
            }
            while (noBlock){
                System.out.println(noBlock);
                noBlock = (piece.getNormalOrCheckMoves().getListOfMoves().equals(new ArrayList<>()));
                if (noBlock)
                    break;
            }
        }

        System.out.println(noBlock + " " + check);

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

    public TurnTracker getTurn() { return turn; }

}
