### 1) As a user, I can register for the platform by using my email address and set up my profile (nickname, password) for quicker access to the application. (must have)
- #19 “Create a User Profile class.”
- #84 “Add a list of users that a user has played with to the User Profile Class.”
- #21 “Create RegistrationSystem.java and provide user profile creation methods.”
- #20 "Create a list of User Profile objects.”
- #99 Register/Login Screen
### 2) As a user, I can create a new match. (must have)
- #37 “Match class”
### 3) As a user, I can invite another user to join the match. (should have)
- #54 “Pull sender info from invite”
- #66 "Create invitation class on server side”
- #64 “Add search functionality to server side”
- #61 “Add search bar to client side”
- #101 “Invite Screen”
### 4) As a user, I can send more than one invitation. (could have)
- (N/A)
### 5) As a user, I can accept or reject an invitation, alerting the inviter. (should have)
- #87 “Invitation Card”
- #80 “Accept and decline buttons for invitation”
- #53 “Accepting an invitation that you have received”
- #58 “Declining an invitation you have received”
### 6) As a user, I can participate in multiple games at the same time. (should have)
- #42 “Method to acquire a list of in progress games for a user”
- #82 “Visual representation of the list”
### 7) As a user, I can quit from any game at any time. (could have)
- #39 “Button to end a match in game”
### 8) As a user, I can unregister. (must have)
- #33 “Method to remove a registered user”
### 9) As a user, I can view history statistics from previous games. (would have)
- #48 “Create previous game record class on server side”
- #171 ”List of Previous Moves”
- #45 “Show users a list of previous games and whether they won or lost the match”
- #44 “'History' button”
- #103”Archives Screen”
### 10) As a user, I can view a user profile if registered. (would have)
- #86 “Profile Card”
- #35 “Graphical card that displays user data”
- #34 “Button for each user profile”
### 11) As a user, I can make the first move if I create the match. (could have)
- #57 “Set sender as player 1”
### 12) As a user, I can return to a saved game. (should have)
- (N/A)

### 13) As a user, I can see the end result of a game I participate in. (would have)
- #38 “Method to end match”
- #68 “Card that lists who won, how many moves”
- #104 “Result Screen”
### 14) As a user, I will be alerted when a user accepts/declines an invitation, when it is my turn, or a game I am participating in is over. (could have)
- #65 “Alert users of finished games if they are not in the match.”
- #47 “Notification of Acceptance/Declining”
- #40 “Create a list of incoming invites per user”
- #60 “Create a list of outgoing invitations per user”
- #83 “Visual representation of outgoing invites”
- #46 “Create Mailbox.java”
- #49 "Add a mailbox object to the user profile class so that every user has a mailbox.”
- #50 “Add methods for adding/removing notifications.”
- #52 “Show users a list of their notifications when they click on the 'Mailbox' button.”
- #56 "Create a method for sending a user an in-game popup.”
- #88 “Notification Alert”
- #55 "When a new notification is added to a player's mailbox, send them a pop-up if they are in game.”
- #51 “'Mailbox' button”
- #102 “Mailbox Screen”
### 15) As a user, I want to have a lobby which shows me the actions I can perform. (should have)
- #90 “Main Menu Button”
- #98 “Main Menu”
### 16) As a user, I will be able to quit the game using a Quit button. (could have)
- #62 “Forfeit button”
### 17) As a user, I will be able to view a chess board and pieces when I’m in a match. (must have)
- #129 “Game board class (logic)”
- #166 “Create arraylists for black and white pieces”
- #97 “Create a Board class”
- #95 “Create a BoardSquare class that is capable of holding a piece and being highlighted”
### 18) As a user, when I choose to move a piece the board will show where I can move the piece. (must have)
- #113 “Wizard class”
- #109 “Abstract Piece Class”
- #111 “King Class”
- #110 “Queen Class”
- #105 “Pawn Class”
- #106 “Rook Class”
- #107 “Knight Class”
- #108 “Bishop Class”
- #131 “Create a method to place a piece on a location on the board”
- #157 “Invalid Space”
- #160 “Implement En Passant Move Strategy”
- #163 “Implement is_king_in_check function in King Class”
- #162 “Implement Castling move strategy”
- #179 “Create a method to receive match move data on the server.”
- #193 "Initial pawn moves”
- #70 “Send match move to the server.”
- #71 “Create a method to receive match move data on the server.”
- #85 “Automatically update clients if they are open with a move”
- #92 “Create a method to place a certain piece onto a certain square on the board.”
- #93 “Create a method to highlight a certain square on the board.”
- #94 “Create a method to remove a piece from a certain square on the board.”
