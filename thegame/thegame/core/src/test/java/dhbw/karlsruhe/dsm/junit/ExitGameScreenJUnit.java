package dhbw.karlsruhe.dsm.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.ExitGameScreen;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class ExitGameScreenJUnit {

	private static DSM dsm;
	
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExitGameScreen() throws Exception {
		dsm = (DSM) Gdx.app.getApplicationListener();
		final ExitGameScreen exitScreen;
		TestHelper.wait(600); // necessary due to multi threading...
		final Screen previous = dsm.getScreen();
		Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run() {
				dsm.setScreen(new ExitGameScreen(dsm, previous));
			}
		});
		TestHelper.wait(100);
		exitScreen = (ExitGameScreen) dsm.getScreen();
		assert(exitScreen.getClass() == ExitGameScreen.class);
		
		Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run() {
				exitScreen.pause();
				exitScreen.dispose();
				previous.dispose();
			}
		});
		
		
		
		TestHelper.wait(1000);
	}
	
}
