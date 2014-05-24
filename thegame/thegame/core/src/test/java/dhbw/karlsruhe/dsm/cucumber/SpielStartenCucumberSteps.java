package dhbw.karlsruhe.dsm.cucumber;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.GameLevelSelectionScreen;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;
import dhbw.karlsruhe.dsm.core.screens.HighScoreLevelSelectionScreen;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class SpielStartenCucumberSteps {
	private DSM dsm;
	
	@Given("the main menu is completely loaded before I attempt to start a game$")
	public void givenMainMenuCompletelyLoaded() throws InterruptedException {
		setUp();
	}
	
	@When("I click on \"Start game\"$")
	public void whenClickOnStartGame() {
		int x = 102, y = 400;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
	
	@Given("the level selection screen is loaded$")
	public void givenLevelSelectionScreenIsLoaded() throws InterruptedException {
		// load main menu Screen
		setUp();
		whenClickOnStartGame();
	}
	
	@When("select a level of my choice, by clicking on it$")
	public void whenClickOnSpecificLevel() {
		int x = 128, y = 298;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(500);
	}
	
	@Then("the game starts this level$") 
	public void thenGameStartsLevel() {
		assertTrue(GameScreen.class == dsm.getScreen().getClass());
	}
	
	@Then("the start game level selection screen will be shown to me$")
	public void thenShowLevelSelectionScreen() {
		assertTrue(GameLevelSelectionScreen.class == dsm.getScreen().getClass());
	}
	
	@Then("I'll get back to the main menu.$")
	public void thenReturnToMainMenuScreen() {
		assertTrue(MainMenuScreen.class == dsm.getScreen().getClass());
	}
	
	@Given("the game is running$")
	public void givenGameIsRunning() throws InterruptedException {
		setUp();
		whenClickOnStartGame();
		whenClickOnSpecificLevel();
	}
	
	@When("I press \"ESC\"$") 
	public void whenPressEsc() {
		int x = 128, y = 298;
		TestHelper.clickOnLocation(x, y);
		TestHelper.pressButton(KeyEvent.VK_ESCAPE);
		TestHelper.wait(400);
	}
	
	
	
	private void setUp() throws InterruptedException {
		// create Application
		TestHelper.setToMainMenuScreen();
		// get Application listener for manipulation
		dsm = (DSM) Gdx.app.getApplicationListener();
		// Wait until everything is set up (openGL Context and native resources)
	}
}
