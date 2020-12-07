# Sprint 4 - P3 - *Team Runtime Terrors*

## Goal
Complete all must-have user stories.

### Sprint Leader: *Daniel Stephenson*
## Plan

Epics planned for this release.

* Result Alert (#29)
* Viewer for Notifications (#27)
* GameBoard move functions (#281)
* Surrounding graphics (#75)
* View history from previous games (#22)
* Server Requests - Match/Board (#249)
* Select an in-progress game (#17)
* Screens (#32)


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *8* | *7* |
| Tasks |  *18*   | *17* |
| Story Points |  *23*  | *22* |


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *11/16/20* | *N/A* | *#44, #52, #62,  #65, #332, #334* | *none* |
| *11/17/20* | *#44, #47, #333* | *#52, #62, #65, #103, #104, #332, #334, #347* | *none* |
| *11/20/20* | *#52* | *#62, #65, #103, #104, #332, #334, #347* | *Running into an error where windows won't read a unicode string properly where it works in linux.* |
| *11/30/20* | *#62, #334* | *#65, #103, #104, #332, #347, #359* | *Figuring out crash on local servers, learning that it works on the deployed server* |
| *12/2/20* | *#43, #295, #361* | *#65, #103, #104, #332, #347, #359* | *none* |
| *12/4/20* | *#85, #103, #104, #240, #347, #359* | *#65, #332* | *none* |

## Review

#### Completed user stories
* #6 "As a user, I can participate in multiple games at the same time."
* #7 "As a user, I can forfeit from any game at any time."
* #9 "As a user, I can view history statistics from previous games."
* #11 "As a user, I can make the first move if I create the match."
* #12 "As a user, I can return to a saved game."
* #13 "As a user, I can see the end result of a game I participate in." 
* #16 "As a user, I will be able to quit the game using a Quit button."
* #18 "As a user, when I choose to move a piece the board will show where I can move the piece."

#### Completed epics in Sprint Backlog 

* Viewer for Notifications (#27)
* GameBoard move functions (#281)
* Surrounding graphics (#75)
* View history from previous games (#22)
* Server Requests - Match/Board (#249)
* Select an in-progress game (#17)
* Screens (#32)

#### Incomplete epics in Sprint Backlog 
* Result Alert (#29)

#### What went well
* Most everything got completed.
* Communication was good.

#### Problems encountered and resolutions
* We ran into a memory leak issue with the match screen which was solved by switching the libGDX screen for a java swing screen.
* We ran into a problem with the En Passant move where the captured piece would not be removed, creating a black hole square.
* There were some issues when trying to implement pawn promotion in which the selection of replacement options was not functioning, despite the presentation of replacement options showing up.

## Retrospective

#### What went well
* We got almost everything completed, including a majority of epics and mega epics.
* We had good communication between team members, especially related to problems that we ran into.

#### Potential improvements
* Figuring out the En Passant and Pawn Promotion features.
* Learning more about LibGDX.

#### What we will change next time
* Picking a different graphical library to use, maybe Java Swing.
* Figuring out how to not have memory leaks with LibGDX.
