package com.omegaChess;

import com.omegaChess.server.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit User Profile Class Test")
public class TestUserProfile {

    @Test
    public void testConstruction() {

        UserProfile testProfile = new UserProfile("Mr. Jiggly", "baseball", "misterjiggly@gmail.com");

        assert (testProfile != null);
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

        assert (newProfile.getNickname().equals(nicknameToSet) &&
                newProfile.getPassword().equals(passwordToSet) &&
                newProfile.getEmailAddress().equals(emailAddressToSet) &&
                newProfile.getGamesWon() == gamesWonToSet &&
                newProfile.getGamesLost() == gamesLostToSet &&
                newProfile.getGamesTied() == gamesTiedToSet);
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

        assert ((testProfile.getGamesWon() == initialGamesWon + 1) &&
                (testProfile.getGamesLost() == initialGamesLost + 1) &&
                (testProfile.getGamesTied() == initialGamesTied + 1));
    }

}
