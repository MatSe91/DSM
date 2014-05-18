package dhbw.karlsruhe.dsm.core.gameStages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;

/**
 * This Stage is responsible for:<br>
 * 		> managing / updating all Actors<br>
 * 		> processing game logic<br>
 * 		> processing inputs<br>
 * 		> drawing the game<br>
 * <br>
 * <i>extends com.badlogic.gdx.scenes.scene2d.Stage</i>
 */
public class GameStage extends Stage {

	protected Level currentLevel;
	protected DSM game;
	
	protected List<PolygonSprite> shapes;
	protected PolygonSpriteBatch polyBatch;
	protected float speed;
	
	// WORKING VARIABLES
	private float i = 0;
	
	// ONLY FOR TESTING
	protected Texture texture;
	// ONLY FOR TESTING END
	
	public GameStage() {
		super();
		
		game = (DSM) Gdx.app.getApplicationListener();
		shapes = new ArrayList<PolygonSprite>();
		testInit();
		setLevel(null);
	}
	
	public void setLevel(Level level) {
		currentLevel = level;
		speed = 10;
	}
	
	@Deprecated
	private void testInit() {
		texture =  new Texture("textures/solid_blue.png");
		polyBatch = new PolygonSpriteBatch();
		float[] vertices = new float[] {
			0, 0,
			50,0,
			25,25
		};
		PolygonRegion polyRegion = new PolygonRegion(new TextureRegion(texture), vertices, new short[] {0,1,2});
		PolygonSprite poly = new PolygonSprite(polyRegion);
		poly.setOrigin(0, 0);
		shapes.add(poly);
	}

	/**
	 * Initializes the current Level by creating the first patterns.
	 */
	protected void initLevel() {
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#draw()
	 */
	@Override
	public void draw() {
		polyBatch.begin();
		for(PolygonSprite shape : shapes) {
			shape.draw(polyBatch);
		}
		polyBatch.end();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		i = - delta * speed;
		for (PolygonSprite shape : shapes) {
			shape.translate(i, 0);
		}
	}
	
}
