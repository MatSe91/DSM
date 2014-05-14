package dhbw.karlsruhe.dsm.cucumber;

import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.Gdx;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.HighscoreScreen;
import dhbw.karlsruhe.dsm.core.screens.LevelSelectionScreen;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class HighscoreAnzeigenCucumberSteps {
	private DSM dsm;

	@Given("the main menu is completely loaded$")
	public void givenMainMenuCompletelyLoaded() {
		setUp();
	}
	
	@When("I click on \"Show Highscores\"$")
	public void whenClickOnShowHighscores() {
		int x = 102, y = 350;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
	
	@Then("the level selection screen will be shown to me$")
	public void thenShowLevelSelectionScreen() {
		assertTrue(LevelSelectionScreen.class == dsm.getScreen().getClass());
	}
	
	@Given("the level overview is completely loaded$")
	public void givenLevelSelectionScreenIsLoaded() {
		// load main menu Screen
		setUp();
		whenClickOnShowHighscores();
	}
	
	@When("I click on a specific level$")
	public void whenClickOnSpecificLevel() {
		int x = 128, y = 298;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
	
	@Then("the highscores of this level will be shown$") 
	public void thenHighscoresShown() {
		assertTrue(HighscoreScreen.class == dsm.getScreen().getClass());
	}
	
	@Given("the highscores are completely loaded$")
	public void givenHighscoreScreenLoaded() {
		setUp();
		whenClickOnShowHighscores();
		whenClickOnSpecificLevel();
	}
	
	@When("I click on the \"Back\" button$")
	public void whenClickOnBackButton() {
		int x = 765, y = 16;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
	
	@Then("I'll get back to the level overview$")
	public void thenReturnToLevelSelectionScreen() {
		assertTrue(LevelSelectionScreen.class == dsm.getScreen().getClass());
	}
	
	@Then("I'll get back to the main menu$")
	public void thenReturnToMainMenuScreen() {
		assertTrue(MainMenuScreen.class == dsm.getScreen().getClass());
	}
	
	
	private void setUp() {
		// create Application
		Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run() {
				Gdx.graphics.setDisplayMode(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT, false);
				dsm.setScreen(new MainMenuScreen(dsm));
			}
		});
		// get Application listener for manipulation
		dsm = (DSM) Gdx.app.getApplicationListener();
		// Wait until everything is set up (openGL Context and native resources)
		TestHelper.wait(800);
		dsm.resize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		TestHelper.wait(400);
	}
	
}
