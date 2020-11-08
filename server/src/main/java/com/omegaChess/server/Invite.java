package com.omegaChess.server;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class Invite {

    private String inviter, invitee;
    private boolean accepted, declined;

    public Invite(String inviter, String invitee){
        this.inviter = inviter;
        this.invitee = invitee;
        accepted = false;
        declined = false;
    }

    // storage constructor
    public Invite() {

    }

    public void Accept(){ accepted = true; }

    public void Decline() { declined = true; }

    public Match makeMatch(){ return new Match(inviter, invitee); }

    public boolean isAccepted() { return accepted; }

    public boolean isDeclined() { return declined; }

    public String getInvitee() { return invitee; }

    public String getInviter() { return inviter; }

    public String toString() {
        OCMessage message = new OCMessage();
        message.put("object", "invite");
        message.put("inviter", inviter);
        message.put("invitee", invitee);
        message.put("accepted", "" + accepted);
        message.put("declined", "" + declined);
        return message.toString();
    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        // save primitives to notifications save folder in primitives.txt
        try {
            File saveFile = new File(saveLocation + inviter + "-" + invitee + ".txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(inviter + "\n");
            saveWriter.write(invitee + "\n");
            saveWriter.write(accepted + "\n");
            saveWriter.write(declined + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveLocation) {
        // load primitives
        try {
            File loadFile = new File(saveLocation);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                setInviter(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setInvitee(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                setAccepted(Boolean.parseBoolean(loadReader.nextLine()));
            }
            if (loadReader.hasNextLine()) {
                setDeclined(Boolean.parseBoolean(loadReader.nextLine()));
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }
    }

    private void setInviter(String s) {
        inviter = s;
    }

    private void setInvitee(String s) {
        invitee = s;
    }

    private void setAccepted(Boolean b) {
        accepted = b;
    }

    private void setDeclined(Boolean b) {
        declined = b;
    }
}
