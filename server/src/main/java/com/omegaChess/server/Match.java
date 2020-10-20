package com.omegaChess.server;

import com.omegaChess.board.*;
import com.omegaChess.pieces.*;
import com.omegaChess.exceptions.*;
import com.omegaChess.server.*;

import java.util.ArrayList;

public class Match {

    private ChessBoard board;
    private UserProfile profile1, profile2;
    private TurnTracker turn;
    public ArrayList<ChessPiece> player1Pieces, player2Pieces;

    public void Match(UserProfile profile1, UserProfile profile2){
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
                noBlock = (piece.getNormalOrCheckMoves().getListOfMoves().equals(new ArrayList<>()));
                if (noBlock)
                    break;
            }
        }

        return (check && noBlock);
    }

    // forfeit a match
    public void endMatch(String quitter, String winner, int moves){ GameRecord end = new GameRecord(winner, quitter, moves, false); }


}
