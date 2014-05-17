package dhbw.karlsruhe.dsm.cucumber;

import com.badlogic.gdx.Gdx;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.InstructionsScreen;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class AnleitungLesenCucumberSteps {
	private DSM dsm;

	@Given("the main menu is loaded$")
	public void mainMenuCompletelyLoaded() {
		setUp();
	}
	
	@When("I click on \"Show Instructions\"$")
	public void clickOnShowInstructions() {
		int x = 102, y = 300;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(100);
	}
	
	@Then("the instructions screen will be shown$")
	public void showInstructionsScreen() {
		assertTrue(InstructionsScreen.class == dsm.getScreen().getClass());
	}
	
	
	@Given("the output is completely loaded$")
	public void instructionsScreenLoaded() {
		setUp();
		clickOnBack();
	}

	@When("I click \"Back\"$")
	public void clickOnBack() {
		int x = 765, y = 16;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}

	@Then("I get back to the main menu$")
	public void returnToMainMenuScreen() {
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
		dsm.resize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
	}
}
