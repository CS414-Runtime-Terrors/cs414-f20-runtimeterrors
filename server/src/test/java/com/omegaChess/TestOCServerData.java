package com.omegaChess;

import com.omegaChess.server.Invite;
import com.omegaChess.server.OCServerData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("JUnit OCServerData Class Test")
public class TestOCServerData {

    @Test
    public void testSavingAndLoading() {
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

        // cleanup
//        loadedData.deleteRootSaveFolder();

        // make sure cleanup worked
//        assertFalse(loadedData.rootSaveFolderExists());

    }

}
