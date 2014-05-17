Feature: Show instructions
In order to know how to play the game
As an actor
I Want to read the instructions

Scenario: Show the instructions screen
Given the main menu is loaded
When I click on "Show Instructions"
Then the instructions screen will be shown

@lastScenario
Scenario: Back to main menu
Given the output is completely loaded
When I click "Back"
Then I get back to the main menu