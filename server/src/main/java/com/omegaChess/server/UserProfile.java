package com.omegaChess.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class UserProfile {

    // fields
    private String nickname = null;
    private String password = null;
    private String emailAddress = null;
    private int gamesWon = -1;
    private int gamesLost = -1;
    private int gamesTied = -1;
    private Mailbox mailbox = null;

    // constructor
    public UserProfile(String nick, String pass, String email) {
        setNickname(nick);
        setPassword(pass);
        setEmailAddress(email);
        mailbox = new Mailbox();
        setGamesWon(0);
        setGamesLost(0);
        setGamesTied(0);
    }

    // loading constructor
    public UserProfile() {

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

    public Mailbox getMailbox() { return mailbox; }

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

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        final String profileSaveLocation = saveLocation + getNickname() + "/";

        createDirectoryIfNonExistent(profileSaveLocation);

        final String mailboxSaveLocation = profileSaveLocation + "mailbox/";

        // save primitives to profile save folder in primitives.txt
        try {
            File saveFile = new File(profileSaveLocation + "primitives.txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(nickname + "\n");
            saveWriter.write(password + "\n");
            saveWriter.write(emailAddress + "\n");
            saveWriter.write(gamesWon + "\n");
            saveWriter.write(gamesLost + "\n");
            saveWriter.write(gamesTied + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save mailbox to mailbox save folder
        mailbox.save(mailboxSaveLocation);
    }

    public void load(String saveFolder) {

        // load primitives
        try {
            File loadFile = new File(saveFolder + "primitives.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                setNickname(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setPassword(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setEmailAddress(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setGamesWon(Integer.parseInt(loadReader.nextLine()));
            }
            if (loadReader.hasNextLine()) {
                setGamesLost(Integer.parseInt(loadReader.nextLine()));
            }
            if (loadReader.hasNextLine()) {
                setGamesTied(Integer.parseInt(loadReader.nextLine()));
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveFolder);
        }

        // load mailbox
        // TODO: make this actually load the mailbox instead of creating a new one
        mailbox = new Mailbox(); // temporary solution
    }
}
