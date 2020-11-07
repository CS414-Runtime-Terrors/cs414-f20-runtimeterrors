package com.omegaChess.server;

import java.util.ArrayList;

// this class is responsible for actually processing any input from a client
public class OCProtocol {

    private final OCServerData serverData;

    public OCProtocol(OCServerData data) {
        serverData = data;
    }

    public String processInput(String input) {
        String toReturn = "";
        try {
            OCMessage receivedMessage = new OCMessage();
            receivedMessage.fromString(input);

            String process = receivedMessage.get("process");

            switch(process) {
                case "square":
                    toReturn = squareInput(receivedMessage);
                    break;
                case "register":
                    toReturn = registerUser(receivedMessage);
                    break;
                case "unregister":
                    toReturn = unregisterUser(receivedMessage);
                    break;
                case "login":
                    toReturn = loginUser(receivedMessage);
                    break;
                case "get profile data":
                    toReturn = getProfileData(receivedMessage);
                    break;
                case "invite":
                    toReturn = sendInvite(receivedMessage);
                    break;
                case "invites sent":
                    toReturn = getSentInvites(receivedMessage);
                    break;
                case "invites received":
                    toReturn = getReceivedInvites(receivedMessage);
                    break;
                case "get notifications":
                    toReturn = getNotifications(receivedMessage);
                    break;
                case "invite response":
                    toReturn  = inviteResponse(receivedMessage);
                    break;
                default:
                    OCMessage message = new OCMessage();
                    message.put("success", "false");
                    message.put("reason", "process not recognized");

                    toReturn = message.toString();
                    break;
            }
        } catch (Exception e) {
            OCMessage message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Something went wrong when processing input.");

            toReturn = message.toString();

            System.out.println("Something went wrong when processing input.");
        }

        return toReturn;
    }

    private String squareInput(OCMessage receivedMessage) {
        String inputLine = receivedMessage.get("number");

        System.out.println("Attempting to square " + inputLine + "...");
        int number;

        try {
            number = Integer.parseInt(inputLine);
        } catch (Exception e) {
            OCMessage message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Wrong input!");

            return message.toString();
        }

        int square = number * number;
        System.out.println("Square: " + square);


        OCMessage message = new OCMessage();
        message.put("success", "true");
        message.put("answer", "Square of " + number + " is " + square);

        return message.toString();
    }

    private String registerUser(OCMessage receivedMessage) {

        String email = receivedMessage.get("email");
        String nickname = receivedMessage.get("nickname");
        String password = receivedMessage.get("password");

        System.out.println("Attempting to register new user: " + nickname);

        Boolean success = serverData.createProfile(nickname, password, email);

        OCMessage message = new OCMessage();
        if (success) {
            message.put("success", "true");

            System.out.println("Registered!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "nickname/email was taken");

            System.out.println("Nickname or email was taken.");

        }
        return message.toString();
    }

    private String unregisterUser(OCMessage receivedMessage) {

        String nickname = receivedMessage.get("nickname");

        System.out.println("Attempting to unregister user: " + nickname);

        Boolean success = serverData.removeProfile(nickname);
        for (GameRecord game : serverData.getArchive()){
            if (game.getLoser().equalsIgnoreCase(nickname))
                game.setLoser("[deleted]");
            if (game.getWinner().equalsIgnoreCase(nickname))
                game.setWinner("[deleted]");
        }
        for (Match match : serverData.getMatches()){
            if (match.getProfile1().equalsIgnoreCase(nickname)){
                match.endMatch("[deleted]", match.getProfile2(), match.getBoard().getMoves().size());
                serverData.getProfile(match.getProfile2()).getMailbox().addNotification("Match ended", "Other user deleted their account before the game ended.");
            }
            if (match.getProfile2().equalsIgnoreCase(nickname)){
                match.endMatch("[deleted]", match.getProfile1(), match.getBoard().getMoves().size());
                serverData.getProfile(match.getProfile1()).getMailbox().addNotification("Match ended", "Other user deleted their account before the game ended.");
            }
        }
        for (UserProfile player : serverData.getProfiles()){
            Mailbox mail = player.getMailbox();
            for (Invite invite : mail.getReceived()){
                if (invite.getInviter().equalsIgnoreCase(nickname)) {
                    invite.Decline();
                    mail.removeFromReceived(invite);
                    mail.addNotification("Invite Canceled", "Other user deleted their account before a response was made.");
                }
            }for (Invite invite: mail.getSent()){
                if (invite.getInvitee().equalsIgnoreCase(nickname)) {
                    invite.Decline();
                    mail.removeFromSent(invite);
                    mail.addNotification("Declined Invite", "Other user deleted their account before responding.");
                }
            }
        }


        OCMessage message = new OCMessage();
        if (success) {
            message.put("success", "true");

            System.out.println("Unregistered!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "nickname wasn't found");

            System.out.println("Nickname wasn't found.");

        }
        return message.toString();
    }

    private String loginUser(OCMessage receivedMessage) {

        String nickname = receivedMessage.get("nickname");
        String password = receivedMessage.get("password");

        System.out.println("Attempting to login user: " + nickname);

        OCMessage message = new OCMessage();

        if (!serverData.profileExists(nickname)) {
            // profile doesn't exist
            message.put("success", "false");
            message.put("reason", "nickname wasn't found");

            System.out.println("Nickname wasn't found.");
            return message.toString();
        }

        Boolean success = serverData.checkPassword(nickname, password);

        if (success) {
            message.put("success", "true");

            System.out.println("Logged in!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "wrong password");

            System.out.println("Wrong password.");

        }
        return message.toString();
    }

    private String getProfileData(OCMessage receivedMessage) {

        String nickname = receivedMessage.get("nickname");

        System.out.println("Attempting to get profile data for user: " + nickname);

        OCMessage message = new OCMessage();

        if (!serverData.profileExists(nickname)) {
            // profile doesn't exist
            message.put("success", "false");
            message.put("reason", "nickname wasn't found");

            System.out.println("Nickname wasn't found.");
            return message.toString();
        }

        message.put("success", "true");
        message.put("nickname", nickname);
        message.put("gamesWon", "" + serverData.getProfile(nickname).getGamesWon());
        message.put("gamesLost", "" + serverData.getProfile(nickname).getGamesLost());
        message.put("gamesTied", "" + serverData.getProfile(nickname).getGamesTied());

        return message.toString();

    }

   private String sendInvite(OCMessage receivedMessage){

        String inviter = receivedMessage.get("inviter");
        String invitee = receivedMessage.get("invitee");

       System.out.println("Attempting to send invite from " + inviter + " to " + invitee);

       OCMessage message = new OCMessage();

       if (!serverData.profileExists(invitee)){
           // Invitee doesn't exist
           message.put("success", "false");
           message.put("reason", "input user doesn't exist");

           System.out.println("Target user doesn't exist");
           return message.toString();
       }

       UserProfile player1 = serverData.getProfile(inviter);
       UserProfile player2 = serverData.getProfile(invitee);
       Invite invite = new Invite(inviter, invitee);
       player1.getMailbox().addToSent(invite);
       player2.getMailbox().addToReceived(invite);
       message.put("success", "true");

       System.out.println("Invite has been sent");
       return message.toString();

   }

   private String getSentInvites(OCMessage receivedMessage){

        String user = receivedMessage.get("user");

        System.out.println("Attempting to recover sent invites from " + user);

        OCMessage message = new OCMessage();

        if (!serverData.profileExists(user)){
            // target user doesn't exist
            message.put("success", "false");
            message.put("reason", "target user doesn't exist");

            System.out.println("Target user doesn't exist");
            return message.toString();
        }

        UserProfile profile = serverData.getProfile(user);
        ArrayList<Invite> sent = profile.getMailbox().getSent();
        message.put("success", "true");
        message.put("amount", sent.size()+"");
        int count = 0;
        for (Invite invite : sent) {
            OCMessage in = new OCMessage();
            in.fromString(invite.toString());
            message.put("object" + count, in.get("object"));
            message.put("inviter" + count, in.get("inviter"));
            message.put("invitee" + count, in.get("invitee"));
            message.put("accepted" + count, in.get("accepted"));
            message.put("declined" + count, in.get("declined"));
            count++;
        }

       message.put("totalCount", String.valueOf(count));
       message.put("maxNicknameLength", String.valueOf(serverData.getLongestNickname()));

       System.out.println("Recovered sent invites!");
        return message.toString();
   }

   private String getReceivedInvites(OCMessage receivedMessage){

        String user = receivedMessage.get("user");

        System.out.println("Attempting to recover received invites from " + user);

        OCMessage message = new OCMessage();

        if (!serverData.profileExists(user)){
            // target user doesn't exist
            message.put("success", "false");
            message.put("reason", "target user doesn't exist");

            System.out.println("Target user doesn't exist");
            return message.toString();
        }

        UserProfile profile = serverData.getProfile(user);
        ArrayList<Invite> received = profile.getMailbox().getReceived();
        message.put("success", "true");
        message.put("amount", received.size()+"");
        int count = 0;
        for (Invite invite : received) {
            OCMessage in = new OCMessage();
            in.fromString(invite.toString());
            message.put("object" + count, "invite");
            message.put("inviter" + count, in.get("inviter"));
            message.put("invitee" + count, in.get("invitee"));
            message.put("accepted" + count, in.get("accepted"));
            message.put("declined" + count, in.get("declined"));
            count++;
        }
        message.put("totalCount", String.valueOf(count));
        message.put("maxNicknameLength", String.valueOf(serverData.getLongestNickname()));

       System.out.println("Recovered received invites!");
        return message.toString();
   }

   private String getNotifications(OCMessage receivedMessage) {
       String user = receivedMessage.get("nickname");

       OCMessage message = new OCMessage();

       if (!serverData.profileExists(user)){
           // target user doesn't exist
           message.put("success", "false");
           message.put("reason", "target user doesn't exist");

           System.out.println("Target user doesn't exist");
           return message.toString();
       }

       ArrayList<Notification> notifications = serverData.getProfile(user).getMailbox().getNotifications();

       message.put("success", "true");

       message.put("count", "" + notifications.size());

       for (int i = 0; i < notifications.size(); i++) {
           message.put("event" + (i + 1), notifications.get(i).getEvent());
           message.put("message" + (i + 1), notifications.get(i).getMessage());
           message.put("datestring" + (i + 1), notifications.get(i).getDateString());
       }
       
       return message.toString();
   }

    private String inviteResponse(OCMessage receivedMessage){
        String response = receivedMessage.get("response"),
                inviter = receivedMessage.get("inviter"),
                invitee = receivedMessage.get("invitee");
        OCMessage message = new OCMessage();
        if (response.equals("accept")) {
            for (UserProfile profile : serverData.getProfiles()){
                Mailbox mail = profile.getMailbox();
                for (Invite invite : mail.getSent()){
                    if (invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)){
                        invite.Accept();
                        mail.removeFromSent(invite);
                        serverData.getProfile(invitee).getMailbox().removeFromReceived(invite);
                        Match match = invite.makeMatch();
                        serverData.addMatch(match);
                        mail.addNotification("Invite accepted", invitee + " accepted your invite request.");
                    }
                }
            }
        }else if (response.equals("decline")) {
            for (UserProfile profile : serverData.getProfiles()){
                Mailbox mail = profile.getMailbox();
                for (Invite invite : mail.getSent()){
                    if (invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)){
                        invite.Decline();
                        mail.removeFromSent(invite);
                        serverData.getProfile(invitee).getMailbox().removeFromReceived(invite);
                        mail.addNotification("Invite declined", invitee + " declined your invite request.");
                    }
                }
            }
        }
        message.put("success", "true");
        return message.toString();
    }
}
