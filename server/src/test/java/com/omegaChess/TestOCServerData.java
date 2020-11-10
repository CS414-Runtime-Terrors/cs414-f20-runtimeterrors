package com.omegaChess;

import com.omegaChess.server.Invite;
import com.omegaChess.server.Match;
import com.omegaChess.server.OCServerData;
import com.omegaChess.server.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        dataToSave.getProfile("Daniel").getMailbox().addNotification("invited", "You were invited by someone!");
        dataToSave.getProfile("Daniel").getMailbox().addToSent(new Invite("Daniel", "Falkyn"));
        dataToSave.getProfile("Daniel").getMailbox().addToReceived(new Invite("Patrick", "Daniel"));

        // save data
        dataToSave.save();

        // create data object to load
        OCServerData loadedData = new OCServerData("./test-data/");
        loadedData.load();

        // make assertions to ensure loading worked properly
        assertTrue(loadedData.profileExists("Daniel"));
        assertTrue(loadedData.getProfile("Daniel").getMailbox().getNotifications().get(0).getEvent().equals("invited"));
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
        // TODO
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
        assertEquals("Falkyn", dataToSave.getArchive().get(0).getWinner());
        assertEquals("Daniel", dataToSave.getArchive().get(0).getLoser());
        assertEquals(50, dataToSave.getArchive().get(0).getNumMoves());

        if (cleanup) {
            // cleanup
            loadedData.deleteRootSaveFolder();

            // make sure cleanup worked
            assertFalse(loadedData.rootSaveFolderExists());
        }
    }

}
