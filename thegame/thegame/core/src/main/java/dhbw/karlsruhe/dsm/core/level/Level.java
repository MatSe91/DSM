package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	
	public Pattern getRandomPattern() {
		int index = (int) (Math.random() * availablePatterns.size());
		// DEBUG
		System.out.println(index);
		return availablePatterns.get(index).createPattern();
	}
	
	// only temporary
	public PolygonSprite getRandomPolygonSprite(float worldPositionX, float worldPositionY) {
		/*float[] vertices = new float[] {
			0, 0,
			50,0,
			25,25
		};
		PolygonRegion polyRegion = new PolygonRegion(new TextureRegion(texture), vertices, new short[] {0,1,2});
		//PolygonSprite poly = new PolygonSprite(polyRegion);*/
		PolygonSprite poly = getRandomPattern();
		poly.setPosition(worldPositionX, worldPositionY);
		return poly;
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
