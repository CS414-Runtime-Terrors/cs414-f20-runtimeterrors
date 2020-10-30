package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class GameBoard {

    ArrayList<ArrayList<BoardSquare>> gameBoard;

    public GameBoard() {
        gameBoard = new ArrayList<>();
        initializeBoard();
    }

    public void initializeBoard() {
        ArrayList<BoardSquare> row0 = new ArrayList<>();
        ArrayList<BoardSquare> row1 = new ArrayList<>();
        ArrayList<BoardSquare> row2 = new ArrayList<>();
        ArrayList<BoardSquare> row3 = new ArrayList<>();
        ArrayList<BoardSquare> row4 = new ArrayList<>();
        ArrayList<BoardSquare> row5 = new ArrayList<>();
        ArrayList<BoardSquare> row6 = new ArrayList<>();
        ArrayList<BoardSquare> row7 = new ArrayList<>();
        ArrayList<BoardSquare> row8 = new ArrayList<>();
        ArrayList<BoardSquare> row9 = new ArrayList<>();
        ArrayList<BoardSquare> row10 = new ArrayList<>();
        ArrayList<BoardSquare> row11 = new ArrayList<>();
        BoardSquare blank = new BoardSquare(Color.BLACK);

        row0.add(new BoardSquare(Color.WHITE, "whiteWizard.png"));
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(blank);
        row0.add(new BoardSquare(Color.GRAY, "whiteWizard.png"));

        row1.add(blank);
        row1.add(new BoardSquare(Color.WHITE, "whiteChampion.png"));
        row1.add(new BoardSquare(Color.GRAY, "whiteRook.png"));
        row1.add(new BoardSquare(Color.WHITE, "whiteKnight.png"));
        row1.add(new BoardSquare(Color.GRAY, "whiteBishop.png"));
        row1.add(new BoardSquare(Color.WHITE, "whiteQueen.png"));
        row1.add(new BoardSquare(Color.GRAY, "whiteKing.png"));
        row1.add(new BoardSquare(Color.WHITE, "whiteBishop.png"));
        row1.add(new BoardSquare(Color.GRAY, "whiteKnight.png"));
        row1.add(new BoardSquare(Color.WHITE, "whiteRook.png"));
        row1.add(new BoardSquare(Color.GRAY, "whiteChampion.png"));
        row1.add(blank);

        row2.add(blank);
        row2.add(new BoardSquare(Color.GRAY, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, "whitePawn.png"));
        row2.add(blank);

        row3.add(blank);
        row3.add(new BoardSquare(Color.WHITE));
        row3.add(new BoardSquare(Color.GRAY));
        row3.add(new BoardSquare(Color.WHITE));
        row3.add(new BoardSquare(Color.GRAY));
        row3.add(new BoardSquare(Color.WHITE));
        row3.add(new BoardSquare(Color.GRAY));
        row3.add(new BoardSquare(Color.WHITE));
        row3.add(new BoardSquare(Color.GRAY));
        row3.add(new BoardSquare(Color.WHITE));
        row3.add(new BoardSquare(Color.GRAY));
        row3.add(blank);

        row4.add(blank);
        row4.add(new BoardSquare(Color.GRAY));
        row4.add(new BoardSquare(Color.WHITE));
        row4.add(new BoardSquare(Color.GRAY));
        row4.add(new BoardSquare(Color.WHITE));
        row4.add(new BoardSquare(Color.GRAY));
        row4.add(new BoardSquare(Color.WHITE));
        row4.add(new BoardSquare(Color.GRAY));
        row4.add(new BoardSquare(Color.WHITE));
        row4.add(new BoardSquare(Color.GRAY));
        row4.add(new BoardSquare(Color.WHITE));
        row4.add(blank);

        row5.add(blank);
        row5.add(new BoardSquare(Color.WHITE));
        row5.add(new BoardSquare(Color.GRAY));
        row5.add(new BoardSquare(Color.WHITE));
        row5.add(new BoardSquare(Color.GRAY));
        row5.add(new BoardSquare(Color.WHITE));
        row5.add(new BoardSquare(Color.GRAY));
        row5.add(new BoardSquare(Color.WHITE));
        row5.add(new BoardSquare(Color.GRAY));
        row5.add(new BoardSquare(Color.WHITE));
        row5.add(new BoardSquare(Color.GRAY));
        row5.add(blank);

        row6.add(blank);
        row6.add(new BoardSquare(Color.GRAY));
        row6.add(new BoardSquare(Color.WHITE));
        row6.add(new BoardSquare(Color.GRAY));
        row6.add(new BoardSquare(Color.WHITE));
        row6.add(new BoardSquare(Color.GRAY));
        row6.add(new BoardSquare(Color.WHITE));
        row6.add(new BoardSquare(Color.GRAY));
        row6.add(new BoardSquare(Color.WHITE));
        row6.add(new BoardSquare(Color.GRAY));
        row6.add(new BoardSquare(Color.WHITE));
        row6.add(blank);

        row7.add(blank);
        row7.add(new BoardSquare(Color.WHITE));
        row7.add(new BoardSquare(Color.GRAY));
        row7.add(new BoardSquare(Color.WHITE));
        row7.add(new BoardSquare(Color.GRAY));
        row7.add(new BoardSquare(Color.WHITE));
        row7.add(new BoardSquare(Color.GRAY));
        row7.add(new BoardSquare(Color.WHITE));
        row7.add(new BoardSquare(Color.GRAY));
        row7.add(new BoardSquare(Color.WHITE));
        row7.add(new BoardSquare(Color.GRAY));
        row7.add(blank);

        row8.add(blank);
        row8.add(new BoardSquare(Color.GRAY));
        row8.add(new BoardSquare(Color.WHITE));
        row8.add(new BoardSquare(Color.GRAY));
        row8.add(new BoardSquare(Color.WHITE));
        row8.add(new BoardSquare(Color.GRAY));
        row8.add(new BoardSquare(Color.WHITE));
        row8.add(new BoardSquare(Color.GRAY));
        row8.add(new BoardSquare(Color.WHITE));
        row8.add(new BoardSquare(Color.GRAY));
        row8.add(new BoardSquare(Color.WHITE));
        row8.add(blank);

        row9.add(blank);
        row9.add(new BoardSquare(Color.WHITE, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, "blackPawn.png"));
        row9.add(blank);

        row10.add(blank);
        row10.add(new BoardSquare(Color.GRAY, "blackChampion.png"));
        row10.add(new BoardSquare(Color.WHITE, "blackRook.png"));
        row10.add(new BoardSquare(Color.GRAY, "blackKnight.png"));
        row10.add(new BoardSquare(Color.WHITE, "blackBishop.png"));
        row10.add(new BoardSquare(Color.GRAY, "blackQueen.png"));
        row10.add(new BoardSquare(Color.WHITE, "blackKing.png"));
        row10.add(new BoardSquare(Color.GRAY, "blackBishop.png"));
        row10.add(new BoardSquare(Color.WHITE, "blackKnight.png"));
        row10.add(new BoardSquare(Color.GRAY, "blackRook.png"));
        row10.add(new BoardSquare(Color.WHITE, "blackChampion.png"));
        row10.add(blank);

        row11.add(new BoardSquare(Color.GRAY, "blackWizard.png"));
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(blank);
        row11.add(new BoardSquare(Color.WHITE, "blackWizard.png"));

        gameBoard.add(row11);
        gameBoard.add(row10);
        gameBoard.add(row9);
        gameBoard.add(row8);
        gameBoard.add(row7);
        gameBoard.add(row6);
        gameBoard.add(row5);
        gameBoard.add(row4);
        gameBoard.add(row3);
        gameBoard.add(row2);
        gameBoard.add(row1);
        gameBoard.add(row0);
    }

    public BoardSquare getSquare(String position) {
        int[] pos = parsePosition(position);
        return gameBoard.get(pos[0]).get(pos[1]);
    }

    public BoardSquare getSquare(int row, int column) {
        return gameBoard.get(row).get(column);
    }

    private int[] parsePosition(String position) {
        int pos[] = new int[2];

        // handle w squares since they are different
        if (position.equals("w1")) {
            pos[0] = 0;
            pos[1] = 0;
        } else if(position.equals("w2")) {
            pos[0] = 0;
            pos[1] = 11;
        } else if(position.equals("w3")) {
            pos[0] = 11;
            pos[1] = 11;
        } else if(position.equals("w4")) {
            pos[0] = 11;
            pos[1] = 0;
        } else {
            char col = position.charAt(0);
            pos[0] = Integer.parseInt(position.substring(1));

            switch (col){
                case 'a':
                    pos[1] = 1;
                    break;
                case 'b':
                    pos[1] = 2;
                    break;
                case 'c':
                    pos[1] = 3;
                    break;
                case 'd':
                    pos[1] = 4;
                    break;
                case 'e':
                    pos[1] = 5;
                    break;
                case 'f':
                    pos[1] = 6;
                    break;
                case 'g':
                    pos[1] = 7;
                    break;
                case 'h':
                    pos[1] = 8;
                    break;
                case 'i':
                    pos[1] = 9;
                    break;
                case 'j':
                    pos[1] = 10;
                    break;
                case 'x':
                    pos[1] = 0;
                    break;
                case 'y':
                    pos[1] = 11;
                    break;
            }
        }

        return pos;
    }

}