# Supported Server Requests

## Square (test request)
- This request is intended to be used for testing purposes, usually to verify that communication has been established.

Message Template:
* "process": "square"
* "number": "(integer to square)"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Register
- This request registers a new user using their nickname, password and email.

Message Template:
* "process": "register"
* "email": "example@gmail.com"
* "nickname": "examplenick"
* "password": "examplepass"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Unregister
- This request unregisters a user using their nickname.

Message Template:
* "process": "unregister"
* "nickname": "examplenick"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Login
- This request logs in a user using their nickname and password.

Message Template:
* "process": "login"
* "nickname": "examplenick"
* "password": "examplepass"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Get Profile Data
- This request returns a user's profile data.

Message Template:
* "process": "get profile data"
* "nickname": "examplenick"

Return Message Template 1:
* "success": "true"
* "nickname": "examplenick"
* "gamesWon": "#"
* "gamesLost": "#"
* "gamesTied": "#"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Send Invite
- This request sends an invitation to a user.

Message Template:
* "process": "invite"
* "invitee": "examplenick1"
* "inviter": "examplenick2"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Get Sent Invites
- This request returns a user's sent invitations.

Message Template:
+ "process": "invites sent"
+ "user": "nickname"

Return Message Template Success:
+ "success": "true"
+ "amount": "#"
> for each invite:
+ "object#": "invite"
+ "inviter#": "inviterName"
+ "invitee#": "inviteeName"
+ "accepted#": "false"
+ "declined#": "false"

Return Message Template Failure:
+ "success": "false"
+ "reason": "(reason for failure)"


## Get Received Invites
- This request returns a user's received invitations.

Message Template:
+ "process": "invites received"
+ "user": "nickname"

Return Message Template Success:
+ "success": "true"
+ "amount": "#"
> for each invite:
+ "object#": "invite"
+ "inviter#": "inviterName"
+ "invitee#": "inviteeName"
+ "accepted#": "false"
+ "declined#": "false"

Return Message Template Failure:
+ "success": "false"
+ "reason": "(reason for failure)"

## Get Notifications
- This request returns a user's notifications.

Message Template:
* "process": "get notifications"
* "nickname": "examplenick"

Return Message Template 1:
* "success": "true"
* "count": "#"
> for (count):
* "event#": "eventString"
* "message#": "eventMessage"
* "datestring#": "dateString"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Invite Response
- This request accepts/declines an invitation

Message Template:
* "process": "invite response"
* "response": "accept/decline"
* "inviter": "nickname1"
* "invitee": "nickname2"

Return Message Template:
* "success": "true"

## Get Board Data
- This request returns the data for what pieces are
 in what spaces on the board for the respective match ID.

Message Template:
* "process": "get board data" 
* "ID": "match ID"

Return Message Success:
* "success": "true"
* "w1": "piece.toString() | null"
* "y1": "InvalidSpace.toString()"
* "y2": "InvalidSpace.toString()"
>...
* "y9": "InvalidSpace.toString()"
* "y10": "InvalidSpace.toString()"
* "w4": "piece.toString() | null"

Return Message Failure:
* "success": "false"
* "reason": "reason for failure"
