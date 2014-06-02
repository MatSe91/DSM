Feature: Start Game

Scenario: Start a Level
Given the main menu is completely loaded before I attempt to start a game
When I click on "Start game"
And select a level of my choice, by clicking on it
Then the game starts this level

Scenario: Pause the game
Given the game is running
When I press "ESC"
Then the game pauses
And the pause screen will be shown to me

Scenario: Pause the game
Given the game is running
When I press "P"
Then the game pauses
And the pause screen will be shown to me

Scenario: Return to level selection
Given the game is pausing
When I click on "Close Game"
Then the start game level selection screen will be shown to me

Scenario: Return to main menu
Given the level selection screen is loaded
When I click on the "Back" button
Then I'll get back to the main menu.
