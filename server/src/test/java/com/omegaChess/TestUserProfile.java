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

        assertEquals(newProfile.getNickname(), nicknameToSet);
        assertEquals(newProfile.getPassword(), passwordToSet);
        assertEquals(newProfile.getEmailAddress(), emailAddressToSet);
        assertEquals(newProfile.getGamesWon(), gamesWonToSet);
        assertEquals(newProfile.getGamesLost(), gamesLostToSet);
        assertEquals(newProfile.getGamesTied(), gamesTiedToSet);
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

        assertEquals(testProfile.getGamesWon(), initialGamesWon + 1);
        assertEquals(testProfile.getGamesLost(), initialGamesLost + 1);
        assertEquals(testProfile.getGamesTied(), initialGamesTied + 1);
    }

}
