Feature: Close game
In order to leave the game
As an actor
I Want to close the application window

@closegame
Scenario: Close game
Given the exit screen is completely loaded
When I click on the exit button
Then the application gets closed.

@abortclosegame
Scenario: Return to previous menu
Given the exit screen is completely loaded
When I click on the return button
Then the application moves back to the previous screen.