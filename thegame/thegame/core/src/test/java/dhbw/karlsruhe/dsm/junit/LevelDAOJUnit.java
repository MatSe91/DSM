package dhbw.karlsruhe.dsm.junit;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.LevelDAO;

public class LevelDAOJUnit {

	
	private static String levelName = "aslkdjlii4989m";
	private int index = 25;
	private Level level = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + levelName);
		if(handle.exists())
			handle.delete();
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
		testLoad();
	}

	private void testLoad() {
		final CountDownLatch latch = new CountDownLatch(1);
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				level = LevelDAO.getLevelFromFile(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + levelName);
				latch.countDown();
			}
		};
		Gdx.app.postRunnable(runner);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals("", levelName, level.getName());
		assertEquals("", index, level.getIndex());
		assertEquals("", 150f, level.speed, .1f);
	}
	
	private void testSave() {
		final Level level = new Level(levelName, index);
		level.speed = 150f;
		
    	final CountDownLatch latch = new CountDownLatch(1);
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				LevelDAO.saveLevel(level);
				latch.countDown();
			}
		};
		Gdx.app.postRunnable(runner);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
