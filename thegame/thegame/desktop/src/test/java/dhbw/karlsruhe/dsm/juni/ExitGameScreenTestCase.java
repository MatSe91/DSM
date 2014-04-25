package dhbw.karlsruhe.dsm.juni;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.ExitGameScreen;
import dhbw.karlsruhe.dsm.java.DSMTheGameDesktop;

public class ExitGameScreenTestCase {

	private DSM dsm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DSM(), config);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Gdx.app.exit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExitGameScreen() {
		dsm = (DSM) Gdx.app.getApplicationListener();
		ExitGameScreen exitScreen;
		Screen menuScreen = dsm.getScreen();
		waitASecond(); // necessary due to multi threading...
		dsm.setScreen(new ExitGameScreen(dsm, dsm.getScreen()));
		exitScreen = (ExitGameScreen) dsm.getScreen();
		assert(exitScreen.getClass() == ExitGameScreen.class);
	}

	private void waitASecond() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
