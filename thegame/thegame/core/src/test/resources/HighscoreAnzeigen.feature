Feature: Show highscores
In order to know if I am the best
As an actor
I Want to see the highscores

Scenario: Show the level selection screen for showing highscores
Given the main menu is completely loaded
When I click on "Show Highscores"
Then the level selection screen will be shown to me

Scenario: Show the highscores of a certain level
Given the level overview is completely loaded
When I click on a specific level
Then the highscores of this level will be shown

Scenario: Back to the level overview
Given the highscores are completely loaded
When I click on the "Back" button
Then I'll get back to the level overview

@lastScenario
Scenario: Back to main menu
Given the highscores are completely loaded
When I click on the "Back" button
And I click on the "Back" button
Then I'll get back to the main menu