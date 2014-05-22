package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PatternPrototype {
	
	private Texture texture;
	private float[] vertices;
	private short[] triangles;
	
	public Pattern createPattern() {
		PolygonRegion polyRegion = new PolygonRegion(new TextureRegion(texture), vertices, triangles);
		return new Pattern(polyRegion);
	}
	
	private void setTriangles() {
		// for each vertice above the third => one more triangle
		System.out.println(vertices.length);
		int countTriangles = (vertices.length / 2) - 2;
		this.triangles = new short[countTriangles * 3];
		
		// 0,1,2 | 0,2,3 | 0,3,4 | 0,4,5 ......
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
		setTriangles();
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
