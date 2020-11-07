package com.omegaChess;

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

        // save data
        dataToSave.save();

        // create data object to load
        OCServerData loadedData = new OCServerData("./test-data/");
        loadedData.load();

        // make assertions to ensure loading worked properly
        assertTrue(loadedData.profileExists("Daniel"));

        // cleanup
        loadedData.deleteRootSaveFolder();

        // make sure cleanup worked
        assertFalse(loadedData.rootSaveFolderExists());

    }

}
