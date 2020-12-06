package com.csc14.runtimeterrors.game.BoardAssets;

import com.csc14.runtimeterrors.game.OCMessage;

import java.util.*;

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

    // A method that converts the integer coordinates of a piece to a String
    public static String reverseParse(int r, int c){
        String colRow = "";

        // handle w squares since they are different
        if (r == 0 && c == 0)
        {
            colRow = "w1";
        }
        else if(r == 0 && c == 11)
        {
            colRow = "w2";
        }
        else if(r == 11 && c == 11)
        {
            colRow = "w3";
        }
        else if(r == 11 && c == 0)
        {
            colRow = "w4";
        }
        else
        {
            switch (c){
                case 0:
                    colRow = "x";
                    break;
                case 1:
                    colRow = "a";
                    break;
                case 2:
                    colRow = "b";
                    break;
                case 3:
                    colRow = "c";
                    break;
                case 4:
                    colRow = "d";
                    break;
                case 5:
                    colRow = "e";
                    break;
                case 6:
                    colRow = "f";
                    break;
                case 7:
                    colRow = "g";
                    break;
                case 8:
                    colRow = "h";
                    break;
                case 9:
                    colRow = "i";
                    break;
                case 10:
                    colRow = "j";
                    break;
                case 11:
                    colRow = "y";
                    break;
            }
            colRow += String.valueOf(r);
        }

        return colRow;
    }

}