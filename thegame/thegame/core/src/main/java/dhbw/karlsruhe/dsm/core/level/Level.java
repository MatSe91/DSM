package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class Level {
	
	private World gameWorld;
	
	private String name;
	private int index;
	private float groundHeight;
	
	public float speed;
	
	private List<PatternPrototype> availablePatterns;
	private Pattern startPattern;

	private Texture startTexture;
	
	public Level() {
		// this constructor is required for JSON object serialization
		name = "";
		index = 0;
		speed = 0f;
		groundHeight = 0;
	}
	
	public Level(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public void load() {
		Vector2 gravity = ConfigurationConstants.GRAVITY_VECTOR.cpy();
		if(speed > 100) {
			gravity.scl(speed / ConfigurationConstants.BASE_LEVEL_SPEED);
		}
		setGameWorld(new World(gravity, false));
		
		for(PatternPrototype patternPrototype : availablePatterns) {
			patternPrototype.load();
		}
		createStartPattern();
	}
	
	private void createStartPattern() {
		startTexture = new Texture("textures/solid_blue.png");
		TextureRegion textureRegion = new TextureRegion(startTexture);
		float[] vertices = new float[] {
				0, 0,
				25, 0,
				25, 1,
				0, 1
		};
		short[] triangles = new short[] {
				0, 1, 2, 0, 2, 3
		};
		float[][] shapeVertices = new float[1][];
		shapeVertices[0] = vertices.clone();
		PolygonRegion region = new PolygonRegion(textureRegion, vertices, triangles);
		setStartPattern(new Pattern(region, 0f, groundHeight, gameWorld, shapeVertices));
	}

	public void dispose() {
		for (PatternPrototype patternPrototype : availablePatterns) {
			patternPrototype.dispose();
		}
		if(gameWorld != null)
			gameWorld.dispose();
		if(startTexture != null) 
			startTexture.dispose();
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

	public float getGroundHeight() {
		return groundHeight;
	}

	public void setGroundHeight(float groundHeight) {
		this.groundHeight = groundHeight;
	}

	public Pattern getStartPattern() {
		return startPattern;
	}

	public void setStartPattern(Pattern startPattern) {
		this.startPattern = startPattern;
	}
	
}
