package dhbw.karlsruhe.dsm.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.lang.System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.ExitGameScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class ExitGameScreenTestCase {

	private static DSM dsm;
	private static LwjglApplication app;
	
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		app = new LwjglApplication(new DSM(), TestHelper.createTestConfig());
	}

	@After
	public void tearDown() throws Exception {
		app.exit();
	}

	@Test
	public void testExitGameScreen() throws Exception {
		dsm = (DSM) Gdx.app.getApplicationListener();
		ExitGameScreen exitScreen;
		TestHelper.wait(600); // necessary due to multi threading...
		Screen previous = dsm.getScreen();
		dsm.setScreen(new ExitGameScreen(dsm, previous));
		exitScreen = (ExitGameScreen) dsm.getScreen();
		assert(exitScreen.getClass() == ExitGameScreen.class);
		
		exitScreen.pause();
		exitScreen.dispose();
		previous.dispose();
		
		
		TestHelper.wait(1000);
	}
	
}
