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
		level.setGroundHeight(4f);
		loadTestPatterns(level);
		Json json = new Json();
		json.setSerializer(Level.class, new LevelSerializer());
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_LEVEL_DIRECTORY + "/" + level.getName());
		handle.writeString(json.toJson(level), false);
	}
	
	private static void loadTestPatterns(Level level) {

		float gap = 3.5f;
		float length = 2;
		
		ArrayList<PatternPrototype> al = new ArrayList<PatternPrototype>();
		PatternPrototype pp = new PatternPrototype();
		
		pp.setVertices(new float[][] {
			new float[] {
				0, 0,
				length + gap, 0,
				length + gap, 1,
				0, 1
			},
			new float[] {
				length + gap -1, 1,
				length + gap, 1,
				length + gap, 3,
				length + gap - 1, 3
			}
		});
		al.add(pp);
		
		pp = new PatternPrototype();
		pp.setVertices( new float[][] {
			new float[] {
				0, 0,
				length + gap, 0,
				length + gap, 1,
				0, 1
			},
			new float[] {
				length + gap - 1, 2,
				length + gap, 2,
				length + gap, 5,
				length + gap - 1, 5
			}
		});
		al.add(pp);
		
		pp = new PatternPrototype();
		pp.setVertices( new float[][] {
			new float[] {
				0, 0,
				length + gap, 0,
				length + gap, 1,
				0, 1
			},
			new float[] {
				length + gap - 1, 1,
				length + gap, 1,
				length + gap, 3,
				length + gap -1, 3
			},
			new float[] {
				7.5f, 3,
				8.5f, 3,
				8.5f, 5,
				7.5f, 5
			},
			new float[] {
				9, 0,
				9 + length + gap, 0,
				9 + length + gap, 1,
				9, 1
			},
		});
		al.add(pp);
		
		/*
		pp = new PatternPrototype();
		pp.setVertices(new float[][] {
			new float[] {
				0, 0,
				2, 0,
				2, 1,
				0, 1
			},
			new float[] {
				0, 0,
				1, 0,
				1, 1,
				0, 1
			}
		});
		al.add(pp);
		
		pp= new PatternPrototype();
		pp.setVertices(new float[][] {
			new float[] {
				0, 0,
				2, 0,
				2, 1,
				0, 1
			},
			new float[] {
				0,0,
				2,0,
				2,3,
				0,3
			}
		});
		al.add(pp);*/
		
		level.setAvailablePatterns(al);
	}
}
