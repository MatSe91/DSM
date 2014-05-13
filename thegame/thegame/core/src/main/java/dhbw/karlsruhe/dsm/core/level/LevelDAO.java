package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public final class LevelDAO {
	
	private LevelDAO() {
		
	}
	
	
	public static Level getLevelFromFile(String levelPath) {
		Json json = new Json();
		FileHandle handle = Gdx.files.local(levelPath);
		String jsonString = handle.readString();
		return json.fromJson(Level.class, jsonString); 
	}
	
	/*
	 * Returns a list of all available levels. 
	 * Sorted by their index.
	 */
	public static List<Level> loadLevels() {
		saveLevel(new Level("Saved Level", 0));

		
		List<Level> levelList = new ArrayList<Level>();
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_LEVEL_DIRECTORY);
		FileHandle[] levelHandles = handle.list();
		
		
		Level level;
		for (FileHandle levelHandle : levelHandles) {
			level = getLevelFromFile(levelHandle.path());
			
			levelList.add(level);
		}

		levelList = sortLevelList(levelList);
		
		
		return levelList;
	}
	
	private static List<Level> sortLevelList(List<Level> levelList) {
		//TODO: sort List by index
		return levelList;
	}

	public static void saveLevel(Level level) {
		Json json = new Json();
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + level.getName());
		handle.writeString(json.toJson(level), false);
	}
	
}
