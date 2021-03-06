package dhbw.karlsruhe.dsm.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.cucumber.CukeRunnerJUnit;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

@RunWith(Suite.class)
@SuiteClasses(
		{ ExitGameScreenJUnit.class, LevelDAOJUnit.class, ScreenHelperJUnit.class, ScoreDAOJUnit.class, CukeRunnerJUnit.class}
		)
public class TestSuite {

	private static LwjglApplication app;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = new LwjglApplication(new DSM(), TestHelper.createTestConfig());
		TestHelper.wait(1200);
		
		// wait is necessary in this case, no synchronization possible
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void dummy() {
		
	}

}
