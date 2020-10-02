package com.csc14.runtimeterrors.game;

public class UserProfile {

    private String nickname = null;
    private String password = null;
    private String emailAddress = null;
    private int gamesWon = -1;
    private int gamesLost = -1;
    private int gamesTied = -1;

    public UserProfile(String nick, String pass, String email) {
        nickname = nick;
        password = pass;
        emailAddress = email;
        gamesWon = 0;
        gamesLost = 0;
        gamesTied = 0;
    }

    // getters and setters

    public void setNickname(String newNickname) {
        nickname = newNickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailAddress(String newEmailAddress) {
        emailAddress = newEmailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setGamesWon(int i) {
        gamesWon = i;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesLost(int i) {
        gamesLost = i;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesTied(int i) {
        gamesTied = i;
    }

    public int getGamesTied() {
        return gamesTied;
    }

    // incrementers

    public boolean increment(String dataName) {
        if (dataName.equalsIgnoreCase("gamesWon")) {
            gamesWon++;
            return true;
        }

        if (dataName.equalsIgnoreCase("gamesLost")) {
            gamesLost++;
            return true;
        }

        if (dataName.equalsIgnoreCase("gamesTied")) {
            gamesTied++;
            return true;
        }

        return false;
    }

}
