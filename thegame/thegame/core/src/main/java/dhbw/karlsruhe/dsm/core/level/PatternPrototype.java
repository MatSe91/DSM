package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;


public class PatternPrototype implements Json.Serializable{
	
	private float[] vertices;
	private short[] triangles;
	
	private Texture texture;
	private TextureRegion region;
	private PolygonRegion polyRegion;
	
	public PatternPrototype() {
	}
	
	public void load() {
		generateTriangles();
		texture = new Texture("textures/solid_blue.png");
		region = new TextureRegion(texture);
		polyRegion = new PolygonRegion(region, vertices, triangles);
	}
	
	public void dispose() {
		texture.dispose();
	}
	
	public Pattern createPattern(float worldPositionX, float worldPositionY, World world) {
		return new Pattern(polyRegion, worldPositionX, worldPositionY, world);
	}
	
	private void generateTriangles() {
		// for each vertice above the third => one more triangle
		int countTriangles = (vertices.length / 2) - 2;
		this.triangles = new short[countTriangles * 3];
		// for triangulate (numbers of vertices): 0,1,2 | 0,2,3 | 0,3,4 | 0,4,5 ...
		for(int triangle = 0; triangle < countTriangles; triangle++)
		{
			triangles[triangle * 3] 	= 0;
			triangles[triangle * 3 + 1] = (short) (1 + 1 * triangle);
			triangles[triangle * 3 + 2] = (short) (2 + 1 * triangle);
		}
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void write(Json json) {
		json.writeValue("vertices", vertices);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		setVertices(json.readValue("vertices", float[].class, jsonData));
	}
}
