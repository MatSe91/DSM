package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Level {
	
	private String name;
	private int index;
	
	public float speed;
	
	private List<PatternPrototype> availablePatterns;
	private Texture texture;
	
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
		texture = new Texture("textures/solid_blue.png");
	}
	
	public void dispose() {
		texture.dispose();
	}
	
	public Pattern getRandomPattern(float worldPositionX, float worldPositionY) {
		int index = (int) (Math.random() * availablePatterns.size());
		return availablePatterns.get(index).createPattern(worldPositionX, worldPositionY);
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
	
}
