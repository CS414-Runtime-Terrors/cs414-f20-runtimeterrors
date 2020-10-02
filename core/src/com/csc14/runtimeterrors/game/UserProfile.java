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



}
