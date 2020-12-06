package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.graphics.Texture;
import com.csc14.runtimeterrors.game.OCMessage;
import com.csc14.runtimeterrors.game.OmegaChess;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final OmegaChess parent;
    public ArrayList<ArrayList<BoardSquare>> gameBoard;
    private BoardSquare clickedPiece = null;
    private boolean isEnPessant;
    private String whitePlayer, blackPlayer, turn;
    private Color turnColor;
    private int matchID;

    public Board(OmegaChess omegaChess) {
        parent = omegaChess;
        gameBoard = new ArrayList<>();
        initializeBoard();
        whitePlayer = "";
        blackPlayer = "";
        turn = "";
    }

    //create 2d arraylist of BoardSquare objects
    public void initializeBoard() {
        gameBoard = new ArrayList<>();
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
        BoardSquare blank = new BoardSquare(-1, -1, "");

        row0.add(new BoardSquare(0, 0, ""));
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
        row0.add(new BoardSquare(0, 11, ""));

        row1.add(blank);
        row1.add(new BoardSquare(1, 1, ""));
        row1.add(new BoardSquare(1, 2, ""));
        row1.add(new BoardSquare( 1, 3, ""));
        row1.add(new BoardSquare(1, 4, ""));
        row1.add(new BoardSquare(1, 5, ""));
        row1.add(new BoardSquare(1, 6, ""));
        row1.add(new BoardSquare( 1, 7, ""));
        row1.add(new BoardSquare(1, 8, ""));
        row1.add(new BoardSquare(1, 9, ""));
        row1.add(new BoardSquare(1, 10, ""));
        row1.add(blank);

        row2.add(blank);
        row2.add(new BoardSquare(2, 1, ""));
        row2.add(new BoardSquare(2, 2, ""));
        row2.add(new BoardSquare(2, 3, ""));
        row2.add(new BoardSquare(2, 4, ""));
        row2.add(new BoardSquare( 2, 5, ""));
        row2.add(new BoardSquare(2, 6, ""));
        row2.add(new BoardSquare(2, 7, ""));
        row2.add(new BoardSquare( 2, 8, ""));
        row2.add(new BoardSquare(2, 9, ""));
        row2.add(new BoardSquare(2, 10, ""));
        row2.add(blank);

        row3.add(blank);
        row3.add(new BoardSquare(3, 1, ""));
        row3.add(new BoardSquare(3, 2, ""));
        row3.add(new BoardSquare( 3, 3, ""));
        row3.add(new BoardSquare( 3, 4, ""));
        row3.add(new BoardSquare( 3, 5, ""));
        row3.add(new BoardSquare( 3, 6, ""));
        row3.add(new BoardSquare( 3, 7, ""));
        row3.add(new BoardSquare( 3, 8, ""));
        row3.add(new BoardSquare( 3, 9, ""));
        row3.add(new BoardSquare( 3, 10, ""));
        row3.add(blank);

        row4.add(blank);
        row4.add(new BoardSquare( 4, 1, ""));
        row4.add(new BoardSquare(4, 2, ""));
        row4.add(new BoardSquare(4, 3, ""));
        row4.add(new BoardSquare( 4, 4, ""));
        row4.add(new BoardSquare( 4, 5, ""));
        row4.add(new BoardSquare( 4, 6, ""));
        row4.add(new BoardSquare( 4 , 7, ""));
        row4.add(new BoardSquare( 4, 8, ""));
        row4.add(new BoardSquare(4, 9, ""));
        row4.add(new BoardSquare( 4, 10, ""));
        row4.add(blank);

        row5.add(blank);
        row5.add(new BoardSquare( 5, 1, ""));
        row5.add(new BoardSquare( 5, 2, ""));
        row5.add(new BoardSquare( 5, 3, ""));
        row5.add(new BoardSquare( 5, 4, ""));
        row5.add(new BoardSquare( 5, 5, ""));
        row5.add(new BoardSquare( 5, 6, ""));
        row5.add(new BoardSquare( 5, 7, ""));
        row5.add(new BoardSquare( 5, 8, ""));
        row5.add(new BoardSquare( 5, 9, ""));
        row5.add(new BoardSquare( 5, 10, ""));
        row5.add(blank);

        row6.add(blank);
        row6.add(new BoardSquare( 6, 1, ""));
        row6.add(new BoardSquare( 6, 2, ""));
        row6.add(new BoardSquare( 6, 3, ""));
        row6.add(new BoardSquare( 6, 4, ""));
        row6.add(new BoardSquare( 6, 5, ""));
        row6.add(new BoardSquare( 6, 6, ""));
        row6.add(new BoardSquare( 6, 7, ""));
        row6.add(new BoardSquare( 6, 8, ""));
        row6.add(new BoardSquare( 6, 9, ""));
        row6.add(new BoardSquare( 6, 10, ""));
        row6.add(blank);

        row7.add(blank);
        row7.add(new BoardSquare( 7, 1, ""));
        row7.add(new BoardSquare( 7, 2, ""));
        row7.add(new BoardSquare( 7, 3, ""));
        row7.add(new BoardSquare( 7, 4, ""));
        row7.add(new BoardSquare( 7, 5, ""));
        row7.add(new BoardSquare( 7, 6, ""));
        row7.add(new BoardSquare( 7, 7, ""));
        row7.add(new BoardSquare( 7, 8, ""));
        row7.add(new BoardSquare( 7, 9, ""));
        row7.add(new BoardSquare( 7, 10, ""));
        row7.add(blank);

        row8.add(blank);
        row8.add(new BoardSquare( 8, 1, ""));
        row8.add(new BoardSquare( 8, 2, ""));
        row8.add(new BoardSquare( 8, 3, ""));
        row8.add(new BoardSquare( 8, 4, ""));
        row8.add(new BoardSquare( 8, 5, ""));
        row8.add(new BoardSquare( 8, 6, ""));
        row8.add(new BoardSquare( 8, 7, ""));
        row8.add(new BoardSquare( 8, 8, ""));
        row8.add(new BoardSquare( 8, 9, ""));
        row8.add(new BoardSquare( 8, 10, ""));
        row8.add(blank);

        row9.add(blank);
        row9.add(new BoardSquare( 9, 1, ""));
        row9.add(new BoardSquare( 9, 2, ""));
        row9.add(new BoardSquare( 9, 3, ""));
        row9.add(new BoardSquare( 9, 4, ""));
        row9.add(new BoardSquare( 9, 5, ""));
        row9.add(new BoardSquare( 9, 6, ""));
        row9.add(new BoardSquare( 9, 7, ""));
        row9.add(new BoardSquare( 9, 8, ""));
        row9.add(new BoardSquare( 9, 9, ""));
        row9.add(new BoardSquare( 9, 10, ""));
        row9.add(blank);

        row10.add(blank);
        row10.add(new BoardSquare( 10, 1, ""));
        row10.add(new BoardSquare( 10, 2, ""));
        row10.add(new BoardSquare( 10, 3, ""));
        row10.add(new BoardSquare( 10, 4, ""));
        row10.add(new BoardSquare( 10, 5, ""));
        row10.add(new BoardSquare( 10, 6, ""));
        row10.add(new BoardSquare( 10, 7, ""));
        row10.add(new BoardSquare( 10, 8, ""));
        row10.add(new BoardSquare( 10, 9, ""));
        row10.add(new BoardSquare( 10, 10, ""));
        row10.add(blank);

        row11.add(new BoardSquare( 11, 0, ""));
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
        row11.add(new BoardSquare( 11, 11, ""));

        gameBoard.add(row0);
        gameBoard.add(row1);
        gameBoard.add(row2);
        gameBoard.add(row3);
        gameBoard.add(row4);
        gameBoard.add(row5);
        gameBoard.add(row6);
        gameBoard.add(row7);
        gameBoard.add(row8);
        gameBoard.add(row9);
        gameBoard.add(row10);
        gameBoard.add(row11);
    }

    public void populateBoard(){
        OCMessage message = parent.getClient().getBoardData(matchID);
        ArrayList<String> locations = message.getKeys();
        locations.remove("success");
        for (String loc : locations){
            int[] pos = parsePosition(loc);
            String piece = message.get(loc);
            Color color;
            if (piece.contains("white")){
                color = Color.WHITE;
            }else{
                color = Color.BLACK;
            }

            gameBoard.get(pos[0]).get(pos[1]).setPiece(piece, color);
        }
    }

    public void setWhitePlayer(String whitePlayer) { this.whitePlayer = whitePlayer; }

    public String getWhitePlayer() { return whitePlayer; }

    public void setBlackPlayer(String blackPlayer) { this.blackPlayer = blackPlayer; }

    public String getBlackPlayer() { return blackPlayer; }

    public void setTurn(String turn) { this.turn = turn; }

    public String getTurn() { return turn; }

    public void setTurnColor(Color turnColor) { this.turnColor = turnColor; }

    public Color getTurnColor() { return turnColor; }

    public void setMatchID(int matchID) { this.matchID = matchID; }

    public int getMatchID() { return matchID; }

    public void setClickedPiece(BoardSquare piece){ clickedPiece = piece;}

    public List<String> getPieceLegalMoves()
    {
        if(clickedPiece != null)
        {
            OCMessage receivedMessage = parent.getClient().getLegalMoves(matchID, clickedPiece.getPosition());
            List<String> legalMoves = GameBoardHelpers.parseLegalMoves(receivedMessage);
            return legalMoves;
        }
        return null;
    }

    public BoardSquare getSquare(int tileId){
        //144 -> 12 0
        System.out.println("Tile id: " + tileId);
        int row = BoardUtilities.NUM_TILES_PER_ROW - (tileId / BoardUtilities.NUM_TILES_PER_ROW)-1;
        int col = tileId - (tileId / BoardUtilities.NUM_TILES_PER_ROW)*BoardUtilities.NUM_TILES_PER_ROW;
        System.out.println("Row: " + row + "\nCol: " + col);
        return gameBoard.get(row).get(col);
    }

    public BoardSquare getSquare(int r, int c){
        return gameBoard.get(r).get(c);
    }

    public void setEnPessant(boolean condition){ isEnPessant = condition;}

    public boolean getEnPessant(){ return isEnPessant; }

    public Integer getSquareIndex(int r, int c)
    {
        return (BoardUtilities.NUM_TILES_PER_ROW-r-1)*BoardUtilities.NUM_TILES_PER_ROW + c;
    }

    //turn chess string into integers ("c3" -> (3,3))
    public int[] parsePosition(String position) {
        int[] pos = new int[2];

        // handle w squares since they are different
        switch (position) {
            case "w1":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "w2":
                pos[0] = 0;
                pos[1] = 11;
                break;
            case "w3":
                pos[0] = 11;
                pos[1] = 11;
                break;
            case "w4":
                pos[0] = 11;
                pos[1] = 0;
                break;
            default:
                char col = position.charAt(0);
                pos[0] = Integer.parseInt(position.substring(1));

                switch (col) {
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
                break;
        }

        return pos;
    }

}
