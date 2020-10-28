package com.omegaChess.server;

import java.util.ArrayList;

public class Mailbox {

    private ArrayList<Invite> sent, received;

    public Mailbox() {
        sent = new ArrayList<>();
        received = new ArrayList<>();
    }


    public void addToSent(Invite invite) { sent.add(invite); }

    public void addToReceived(Invite invite) { received.add(invite); }

    public ArrayList<Invite> getReceived() { return received; }

    public ArrayList<Invite> getSent() { return sent; }
}
