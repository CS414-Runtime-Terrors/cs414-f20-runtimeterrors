package com.omegaChess.server;

import com.omegaChess.board.*;
import com.omegaChess.pieces.*;
import com.omegaChess.exceptions.*;
import com.omegaChess.server.*;

import java.util.ArrayList;

public class Match {

    private ChessBoard board;
    private UserProfile profile1, profile2;
    private TurnTracker turns;
    public ArrayList<ChessPiece> player1Pieces, player2Pieces;

    public void Match(UserProfile profile1, UserProfile profile2){
        this.profile1 = profile1;
        this.profile2 = profile2;
        board = new ChessBoard();
        turns = null;
        player1Pieces = new ArrayList<>();
        player2Pieces = new ArrayList<>();
    }

    public void initialize(){
        board.initialize();
        turns = new TurnTracker(profile1.getNickname(), profile2.getNickname());
        player1Pieces = board.get_white_pieces();
        player2Pieces = board.get_black_pieces();
    }

    public static void main(String[] args){

    }

}
