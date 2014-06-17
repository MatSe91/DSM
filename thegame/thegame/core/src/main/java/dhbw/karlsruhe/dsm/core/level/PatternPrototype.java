package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;


public class PatternPrototype implements Json.Serializable{
	
	private float[][] vertices;
	private float[] listedVertices;
	private short[] triangles;
	
	private Texture texture;
	private TextureRegion region;
	private PolygonRegion polyRegion;
	
	public PatternPrototype() {
	}
	
	public void load() {
		generatePatternsTriangles();
		texture = new Texture("textures/solid_blue.png");
		region = new TextureRegion(texture);
		polyRegion = new PolygonRegion(region, listedVertices, triangles);
	}

	public void dispose() {
		texture.dispose();
	}
	
	public Pattern createPattern(float worldPositionX, float worldPositionY, World world) {
		return new Pattern(polyRegion, worldPositionX, worldPositionY, world);
	}
	
	private void generatePatternsTriangles() {
		int countTriangles = 0;
		int countVertices = 0;
		short startVerticeNo = 0;
		
		// for all connected vertices
		for(float[] connectedVertices : vertices) {
			
			// check if vertices are declared valid
			if( (connectedVertices.length==0) || 
				(connectedVertices.length%2!=0) )
				continue;
			
			for(float vertice : connectedVertices) {
				addVertice(vertice);
			}
			
			countVertices = connectedVertices.length / 2;
			
			// for each vertice above the third => one more triangle
			countTriangles = countVertices - 2;
			addTriangles(countTriangles);

			generateTriangles(countTriangles, startVerticeNo);
			
			startVerticeNo += countVertices;
		}
	}

	private void generateTriangles(int countTriangles, short startVerticeNo) {
		
		int i = (startVerticeNo == 0 ? 0 : ((startVerticeNo-2)*3));
		int verticeNo = startVerticeNo;
		// for triangulate (numbers of vertices): 0,1,2 | 0,2,3 | 0,3,4 | 5,6,7 ...
		for(int triangle = i; triangle<i+countTriangles*3; triangle+=3)
		{
			triangles[triangle] 	= startVerticeNo;
			triangles[triangle + 1] = (short) (1 + verticeNo );
			triangles[triangle + 2] = (short) (2 + verticeNo );
			verticeNo++;
		}
	}
	
	private void addTriangles(int count) {
		short[] temp = new short[0];
		
		if(triangles != null) {
			temp = new short[triangles.length];
			temp = triangles.clone();
		}
		
		this.triangles = new short[temp.length + (count * 3)];

		for(int i = 0; i < temp.length; i++)
			triangles[i] = temp[i];
	}
	
	private void addVertice(float vertice) {
		float[] temp = new float[0];
		
		if(listedVertices != null) {
			temp = new float[listedVertices.length];
			temp = listedVertices.clone();
		}
		
		this.listedVertices = new float[temp.length + 1];

		for(int i = 0; i < temp.length; i++)
			listedVertices[i] = temp[i];
		
		listedVertices[listedVertices.length - 1] = vertice;
	}

	public float[][] getVertices() {
		return vertices;
	}

	public void setVertices(float[][] vertices) {
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
		setVertices(json.readValue("vertices", float[][].class, jsonData));
	}
}
