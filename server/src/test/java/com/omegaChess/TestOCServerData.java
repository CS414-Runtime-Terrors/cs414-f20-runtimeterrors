package com.omegaChess;

import com.omegaChess.server.*;
import com.omegaChess.board.ChessBoard;
import com.omegaChess.board.Move;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit OCServerData Class Test")
public class TestOCServerData {

    @Test
    public void testSavingAndLoadingUserProfiles() {
        boolean cleanup = true;

        // create data object to save
        OCServerData dataToSave = new OCServerData("./test-data/");

        // add data
        dataToSave.createProfile("Daniel", "pass", "daniel@gmail.com");
        dataToSave.getProfile("Daniel").getMailbox().addNotification(Notification.NotificationType.INVITE_REQUEST, "You were invited by someone!");
        dataToSave.getProfile("Daniel").getMailbox().addToSent(new Invite("Daniel", "Falkyn"));
        dataToSave.getProfile("Daniel").getMailbox().addToReceived(new Invite("Patrick", "Daniel"));

        // save data
        dataToSave.save();

        // create data object to load
        OCServerData loadedData = new OCServerData("./test-data/");
        loadedData.load();

        // make assertions to ensure loading worked properly
        assertTrue(loadedData.profileExists("Daniel"));
        assertTrue(loadedData.getProfile("Daniel").getMailbox().getNotifications().get(0).getEvent().name().equals("INVITE_REQUEST"));
        assertTrue(loadedData.getProfile("Daniel").getMailbox().getSent().get(0).getInvitee().equals("Falkyn"));
        assertTrue(loadedData.getProfile("Daniel").getMailbox().getReceived().get(0).getInviter().equals("Patrick"));

        if (cleanup) {
            // cleanup
            loadedData.deleteRootSaveFolder();

            // make sure cleanup worked
            assertFalse(loadedData.rootSaveFolderExists());
        }

    }

    @Test
    public void testSavingAndLoadingMatches() {
        boolean cleanup = true;

        // create data object to save
        OCServerData dataToSave = new OCServerData("./test-data/");

        // add data
        dataToSave.createProfile("Daniel", "pass", "daniel@gmail.com");
        dataToSave.createProfile("Falkyn", "pass", "falkyn@gmail.com");
        Match match = new Match("Daniel", "Falkyn");

        match.getBoard().getMoves().add(new Move(new Pawn(), "A2","A3", true));

        int ID = match.getMatchID();
        String playerWhoseTurnItIs = match.getBoard().getTurn().getCurrentTurnPlayer();
        ArrayList<ChessPiece> black_pieces = match.getBoard().get_black_pieces();
        ArrayList<ChessPiece> white_pieces = match.getBoard().get_white_pieces();
        ArrayList<Move> moves = match.getBoard().getMoves();
        dataToSave.addMatch(match);

        // save data
        dataToSave.save();

        // create data object to load
        OCServerData loadedData = new OCServerData("./test-data/");
        loadedData.load();

        // ensure primitives loaded correctly
        assertEquals(ID, loadedData.getMatches().get(0).getMatchID());
        assertEquals("Daniel", loadedData.getMatch(ID).getProfile1());
        assertEquals("Falkyn", loadedData.getMatch(ID).getProfile2());

        // ensure board pieces loaded correctly
        assertEquals(black_pieces.toString(), loadedData.getMatch(ID).getBoard().get_black_pieces().toString());
        assertEquals(white_pieces.toString(), loadedData.getMatch(ID).getBoard().get_white_pieces().toString());

        // ensure board moves loaded correctly
        assertEquals(moves.get(0).getID(), loadedData.getMatch(ID).getBoard().getMoves().get(0).getID());
        assertEquals(moves.get(0).getMovedFromPosition(), loadedData.getMatch(ID).getBoard().getMoves().get(0).getMovedFromPosition());
        assertEquals(moves.get(0).getMovedToPosition(), loadedData.getMatch(ID).getBoard().getMoves().get(0).getMovedToPosition());
        assertEquals(moves.get(0).isFirstMove(), loadedData.getMatch(ID).getBoard().getMoves().get(0).isFirstMove());
        assertEquals(ChessBoard.getType(moves.get(0).getMovedPiece()), ChessBoard.getType(loadedData.getMatch(ID).getBoard().getMoves().get(0).getMovedPiece()));

        // ensure turn tracker loaded correctly
        assertEquals(playerWhoseTurnItIs, loadedData.getMatch(ID).getBoard().getTurn().getCurrentTurnPlayer());

        if (cleanup) {
            // cleanup
            loadedData.deleteRootSaveFolder();

            // make sure cleanup worked
            assertFalse(loadedData.rootSaveFolderExists());
        }
    }

    @Test
    public void testSavingAndLoadingGameRecords() {
        boolean cleanup = true;

        // create data object to save
        OCServerData dataToSave = new OCServerData("./test-data/");

        // add data
        dataToSave.createProfile("Daniel", "pass", "daniel@gmail.com");
        dataToSave.createProfile("Falkyn", "pass", "falkyn@gmail.com");
        Match match = new Match("Daniel", "Falkyn");
        dataToSave.addMatch(match);

        dataToSave.addToArchive(dataToSave.getMatches().get(0).endMatch("Daniel", "Falkyn", 50));

        // save data
        dataToSave.save();

        // create data object to load
        OCServerData loadedData = new OCServerData("./test-data/");
        loadedData.load();

        // make assertions to ensure loading worked properly
        assertEquals("Falkyn", loadedData.getArchive().get(0).getWinner());
        assertEquals("Daniel", loadedData.getArchive().get(0).getLoser());
        assertEquals(50, loadedData.getArchive().get(0).getNumMoves());

        if (cleanup) {
            // cleanup
            loadedData.deleteRootSaveFolder();

            // make sure cleanup worked
            assertFalse(loadedData.rootSaveFolderExists());
        }
    }

}
