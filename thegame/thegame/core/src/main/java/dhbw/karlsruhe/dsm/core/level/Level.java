package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class Level {
	
	private World gameWorld;
	
	private String name;
	private int index;
	
	public float speed;
	
	private List<PatternPrototype> availablePatterns;
	
	public Level() {
		// this constructor is required for JSON object serialization
		name = "";
		index = 0;
		speed = 0f;
	}
	
	public Level(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public void load() {
		Vector2 gravity = ConfigurationConstants.GRAVITY_VECTOR;
		if(speed > 100) {
			gravity.scl(speed / ConfigurationConstants.BASE_LEVEL_SPEED);
		}
		setGameWorld(new World(gravity, false));
		
		for(PatternPrototype patternPrototype : availablePatterns) {
			patternPrototype.load();
		}
	}
	
	public void dispose() {
		for (PatternPrototype patternPrototype : availablePatterns) {
			patternPrototype.dispose();
		}
		
	}
	
	public Pattern getRandomPattern(float worldPositionX, float worldPositionY) {
		int index = (int) (Math.random() * availablePatterns.size());
		return availablePatterns.get(index).createPattern(worldPositionX, worldPositionY, gameWorld);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public List<PatternPrototype> getAvailablePatterns() {
		return availablePatterns;
	}

	public void setAvailablePatterns(ArrayList<PatternPrototype> availablePatterns) {
		this.availablePatterns = availablePatterns;
	}

	public World getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(World gameWorld) {
		this.gameWorld = gameWorld;
	}
	
}
