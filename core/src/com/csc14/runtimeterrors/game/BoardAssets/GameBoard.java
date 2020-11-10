package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.csc14.runtimeterrors.game.OCMessage;
import com.csc14.runtimeterrors.game.OmegaChess;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private OmegaChess parent;
    public ArrayList<ArrayList<BoardSquare>> gameBoard;
    private BoardSquare clickedPiece = null;
    private List<String> highlightedSquares;

    public GameBoard(OmegaChess omegaChess) {
        parent = omegaChess;
        gameBoard = new ArrayList<>();
        initializeBoard();
    }

    //create 2d arraylist of BoardSquare objects
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
        BoardSquare blank = new BoardSquare(Color.BLACK, -1, -1);

        row0.add(new BoardSquare(Color.WHITE,0, 0, "whiteWizard.png"));
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
        row0.add(new BoardSquare(Color.GRAY,0, 11, "whiteWizard.png"));

        row1.add(blank);
        row1.add(new BoardSquare(Color.WHITE, 1, 1, "whiteChampion.png"));
        row1.add(new BoardSquare(Color.GRAY, 1, 2, "whiteRook.png"));
        row1.add(new BoardSquare(Color.WHITE, 1, 3, "whiteKnight.png"));
        row1.add(new BoardSquare(Color.GRAY, 1, 4, "whiteBishop.png"));
        row1.add(new BoardSquare(Color.WHITE, 1, 5, "whiteQueen.png"));
        row1.add(new BoardSquare(Color.GRAY, 1, 6, "whiteKing.png"));
        row1.add(new BoardSquare(Color.WHITE, 1, 7, "whiteBishop.png"));
        row1.add(new BoardSquare(Color.GRAY, 1, 8, "whiteKnight.png"));
        row1.add(new BoardSquare(Color.WHITE, 1, 9, "whiteRook.png"));
        row1.add(new BoardSquare(Color.GRAY, 1, 10, "whiteChampion.png"));
        row1.add(blank);

        row2.add(blank);
        row2.add(new BoardSquare(Color.GRAY, 2, 1, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, 2, 2, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, 2, 3, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, 2, 4, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, 2, 5, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, 2, 6, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, 2, 7, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, 2, 8, "whitePawn.png"));
        row2.add(new BoardSquare(Color.GRAY, 2, 9, "whitePawn.png"));
        row2.add(new BoardSquare(Color.WHITE, 2, 10, "whitePawn.png"));
        row2.add(blank);

        row3.add(blank);
        row3.add(new BoardSquare(Color.WHITE, 3, 1));
        row3.add(new BoardSquare(Color.GRAY, 3, 2));
        row3.add(new BoardSquare(Color.WHITE, 3, 3));
        row3.add(new BoardSquare(Color.GRAY, 3, 4));
        row3.add(new BoardSquare(Color.WHITE, 3, 5));
        row3.add(new BoardSquare(Color.GRAY, 3, 6));
        row3.add(new BoardSquare(Color.WHITE, 3, 7));
        row3.add(new BoardSquare(Color.GRAY, 3, 8));
        row3.add(new BoardSquare(Color.WHITE, 3, 9));
        row3.add(new BoardSquare(Color.GRAY, 3, 10));
        row3.add(blank);

        row4.add(blank);
        row4.add(new BoardSquare(Color.GRAY, 4, 1));
        row4.add(new BoardSquare(Color.WHITE, 4, 2));
        row4.add(new BoardSquare(Color.GRAY, 4, 3));
        row4.add(new BoardSquare(Color.WHITE, 4, 4));
        row4.add(new BoardSquare(Color.GRAY, 4, 5));
        row4.add(new BoardSquare(Color.WHITE, 4, 6));
        row4.add(new BoardSquare(Color.GRAY, 4 , 7));
        row4.add(new BoardSquare(Color.WHITE, 4, 8));
        row4.add(new BoardSquare(Color.GRAY, 4, 9));
        row4.add(new BoardSquare(Color.WHITE, 4, 10));
        row4.add(blank);

        row5.add(blank);
        row5.add(new BoardSquare(Color.WHITE, 5, 1));
        row5.add(new BoardSquare(Color.GRAY, 5, 2));
        row5.add(new BoardSquare(Color.WHITE, 5, 3));
        row5.add(new BoardSquare(Color.GRAY, 5, 4));
        row5.add(new BoardSquare(Color.WHITE, 5, 5));
        row5.add(new BoardSquare(Color.GRAY, 5, 6));
        row5.add(new BoardSquare(Color.WHITE, 5, 7));
        row5.add(new BoardSquare(Color.GRAY, 5, 8));
        row5.add(new BoardSquare(Color.WHITE, 5, 9));
        row5.add(new BoardSquare(Color.GRAY, 5, 10));
        row5.add(blank);

        row6.add(blank);
        row6.add(new BoardSquare(Color.GRAY, 6, 1));
        row6.add(new BoardSquare(Color.WHITE, 6, 2));
        row6.add(new BoardSquare(Color.GRAY, 6, 3));
        row6.add(new BoardSquare(Color.WHITE, 6, 4));
        row6.add(new BoardSquare(Color.GRAY, 6, 5));
        row6.add(new BoardSquare(Color.WHITE, 6, 6));
        row6.add(new BoardSquare(Color.GRAY, 6, 7));
        row6.add(new BoardSquare(Color.WHITE, 6, 8));
        row6.add(new BoardSquare(Color.GRAY, 6, 9));
        row6.add(new BoardSquare(Color.WHITE, 6, 10));
        row6.add(blank);

        row7.add(blank);
        row7.add(new BoardSquare(Color.WHITE, 7, 1));
        row7.add(new BoardSquare(Color.GRAY, 7, 2));
        row7.add(new BoardSquare(Color.WHITE, 7, 3));
        row7.add(new BoardSquare(Color.GRAY, 7, 4));
        row7.add(new BoardSquare(Color.WHITE, 7, 5));
        row7.add(new BoardSquare(Color.GRAY, 7, 6));
        row7.add(new BoardSquare(Color.WHITE, 7, 7));
        row7.add(new BoardSquare(Color.GRAY, 7, 8));
        row7.add(new BoardSquare(Color.WHITE, 7, 9));
        row7.add(new BoardSquare(Color.GRAY, 7, 10));
        row7.add(blank);

        row8.add(blank);
        row8.add(new BoardSquare(Color.GRAY, 8, 1));
        row8.add(new BoardSquare(Color.WHITE, 8, 2));
        row8.add(new BoardSquare(Color.GRAY, 8, 3));
        row8.add(new BoardSquare(Color.WHITE, 8, 4));
        row8.add(new BoardSquare(Color.GRAY, 8, 5));
        row8.add(new BoardSquare(Color.WHITE, 8, 6));
        row8.add(new BoardSquare(Color.GRAY, 8, 7));
        row8.add(new BoardSquare(Color.WHITE, 8, 8));
        row8.add(new BoardSquare(Color.GRAY, 8, 9));
        row8.add(new BoardSquare(Color.WHITE, 8, 10));
        row8.add(blank);

        row9.add(blank);
        row9.add(new BoardSquare(Color.WHITE, 9, 1, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, 9, 2, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, 9, 3, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, 9, 4, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, 9, 5, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, 9, 6, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, 9, 7, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, 9, 8, "blackPawn.png"));
        row9.add(new BoardSquare(Color.WHITE, 9, 9, "blackPawn.png"));
        row9.add(new BoardSquare(Color.GRAY, 9, 10, "blackPawn.png"));
        row9.add(blank);

        row10.add(blank);
        row10.add(new BoardSquare(Color.GRAY, 10, 1, "blackChampion.png"));
        row10.add(new BoardSquare(Color.WHITE, 10, 2, "blackRook.png"));
        row10.add(new BoardSquare(Color.GRAY, 10, 3, "blackKnight.png"));
        row10.add(new BoardSquare(Color.WHITE, 10, 4, "blackBishop.png"));
        row10.add(new BoardSquare(Color.GRAY, 10, 5, "blackQueen.png"));
        row10.add(new BoardSquare(Color.WHITE, 10, 6, "blackKing.png"));
        row10.add(new BoardSquare(Color.GRAY, 10, 7, "blackBishop.png"));
        row10.add(new BoardSquare(Color.WHITE, 10, 8, "blackKnight.png"));
        row10.add(new BoardSquare(Color.GRAY, 10, 9, "blackRook.png"));
        row10.add(new BoardSquare(Color.WHITE, 10, 10, "blackChampion.png"));
        row10.add(blank);

        row11.add(new BoardSquare(Color.GRAY, 11, 1, "blackWizard.png"));
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
        row11.add(new BoardSquare(Color.WHITE, 11, 11, "blackWizard.png"));

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

    //get square with chess position string (i.e. "a3", "f5", etc.)
    public BoardSquare getSquare(String position) {
        int[] pos = parsePosition(position);
        return gameBoard.get(pos[0]).get(pos[1]);
    }

    //get square with integers (i.e. (1,3), (2,5), etc.)
    public BoardSquare getSquare(int row, int column) {
        return gameBoard.get(row).get(column);
    }

    //add click listener to each square
    public void addListeners() {
        for (ArrayList<BoardSquare> row : gameBoard) {
            for (final BoardSquare square : row) {
                //currently, you can click a piece and move it wherever you want
                square.addListener( new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // if first click is on a square with a piece, get that piece ready to move and highlight legal moves
                        if (square.hasPiece() && clickedPiece == null) {
                            // set square as clicked
                            clickedPiece = square;

                            // get legal moves from server
                            OCMessage receivedMessage = parent.getClient().getLegalMoves(1, clickedPiece.getPosition());
                            List<String> legalMoves = GameBoardHelpers.parseLegalMoves(receivedMessage);

                            // highlight legal moves
                            for (String position : legalMoves) {
                                getSquare(position).highlight();
                            }
                            highlightedSquares = legalMoves;
                        }
                        // if second click is on a highlighted square, move piece and unhighlight squares
                        else if (clickedPiece != null && square.isHighlighted()) {
                            // move piece
                            square.setPiece(clickedPiece.getCurrentPiece());
                            clickedPiece.removePiece();
                            clickedPiece = null;

                            // unhighlight squares
                            for (String position : highlightedSquares) {
                                getSquare(position).unHighlight();
                            }
                        }
                        // if second click is on a non-highlighted square, reset first click and unhighlight squares
                        else if (clickedPiece != null && !square.isHighlighted()) {
                            // reset clicked pieces
                            clickedPiece = null;

                            // unhighlight squares
                            for (String position : highlightedSquares) {
                                getSquare(position).unHighlight();
                            }
                        }
                    }
                });
            }
        }
    }

    //turn chess string into integers ("c3" -> (3,3))
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