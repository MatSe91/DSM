package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class LevelDAO {
	
	
	public static Level getLevelFromFile(String levelName) {
		// TODO: replace
		Json json = new Json();
		FileHandle handle = Gdx.files.internal(ConfigurationConstants.PATH_LEVEL_DIRECTORY + levelName);
		String jsonString = handle.readString();
		Level level = json.fromJson(Level.class, jsonString); 
		return level;
	}
	
	/*
	 * Returns a list of all available levels. 
	 * Sorted by their index.
	 */
	public static List<Level> loadLevels() {
		//ConfigurationConstants.PATH_LEVEL_DIRECTORY
		List<Level> levelList = new ArrayList<Level>();
		FileHandle handle = Gdx.files.internal(ConfigurationConstants.PATH_LEVEL_DIRECTORY);
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
		List<Level> sortedList = new ArrayList<Level>();
		int index = 0;
		for (Level level : levelList) {
			index = level.getIndex();
			while(sortedList.get(index) != null) {
				index++;
			}
			sortedList.add(level.getIndex(), level);
		}
		return sortedList;
	}

	public static void saveLevel(Level level) {
		Json json = new Json();
		FileHandle handle = Gdx.files.local("data/levels/" + level.getName());
		handle.writeString(json.toJson(level), false);
	}
	
}
