package dhbw.karlsruhe.dsm.cucumber;

import com.badlogic.gdx.Gdx;

import static org.junit.Assert.assertTrue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.CreditScreen;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class CreditsLesenCucumberSteps {
	private DSM dsm;

	@Given("the main menu is loaded$")
	public void mainMenuCompletelyLoaded() throws InterruptedException {
		setUp();
	}
	
	@When("I click on \"Show Credits\"$")
	public void clickOnShowInstructions() {
		int x = 102, y = 250;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
	
	@Then("the credit screen will be shown$")
	public void showCreditScreen() {
		assertTrue(CreditScreen.class == dsm.getScreen().getClass());
	}
	
	
	@Given("the output is completely loaded$")
	public void creditScreenLoaded() throws InterruptedException {
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

	private void setUp() throws InterruptedException {
		// prepare Application
		TestHelper.setToMainMenuScreen();
		// get Application listener for manipulation
		dsm = (DSM) Gdx.app.getApplicationListener();
	}
}
