package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;

public class LevelLoader {
	
	
	public static Level getLevelFromFile(String filename) {
		// TODO: replace
		
		return new Level("test", 0);
	}
	
	/*
	 * Returns a list of all available levels. 
	 * Sorted by their index.
	 */
	public static ArrayList<Level> loadLevels() {
		//ConfigurationConstants.PATH_LEVEL_DIRECTORY
		ArrayList<Level> levelList = new ArrayList<Level>();

		levelList.add(new Level("a", 0));
		levelList.add(new Level("b", 1));
		levelList.add(new Level("c", 2));
		
		
		return levelList;
	}
}
