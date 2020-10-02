package com.csc14.runtimeterrors.game;

import org.junit.Test;

public class UserProfileTest {

    @Test
    public boolean testConstruction() {

        UserProfile testProfile = new UserProfile("Mr. Jiggly", "baseball", "misterjiggly@gmail.com");

        return (testProfile != null);
    }

    @Test
    public boolean testSettersAndGetters() {

        UserProfile newProfile = new UserProfile("Bill", "bob", "billybob@gmail.com");

        String nicknameToSet = "Ted";
        String passwordToSet = "apple";
        String emailAddressToSet = "ted@gmail.com";

        newProfile.setNickname(nicknameToSet);
        newProfile.setPassword(passwordToSet);
        newProfile.setEmailAddress(emailAddressToSet);

        return (newProfile.getNickname().equals(nicknameToSet) &&
                newProfile.getPassword().equals(passwordToSet) &&
                newProfile.getEmailAddress().equals(emailAddressToSet));
    }

    @Test
    public boolean testIncrementers() {
        UserProfile testProfile = new UserProfile("Bill", "bob", "billybob@gmail.com");

        int initialGamesWon = testProfile.getGamesWon();
        int initialGamesLost = testProfile.getGamesLost();
        int initialGamesTied = testProfile.getGamesTied();

        testProfile.increment("gamesWon");
        testProfile.increment("gamesLost");
        testProfile.increment("gamesTied");

        return ((testProfile.getGamesWon() == initialGamesWon + 1) ||
                (testProfile.getGamesLost() == initialGamesLost + 1) ||
                (testProfile.getGamesTied() == initialGamesTied + 1));
    }

}
