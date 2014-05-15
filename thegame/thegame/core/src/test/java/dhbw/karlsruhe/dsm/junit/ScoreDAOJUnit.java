package dhbw.karlsruhe.dsm.junit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.Score;
import dhbw.karlsruhe.dsm.core.level.ScoreDAO;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class ScoreDAOJUnit {

	private final static String levelName = "asldkjaslkd";
	
	@Before
	public void setUp() throws Exception {
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_SCORE_DIRECTORY + "/" + levelName);
		if(handle.exists())
			handle.delete();
	}

	@Test
	public void testSaveAndLoad() {
		testSave();
		TestHelper.wait(1500);
		testLoad();
	}

	private void testLoad() {
		final int EXPECTED_LIST_SIZE = 10;
		List<Score> testList = ScoreDAO.loadScores(new Level(levelName, 0));
		assertNotNull("List is empty", testList);
		assertEquals("List size doesn't match", EXPECTED_LIST_SIZE, testList.size());
		for (int i = 0; i < EXPECTED_LIST_SIZE; i++) {
			assertEquals("Score doesn't match at index " + i, 9-i, testList.get(i).getScore());
		}
	}

	private void testSave() {
		List<Score> scoreList = new ArrayList<Score>();
		scoreList.add(new Score("Player 1", 7));
		scoreList.add(new Score("Player 2", 6));
		scoreList.add(new Score("Player 4", 1));
		scoreList.add(new Score("Player 3", 0));
		scoreList.add(new Score("Player 5", 8));
		scoreList.add(new Score("Player 6", 5));
		scoreList.add(new Score("Player 7", 2));
		scoreList.add(new Score("Player 8", 9));
		scoreList.add(new Score("Player 7", 3));
		scoreList.add(new Score("Player 8", 4));
		ScoreDAO.saveScores(scoreList, new Level(levelName, 0));
	}
	
}
