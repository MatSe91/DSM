package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Json;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class PatternPrototype {
	
	private Polygon pattern;
	private int id;
	
	public PatternPrototype(int id) {
		this.id = id;
		
		createTestPattern();
		loadPattern();
		
	}
	
	private void loadPattern() {
		Json json = new Json();
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_PATTERNS_DIRECTORY + "/Pattern " + id);
		String jsonString = handle.readString();
		this.pattern = json.fromJson(Polygon.class, jsonString);
	}
	
	public void createTestPattern() {
		saveTestPattern(new Polygon(new float[] {
				-2,		-2,
				-2,		2,
				2,		2,
				2,		-2				
		}));
	}

	private static void saveTestPattern(Polygon pattern) {
		Json json = new Json();
		System.out.println(Gdx.files.getLocalStoragePath());
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_PATTERNS_DIRECTORY + "/Pattern1");
		String jsonString = json.toJson(pattern, Polygon.class);
		handle.writeString(jsonString, false);
		return;
	}
}
