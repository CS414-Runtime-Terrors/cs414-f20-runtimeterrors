package com.omegaChess.server;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.LegalMoves;

import java.util.ArrayList;

// this class is responsible for actually processing any input from a client
public class OCProtocol {

    private final OCServerData serverData;
    private OCMessage message;

    public OCProtocol(OCServerData data) {
        serverData = data;
        message = new OCMessage();
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
                    toReturn = inviteResponse(receivedMessage);
                    break;
                case "get legal moves":
                    toReturn = getLegalMoves(receivedMessage);
                    break;
                case "get board data":
                    toReturn = getBoardData(receivedMessage);
                    break;
                case "match move":
                    toReturn = matchMove(receivedMessage);
                    break;
                case "get in-progress matches":
                    toReturn = resumeMatchesListResponse(receivedMessage);
                    break;
                case "get turn":
                    toReturn = getTurn(receivedMessage);
                    break;
                case "end match":
                    toReturn = endMatch(receivedMessage);
                    break;
                default:
                    message = new OCMessage();
                    message.put("success", "false");
                    message.put("reason", "process not recognized");

                    toReturn = message.toString();
                    break;
            }
        } catch (Exception e) {
            message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Something went wrong when processing input.");

            toReturn = message.toString();

            e.printStackTrace();
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
            message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Wrong input!");

            return message.toString();
        }

        int square = number * number;
        System.out.println("Square: " + square);


        message = new OCMessage();
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

        message = new OCMessage();
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
                serverData.removeMatch(match);
                serverData.getProfile(match.getProfile2()).getMailbox().addNotification(Notification.NotificationType.MATCH_ENDED,
                        "Other user deleted their account before the game ended.");
            }
            if (match.getProfile2().equalsIgnoreCase(nickname)){
                match.endMatch("[deleted]", match.getProfile1(), match.getBoard().getMoves().size());
                serverData.removeMatch(match);
                serverData.getProfile(match.getProfile1()).getMailbox().addNotification(Notification.NotificationType.MATCH_ENDED,
                        "Other user deleted their account before the game ended.");
            }
            if (serverData.getMatches().size() == 0)
                break;
        }
        for (UserProfile player : serverData.getProfiles()){
            Mailbox mail = player.getMailbox();
            for (Invite invite : mail.getReceived()){
                if (invite.getInviter().equalsIgnoreCase(nickname)) {
                    invite.Decline();
                    mail.removeFromReceived(invite);
                    mail.addNotification(Notification.NotificationType.INVITE_CANCELLED,
                            "Other user deleted their account before a response was made.");
                }
                if (mail.getReceived().size() == 0)
                    break;
            }
            for (Invite invite: mail.getSent()){
                if (invite.getInvitee().equalsIgnoreCase(nickname)) {
                    invite.Decline();
                    mail.removeFromSent(invite);
                    mail.addNotification(Notification.NotificationType.DECLINED_INVITE,
                            "Other user deleted their account before responding.");
                }
                if (mail.getSent().size() == 0)
                    break;
            }
        }


        message = new OCMessage();
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

        message = new OCMessage();

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

        message = new OCMessage();

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

       message = new OCMessage();

        if (invitee.equalsIgnoreCase(inviter)){
            // Can't send invite to yourself
            message.put("success", "false");
            message.put("reason", "Can't send an invite to yourself");

            System.out.println(message.get("reason"));
            return message.toString();
        }

       System.out.println("Attempting to send invite from " + inviter + " to " + invitee);

       if (!serverData.profileExists(invitee)){
           // Invitee doesn't exist
           message.put("success", "false");
           message.put("reason", "input user doesn't exist");

           System.out.println("Target user doesn't exist");
           return message.toString();
       }

       UserProfile player1 = serverData.getProfile(inviter);
       UserProfile player2 = serverData.getProfile(invitee);
       if (lookForMatch(inviter, invitee) != null){
           message.put("success", "false");
           message.put("reason", "Already in a match with " + invitee);
           System.out.println(message.get("reason"));
           return message.toString();
       }
       if (lookForInvite(inviter, invitee, player1.getMailbox(), true) != null){
           message.put("success", "false");
           message.put("reason", "Already sent an invite to " + invitee);
           System.out.println(message.get("reason"));
           return message.toString();   // return if invite was already sent
       }else if (lookForInvite(inviter, invitee, player1.getMailbox(), false) != null){
           message.put("success", "false");
           message.put("reason", "Already have an invite from " + inviter);
           System.out.println(message.get("reason"));
           return message.toString();   // return if invite was already sent
       }
       Invite invite = new Invite(inviter, invitee);
       player1.getMailbox().addToSent(invite);
       player2.getMailbox().addToReceived(invite);
       player2.getMailbox().addNotification(Notification.NotificationType.INVITE_REQUEST,
               "You have been invited to play OmegaChess by user: " + inviter +
               ". Go to your mailbox to accept/decline this invite.");
       message.put("success", "true");

       System.out.println("Invite has been sent");
       return message.toString();

   }

   private String getSentInvites(OCMessage receivedMessage){

        String user = receivedMessage.get("user");

        System.out.println("Attempting to recover sent invites from " + user);

        message = new OCMessage();

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

        message = new OCMessage();

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

       message = new OCMessage();

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
           message.put("event" + (i + 1), notifications.get(i).getEvent().name());
           message.put("message" + (i + 1), notifications.get(i).getMessage());
           message.put("datestring" + (i + 1), notifications.get(i).getDateString());
       }
       
       return message.toString();
   }

    private String inviteResponse(OCMessage receivedMessage){
        String response = receivedMessage.get("response"),
                inviter = receivedMessage.get("inviter"),
                invitee = receivedMessage.get("invitee");
        message = new OCMessage();

        System.out.println("Attempting to " + response + " invite from " + inviter + " to " + invitee);

        if (response.equals("accept")) {
            for (UserProfile profile : serverData.getProfiles()){
                Mailbox mail = profile.getMailbox();
                for (Invite invite : mail.getSent()){
                    if (invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)){
                        Invite inviteF = lookForInvite(inviter, invitee, serverData.getProfile(invitee).getMailbox(), false);
                        invite.Accept();
                        mail.removeFromSent(invite);
                        serverData.getProfile(invitee).getMailbox().removeFromReceived(inviteF);
                        Match match = invite.makeMatch();
                        int matchID = match.getMatchID();
                        serverData.addMatch(match);
                        mail.addNotification(Notification.NotificationType.ACCEPTED_INVITE,
                                invitee + " accepted your invite request.");
                        message.put("success", "true");
                        message.put("matchID", Integer.toString(matchID));
                        return message.toString();
                    }
                }
            }
        }else if (response.equals("decline")) {
            for (UserProfile profile : serverData.getProfiles()){
                Mailbox mail = profile.getMailbox();
                for (Invite invite : mail.getSent()){
                    if (invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)){
                        Invite inviteF = lookForInvite(inviter, invitee, serverData.getProfile(invitee).getMailbox(), false);
                        invite.Decline();
                        mail.removeFromSent(invite);
                        serverData.getProfile(invitee).getMailbox().removeFromReceived(inviteF);
                        mail.addNotification(Notification.NotificationType.DECLINED_INVITE,
                                invitee + " declined your invite request.");
                        message.put("success", "true");
                        return message.toString();
                    }
                }
            }
        }
        return message.toString();
    }

    public String getBoardData(OCMessage receivedMessage){
        int ID = Integer.parseInt(receivedMessage.get("ID"));
        message = new OCMessage();

        System.out.println("Attempting to get board for match " + ID);

        Match match = null;
        if (serverData.getMatches().size() == 0){
            message.put("success", "false");
            message.put("reason", "There are no matches available");
        }
        for (Match mat : serverData.getMatches()){
            if (mat.getMatchID() == ID) {
                message.put("success", "true");
                match = mat;
                break;
            }
        }
        if (match == null) {
            message.put("success", "false");
            message.put("reason", "No match found that has ID=" + ID);
            return message.toString();
        }

        message.fromString(match.getBoard().boardString());

        return message.toString();
    }

    private String getLegalMoves(OCMessage receivedMessage) {
        int matchID = Integer.parseInt(receivedMessage.get("matchID"));
        int row = Integer.parseInt(receivedMessage.get("row"));
        int column = Integer.parseInt(receivedMessage.get("column"));
        message = new OCMessage();

        // get correct match and board

        Match match = serverData.getMatch(matchID);
        ChessBoard board = match.getBoard();

        // get piece at specified position on board
        String position = board.reverseParse(row, column);
        ChessPiece piece = null;
        try {
            piece = board.getPiece(position);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // get legal moves for that piece
        LegalMoves moves;
        if (piece == null) {
            message.put("success", "false");
            message.put("reason", "no piece at specified position");
            return message.toString();
        } else {
            moves = piece.getNormalOrCheckMoves();
        }
        String legalMoves = "/";
        for (String move : moves.getListOfMoves()) {
            legalMoves += move;
            legalMoves += "/";
        }
        message.put("success", "true");
        message.put("legal moves", legalMoves);
        if (moves.isEnPessant()) {
            message.put("enPessant", "true");
        }
        else {
            message.put("enPessant", "false");
        }
        System.out.println("Sending legal moves: " + legalMoves);

        return message.toString();
    }

    private String matchMove(OCMessage receivedMessage) {
        int matchID = Integer.parseInt(receivedMessage.get("matchID"));
        int[] fromArray = new int[2];
        int[] toArray = new int[2];
        fromArray[0] = Integer.parseInt(receivedMessage.get("fromRow"));
        fromArray[1] = Integer.parseInt(receivedMessage.get("fromColumn"));
        toArray[0] = Integer.parseInt(receivedMessage.get("toRow"));
        toArray[1] = Integer.parseInt(receivedMessage.get("toColumn"));
        message = new OCMessage();

        // get correct match and board
        Match match = serverData.getMatch(matchID);
        ChessBoard board = match.getBoard();
        String fromPosition = board.reverseParse(fromArray[0], fromArray[1]);
        String toPosition = board.reverseParse(toArray[0], toArray[1]);

        // make move
        boolean moveMade = false;
        try {
            board.move(fromPosition, toPosition);
            moveMade = true;
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        if (moveMade) {
            message.put("success", "true");
            System.out.println("Move was successful!");
        } else {
            message.put("success", "false");
            message.put("reason", "invalid move");
            System.out.println("Invalid move!");
        }
        return message.toString();
    }

    public String resumeMatchesListResponse(OCMessage receivedMessage) {
        String user = receivedMessage.get("nickname");
        String opponents = "";
        String IDs = "";
        String playerIndex = "";
        ArrayList<Match> matches = serverData.getMatches();
        message = new OCMessage();

        // This allows us to figure out if the user requesting the match to resume is the first or second player
        for (Match m : matches) {
            if (m.getProfile1().equals(user)) {
                playerIndex += 1 + ", ";
                opponents += m.getProfile2() + ", ";
                IDs += m.getMatchID() + ", ";
            }
            else if (m.getProfile2().equals(user)) {
                playerIndex += 2 + ", ";
                opponents += m.getProfile1() + ", ";
                IDs += m.getMatchID() + ", ";
            }
        }

        if (!opponents.equals("")) {
            opponents = opponents.substring(0, opponents.length() - 2);
            IDs = IDs.substring(0, IDs.length() - 2);
        }

        message.put("success", "true");
        message.put("opponents", opponents);
        message.put("matchIDs", IDs);
        message.put("playerIndex", playerIndex);
        return message.toString();
    }

    public String getTurn(OCMessage receivedMessage){
        int ID = Integer.valueOf(receivedMessage.get("ID"));
        message = new OCMessage();

        TurnTracker turn = null;
        if (serverData.getMatches().size() == 0){
            message.put("success", "false");
            message.put("reason", "There are no matches available");
        }
        for (Match match : serverData.getMatches()){
            if (match.getMatchID() == ID) {
                message.put("success", "true");
                turn = match.getBoard().getTurn();
                break;
            }
        }
        if (turn == null) {
            message.put("success", "false");
            message.put("reason", "No match found that has ID=" + ID);
            return message.toString();
        }

        message.put("user", turn.getCurrentTurnPlayer());

        return message.toString();
    }

    public String endMatch(OCMessage receivedMessage){
        int ID = Integer.valueOf(receivedMessage.get("ID")), moves = 0;
        String loser = receivedMessage.get("loser"), winner = receivedMessage.get("winner");
        message = new OCMessage();

        Match end = null;
        if (serverData.getMatches().size() == 0){
            message.put("success", "false");
            message.put("reason", "There are no matches available");
            return message.toString();
        }
        for (Match match : serverData.getMatches()){
            if (match.getMatchID() == ID) {
                message.put("success", "true");
                end = match;
                break;
            }
        }
        if (end == null){
            message.put("success", "false");
            message.put("reason", "there is no match with ID " + ID);
            return message.toString();
        }
        moves = end.getBoard().getMoves().size();
        end.endMatch(loser, winner, moves);
        serverData.removeMatch(end);
        message.put("ID", String.valueOf(serverData.getArchive().size()));

        return message.toString();
    }

    // Helper method to grab an invite between users
    public Invite lookForInvite(String inviter, String invitee, Mailbox mail, boolean sent){
        if (sent) {
            for (Invite invite : mail.getSent()) {
                if ((invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)) ||
                        (invite.getInviter().equalsIgnoreCase(invitee) && invite.getInvitee().equalsIgnoreCase(inviter)))
                    return invite;
            }
        }else {
            for (Invite invite : mail.getReceived()) {
                if ((invite.getInviter().equalsIgnoreCase(inviter) && invite.getInvitee().equalsIgnoreCase(invitee)) ||
                        (invite.getInviter().equalsIgnoreCase(invitee) && invite.getInvitee().equalsIgnoreCase(inviter)))
                    return invite;
            }
        }
        return null;
    }

    // Helper method to grab a match between users
    public Match lookForMatch(String player1, String player2){
        for (Match match : serverData.getMatches()){
            if ((match.getProfile1().equalsIgnoreCase(player1) && match.getProfile2().equalsIgnoreCase(player2)) ||
                    (match.getProfile1().equalsIgnoreCase(player2) && match.getProfile2().equalsIgnoreCase(player1)))
                return match;
        }
        return null;
    }
}
