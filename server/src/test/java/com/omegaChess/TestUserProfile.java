package com.omegaChess;

import com.omegaChess.server.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("JUnit User Profile Class Test")
public class TestUserProfile {

    @Test
    public void testConstruction() {

        UserProfile testProfile = new UserProfile("Mr. Jiggly", "baseball", "misterjiggly@gmail.com");

        assertNotNull(testProfile);
    }

    @Test
    public void testSettersAndGetters() {

        UserProfile newProfile = new UserProfile("Bill", "bob", "billybob@gmail.com");

        String nicknameToSet = "Ted";
        String passwordToSet = "apple";
        String emailAddressToSet = "ted@gmail.com";
        int gamesWonToSet = 5;
        int gamesLostToSet = 3;
        int gamesTiedToSet = 2;

        newProfile.setNickname(nicknameToSet);
        newProfile.setPassword(passwordToSet);
        newProfile.setEmailAddress(emailAddressToSet);
        newProfile.setGamesWon(gamesWonToSet);
        newProfile.setGamesLost(gamesLostToSet);
        newProfile.setGamesTied(gamesTiedToSet);

        assertEquals(nicknameToSet, newProfile.getNickname());
        assertEquals(passwordToSet, newProfile.getPassword());
        assertEquals(emailAddressToSet, newProfile.getEmailAddress());
        assertEquals(gamesWonToSet, newProfile.getGamesWon());
        assertEquals(gamesLostToSet, newProfile.getGamesLost());
        assertEquals(gamesTiedToSet, newProfile.getGamesTied());
    }

    @Test
    public void testIncrementers() {
        UserProfile testProfile = new UserProfile("Bill", "bob", "billybob@gmail.com");

        int initialGamesWon = testProfile.getGamesWon();
        int initialGamesLost = testProfile.getGamesLost();
        int initialGamesTied = testProfile.getGamesTied();

        testProfile.increment("gamesWon");
        testProfile.increment("gamesLost");
        testProfile.increment("gamesTied");

        assertEquals(initialGamesWon + 1, testProfile.getGamesWon());
        assertEquals(initialGamesLost + 1, testProfile.getGamesLost());
        assertEquals(initialGamesTied + 1, testProfile.getGamesTied());
    }

}
