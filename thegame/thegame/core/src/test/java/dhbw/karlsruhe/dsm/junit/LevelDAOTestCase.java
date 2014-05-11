package dhbw.karlsruhe.dsm.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.LevelDAO;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class LevelDAOTestCase {

	private static LwjglApplication app;
	
	private static String levelName = "aslkdjlii4989m";
	private int index = 25;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = new LwjglApplication(new DSM(), TestHelper.createTestConfig());
		TestHelper.wait(1000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + levelName);
		if(handle.exists())
			handle.delete();
		app.exit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveAndLoad() {
		testSave();
		TestHelper.wait(1500);
		testLoad();
	}

	private void testLoad() {
		Level level = LevelDAO.getLevelFromFile(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + levelName);
		assertEquals("", levelName, level.getName());
		assertEquals("", index, level.getIndex());
	}

	private void testSave() {
		Level level = new Level(levelName, index);
		LevelDAO.saveLevel(level);
	}

}
