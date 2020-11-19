package com.csc14.runtimeterrors.game.BoardAssets;

import com.csc14.runtimeterrors.game.OCMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoardHelpers {

    // helper to parse list of legal moves from server
    public static List<String> parseLegalMoves(OCMessage message) {
        System.out.println("List of legal moves received: " + message.get("legal moves"));
        String moveList = message.get("legal moves");

        if ((moveList == null) || (moveList.equals("/"))) {
            return new ArrayList<>();
        }

        int end = moveList.length()-1;
        String newString = moveList.substring(1, end);
        String[] moves = newString.split("/");
        List<String> legalMoves;
        legalMoves = Arrays.asList(moves);
        return legalMoves;
    }

}