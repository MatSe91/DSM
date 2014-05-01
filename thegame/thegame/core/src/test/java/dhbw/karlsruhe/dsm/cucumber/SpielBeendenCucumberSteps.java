package dhbw.karlsruhe.dsm.cucumber;

import static org.junit.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.InputEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.ExitGameScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class SpielBeendenCucumberSteps {

	private DSM dsm;
	private LwjglApplication application;

	
	@Given("the exit screen is completely loaded$")
	public void the_exit_screen_is_completely_loaded() throws Throwable {
		setUp();
	}

	@When("I click on the exit button$")
	public void I_click_on_the_exit_button() throws Throwable {
		// cursor position to trigger mouse click
		int x = 590, y = 305;
		application.getInput().setCursorPosition(x, y);

		// click!
		Robot bot;
		bot = new Robot();
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		// make sure the input can be processed
		TestHelper.wait(200);
	}

	@Then("the application gets closed.$")
	public void the_application_gets_closed() throws Throwable {
		assertTrue(TestHelper.exitStatus); // the previous step will verify that the application is actually closed.
	}
	
	@After("@closegame")
	public static void restoreSecuritymanager() {
		System.setSecurityManager(null);
	}

	
	
	
	
	
	
	
	private void setUp() {
		TestHelper.exitStatus = false;
		System.setSecurityManager(new TestHelper.NoExitSecurityManager());
		// create Application
		application = new LwjglApplication(new DSM(), TestHelper.createTestConfig());
		// get Application listener for manipulation
		dsm = (DSM) Gdx.app.getApplicationListener();
		// Wait until everything is set up (openGL Context and native resources)
		TestHelper.wait(600);
		// load exit game Screen
		dsm.setScreen(new ExitGameScreen(dsm, dsm.getScreen()));
	}
	

    
}
