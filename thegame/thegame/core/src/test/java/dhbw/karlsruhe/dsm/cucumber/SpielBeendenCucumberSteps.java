package dhbw.karlsruhe.dsm.cucumber;

import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.Gdx;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class SpielBeendenCucumberSteps {

	private DSM dsm;

	
	@Given("the exit screen is completely loaded$")
	public void the_exit_screen_is_completely_loaded() throws Throwable {
		setUp();
	}

	@When("I click on the exit button$")
	public void I_click_on_the_exit_button() throws Throwable {
		// cursor position to trigger mouse click
		int x = 590, y = 305;
		TestHelper.clickOnLocation(x, y);
		
		// make sure the input can be processed
		TestHelper.wait(200);
	}
	
	@When("I click on the return button$")
	public void I_click_on_the_return_button() throws Throwable {
		// cursor position to trigger mouse click
		int x = 220, y = 305;
		TestHelper.clickOnLocation(x, y);
		
		// make sure the input can be processed
		TestHelper.wait(200);
	}
	
	@Then("the application moves back to the previous screen.$")
	public void the_application_moves_back_to_the_previous_screen() throws Throwable {
		assertTrue(dsm.getScreen().getClass() == MainMenuScreen.class);
	}

	@Then("the application gets closed.$")
	public void the_application_gets_closed() throws Throwable {
		assertTrue(TestHelper.exitStatus); // the previous step will verify that the application is actually closed.
	}
	
	@After("@closegame")
	public static void restoreSecuritymanager() {
		System.setSecurityManager(null);
	}
	
	@After("@abortclosegame")
	public static void afterAbortScenario() {
		System.setSecurityManager(null);
		TestHelper.wait(100);
	}


	private void setUp() throws InterruptedException {
		TestHelper.exitStatus = false;
		System.setSecurityManager(new TestHelper.NoExitSecurityManager());
		// create Application
		// get Application listener for manipulation
		dsm = (DSM) Gdx.app.getApplicationListener();
		// Wait until everything is set up (openGL Context and native resources)
		// load exit game Screen
		TestHelper.setToMainMenuScreen();
		int x = 75, y = 200;
		TestHelper.clickOnLocation(x, y);
		TestHelper.wait(200);
	}
 
}
