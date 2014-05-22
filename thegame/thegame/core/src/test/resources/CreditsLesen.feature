Feature: Show credits
In order to read the credits of the game
As an actor
I Want to read the credits

Scenario: Show the credit screen
Given the main menu is loaded -credits
When I click on "Show Credits"
Then the credit screen will be shown

Scenario: Back to main menu from credits
Given the output is completely loaded -credits
When I click "Back" -credits
Then I get back to the main menu -credits