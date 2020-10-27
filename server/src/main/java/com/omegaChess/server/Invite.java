package com.omegaChess.server;


public class Invite {

    private String inviter, invitee;
    private boolean accepted, declined;
    private OCServerData data;


    public Invite(String inviter, String invitee){
        this.inviter = inviter;
        this.invitee = invitee;
        accepted = false;
        declined = false;
    }

    public void setAccepted(boolean accepted){ this.accepted = accepted; }

    public void setDeclined(boolean declined) { this.declined = declined; }

    public Match makeMatch(){ return new Match(inviter, invitee); }

    public boolean isAccepted() { return accepted; }

    public boolean isDeclined() { return declined; }

    public String getInvitee() { return invitee; }

    public String getInviter() { return inviter; }
}
