package com.csc14.runtimeterrors.game.BoardAssets;

import com.csc14.runtimeterrors.game.OCMessage;
import com.csc14.runtimeterrors.game.OmegaChess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public final class MatchScreen {

    private Board chessBoard;
    private BoardSquare fromSquare;
    private BoardSquare toSquare;
    private OmegaChess parent;

    private boolean justFinishedTurn = false;
    private JLabel turnLabel;
    JFrame gameFrame;

    public MatchScreen()
    {
    }

    public void setTable(OmegaChess omegaChess, int matchId, String whitePlayer, String blackPlayer) {
        parent = omegaChess;
        this.chessBoard = new Board(parent);

        // set chess board values up
        chessBoard.setMatchID(matchId);
        chessBoard.setMatchID(matchId);
        chessBoard.setWhitePlayer(whitePlayer);
        chessBoard.setBlackPlayer(blackPlayer);

        // set up screen components
        gameFrame = new JFrame("Omega Chess Match");
        gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createMenu());
        gameFrame.setJMenuBar(tableMenuBar);
        gameFrame.setSize(new Dimension(600, 600));

        // get the current turn and set the label
        setTurn();
        turnLabel = new JLabel("Current Turn: " + chessBoard.getTurn());
        turnLabel.setFont(new Font("Serif", Font.BOLD, 16));
        gameFrame.add(turnLabel);
        gameFrame.add(turnLabel, BorderLayout.NORTH);

        // create the chess board graphics
        final BoardGraphical boardGraphical = new BoardGraphical();
        gameFrame.add(boardGraphical, BorderLayout.CENTER);

        // finish setting game frame up
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        // create timer to check for turn updates every 10 seconds
        final java.util.Timer t = new java.util.Timer(true);
        final TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(!chessBoard.getTurn().equals(parent.getClient().getTurn(chessBoard.getMatchID()).get("user")))
                {
                    setTurn();
                    turnLabel.setText("Current Turn: " + chessBoard.getTurn());
                    chessBoard.initializeBoard();
                    chessBoard.populateBoard();
                    boardGraphical.drawBoard(chessBoard);
                }

            }
        };
        t.scheduleAtFixedRate(tt, 15000,10000);
    }

    private void setTurn(){
        chessBoard.setTurn(parent.getClient().getTurn(chessBoard.getMatchID()).get("user"));
        Color turnColor = null;
        switch (parent.getClient().getTurn(chessBoard.getMatchID()).get("color")){
            case "WHITE":
                turnColor = Color.WHITE;
                break;
            case "BLACK":
                turnColor = Color.BLACK;
                break;
        }
        chessBoard.setTurnColor(turnColor);
    }

    private void checkCheckmate(boolean startOfOppTurn) {
        OCMessage receivedMessage = parent.getClient().getCheckmate(chessBoard.getMatchID());

        if (receivedMessage.get("success").equals("true")) {
            if (receivedMessage.get("checkmate").equals("true")) {
                System.out.println("In checkmate!!");
                String title = "Checkmate!";
                String message = "";
                if (startOfOppTurn) {
                    message = "You put " + receivedMessage.get("loser") + " in checkmate!";
                } else {
                    message = receivedMessage.get("winner") + " put you in checkmate!";
                }
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                parent.getClient().endMatch(chessBoard.getMatchID(), receivedMessage.get("winner"), receivedMessage.get("loser"));
                parent.changeScreenFromMatch();
                gameFrame.setVisible(false);
                gameFrame.dispose();
            }
        }
    }

    private void checkForfeit() {
        OCMessage receivedMessage = parent.getClient().getForfeit(chessBoard.getMatchID());

        if (receivedMessage.get("success").equals("true")) {
            if (receivedMessage.get("forfeit").equals("true")) {
                String title = "Forfeit!";
                String message = chessBoard.getTurn() + " has forfeit the match!";

                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                parent.getClient().endMatch(chessBoard.getMatchID(), parent.getUser(), chessBoard.getTurn());
                parent.changeScreenFromMatch();
                gameFrame.setVisible(false);
                gameFrame.dispose();
            }
        }
    }

    private JMenu createMenu() {

        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        final JMenuItem lobbyItem = new JMenuItem("Return to Lobby");
        lobbyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.changeScreenFromMatch();
                gameFrame.setVisible(false);
                gameFrame.dispose();
            }
        });
        optionsMenu.add(lobbyItem);

        final JMenuItem forfeitGameItem = new JMenuItem("Forfeit Game");
        forfeitGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit?",
                        "Forfeit?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    if (parent.getUser().equalsIgnoreCase(chessBoard.getWhitePlayer())) {
                        parent.getClient().endMatch(chessBoard.getMatchID(), parent.getUser(), chessBoard.getBlackPlayer());
                    } else {
                        parent.getClient().endMatch(chessBoard.getMatchID(), parent.getUser(), chessBoard.getWhitePlayer());
                    }
                    parent.changeScreenFromMatch();
                    gameFrame.setVisible(false);
                    gameFrame.dispose();
                }
            }
        });
        optionsMenu.add(forfeitGameItem);

        return optionsMenu;
    }

    // corresponds to chessboard
    private class BoardGraphical extends JPanel {
        final List<GraphicalSquares> boardSquares;

        BoardGraphical() {
            super(new GridLayout(12,12));
            this.boardSquares = new ArrayList<>();
            for (int i = 0; i < BoardUtilities.NUM_TILES; i++) {
                final GraphicalSquares graphicalSquares = new GraphicalSquares(this, i);
                this.boardSquares.add(graphicalSquares);
                add(graphicalSquares);
            }
            setPreferredSize(new Dimension(400, 350));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setBackground(Color.decode("#000000"));
            validate();
        }

        void drawBoard(final Board board) {
            removeAll();
            for (final GraphicalSquares boardTile : boardSquares) {
                boardTile.drawSquare(board);
                add(boardTile);
            }
            validate();
            repaint();
        }
    }

    // corresponds to individual square on chessboard
    private class GraphicalSquares extends JPanel {
        private final int squareId;

        GraphicalSquares(final BoardGraphical boardGraphical,
                         final int squareId) {
            super(new GridBagLayout());
            this.squareId = squareId;
            setPreferredSize(new Dimension(10, 10));
            setSquareColor();
            setSquareImage(chessBoard);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    if(parent.getUser().equalsIgnoreCase(chessBoard.getTurn()))
                    {
                        if (isRightMouseButton(event)) {
                            fromSquare = null;
                            toSquare = null;
                            chessBoard.setClickedPiece(null);

                        } else if (isLeftMouseButton(event)) {
                            if(fromSquare == null)
                            {
                                // first click
                                fromSquare = chessBoard.getSquare(squareId);
                                if(!chessBoard.getSquare(squareId).hasPiece())
                                {
                                    fromSquare = null;
                                }
                                // make sure it is the correct person's turn
                                else if( fromSquare.getPieceColor() == chessBoard.getTurnColor())
                                {
                                    chessBoard.setClickedPiece(fromSquare);

                                    OCMessage receivedMessage = parent.getClient().getLegalMoves(chessBoard.getMatchID(), fromSquare.getPosition());
                                    chessBoard.setEnPessant(receivedMessage.get("enPessant").equals("true"));
                                }
                            }
                            else
                            {
                                //second click
                                toSquare = chessBoard.getSquare(squareId);

                                // get legal moves from server
                                OCMessage receivedMessage = parent.getClient().getLegalMoves(chessBoard.getMatchID(), fromSquare.getPosition());
                                List<String> legalMoves = GameBoardHelpers.parseLegalMoves(receivedMessage);

                                int[] pos = toSquare.getPosition();
                                if(legalMoves.contains(GameBoardHelpers.reverseParse(pos[0], pos[1])))
                                {
                                    parent.getClient().matchMove(chessBoard.getMatchID(), fromSquare.getPosition(), toSquare.getPosition());

                                    toSquare.setPiece(fromSquare.getCurrentPiece(), fromSquare.getPieceColor());
                                    fromSquare.removePiece();

                                    if (chessBoard.getEnPessant()) {
                                        int increment = fromSquare.getPosition()[0] - fromSquare.getPosition()[0];  //todo what is this supposed to be doing? [0]-[0] is always 0
                                        BoardSquare enPesSq = chessBoard.getSquare(fromSquare.getPosition()[0] + increment, fromSquare.getPosition()[1]);
                                        enPesSq.removePiece();
                                    }

                                    chessBoard.setEnPessant(false);

                                    String newTurn = parent.getClient().getTurn(chessBoard.getMatchID()).get("user");
                                    chessBoard.setTurn(newTurn);
                                    turnLabel.setText("Current Turn: " + newTurn);
                                    switch (parent.getClient().getTurn(chessBoard.getMatchID()).get("color")){
                                        case "White":
                                            chessBoard.setTurnColor(Color.WHITE);
                                            break;
                                        case "Black":
                                            chessBoard.setTurnColor(Color.BLACK);
                                            break;
                                    }

                                    justFinishedTurn = true;

                                    //check for forfeit
                                    checkForfeit();

                                    // just switched turns, check checkmate
                                    if (parent.getUser().equals(chessBoard.getTurn()) || justFinishedTurn) {
                                        checkCheckmate(justFinishedTurn);
                                    }
                                    justFinishedTurn = false;

                                    chessBoard.initializeBoard();
                                    chessBoard.populateBoard();
                                }
                                else
                                {
                                    chessBoard.setClickedPiece(null);

                                    chessBoard.initializeBoard();
                                    chessBoard.populateBoard();
                                }

                                fromSquare = null;
                                toSquare = null;
                            }

                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardGraphical.drawBoard(chessBoard);
                            }
                        });
                    }
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                }

                @Override
                public void mouseEntered(final MouseEvent e) {
                }

                @Override
                public void mouseReleased(final MouseEvent e) {
                }

                @Override
                public void mousePressed(final MouseEvent e) {
                }
            });
            validate();
        }

        void drawSquare(final Board board) {
            setSquareColor();
            setSquareImage(board);
            highlightLegals(board);
            validate();
            repaint();
        }

        private void highlightLegals(final Board board) {
            List<String> legalMoves = board.getPieceLegalMoves();
            if(legalMoves != null)
            {
                for (final String move : legalMoves) {
                    int[] pos = board.parsePosition(move);
                    if (board.getSquareIndex(pos[0], pos[1]) == this.squareId) {
                        setBackground(Color.YELLOW);
                    }
                }
            }

        }

        private void setSquareImage(final Board board) {
            String imagePath = "core/assets/";
            this.removeAll();
            board.populateBoard();
            if(board.getSquare(this.squareId) != null) {
                try{
                    if(!board.getSquare(this.squareId).getPiece().equals(""))
                    {
                        final BufferedImage image = ImageIO.read(new File(imagePath +
                                board.getSquare(this.squareId).getPiece()));
                        add(new JLabel(new ImageIcon(image)));
                    }

                } catch(final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void setSquareColor() {
            if( (BoardUtilities.INSTANCE.FIRST_ROW.get(this.squareId) ||
                BoardUtilities.INSTANCE.TWELFTH_ROW.get(this.squareId)) &&
                (!BoardUtilities.INSTANCE.FIRST_COLUMN.get(this.squareId) &&
                !BoardUtilities.INSTANCE.TWELFTH_COLUMN.get(this.squareId)))
            {
                setBackground(Color.decode("#000000"));
            }
            else if( (BoardUtilities.INSTANCE.FIRST_COLUMN.get(this.squareId) ||
                 BoardUtilities.INSTANCE.TWELFTH_COLUMN.get(this.squareId)) &&
                 (!BoardUtilities.INSTANCE.FIRST_ROW.get(this.squareId) &&
                  !BoardUtilities.INSTANCE.TWELFTH_ROW.get(this.squareId)))
            {
                setBackground(Color.decode("#000000"));
            }
            else if (BoardUtilities.INSTANCE.FIRST_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.THIRD_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.FIFTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.SEVENTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.NINTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.ELEVENTH_ROW.get(this.squareId)) {
                setBackground(this.squareId % 2 == 0 ? Color.decode("#808080") : Color.decode("#FFFFFF"));
            } else if(BoardUtilities.INSTANCE.SECOND_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.FOURTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.SIXTH_ROW.get(this.squareId)  ||
                    BoardUtilities.INSTANCE.EIGHTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.TENTH_ROW.get(this.squareId) ||
                    BoardUtilities.INSTANCE.TWELFTH_ROW.get(this.squareId)) {
                setBackground(this.squareId % 2 != 0 ? Color.decode("#808080") : Color.decode("#FFFFFF"));
            }
        }
    }
}