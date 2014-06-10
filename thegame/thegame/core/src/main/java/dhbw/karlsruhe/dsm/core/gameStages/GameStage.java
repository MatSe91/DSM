package dhbw.karlsruhe.dsm.core.gameStages;

import java.util.concurrent.ArrayBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.Pattern;
import dhbw.karlsruhe.dsm.core.level.Player;
import dhbw.karlsruhe.dsm.core.screenCommands.OpenPauseScreenCommand;

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

	private static final int GROUND_HEIGHT_ZERO = 250;
	private static final int MAX_PATTERNS = 1000;
	protected Level currentLevel;
	protected DSM game = (DSM) Gdx.app.getApplicationListener();
	protected OrthographicCamera screenCamera;
	protected Player player;
	
	protected ArrayBlockingQueue<Pattern> shapes = new ArrayBlockingQueue<Pattern>(MAX_PATTERNS);
	protected PolygonSpriteBatch polyBatch = new PolygonSpriteBatch();

	protected float speed;
	protected float stepLength;
	
	protected World world;
	
	// DEBUG ONLY
	private Box2DDebugRenderer debugRenderer;
	
	// WORKING VARIABLES
	private float i = 0;
	private float totalRightBound;
	private Pattern temp;
	
	public GameStage(OrthographicCamera camera, Level currentLevel) {
		super();
		this.screenCamera = camera;
		this.player = new Player();
		
		polyBatch.setProjectionMatrix(screenCamera.combined);
		
		setLevel(currentLevel);
		initLevel();

		world = currentLevel.getGameWorld();
		player.makePhysical(world);
		
		debugRenderer = new Box2DDebugRenderer();
	}
	
	public void setLevel(Level level) {
		currentLevel = level;
		speed = 400;
		currentLevel.speed = speed;
	}
	
	/**
	 * Initializes the current Level by creating the first patterns.
	 */
	protected void initLevel() {
		currentLevel.load();
		
		float rightBoundX = 0;
		
		while(rightBoundX < screenCamera.viewportWidth*1.2) {
			temp = currentLevel.getRandomPattern(rightBoundX, GROUND_HEIGHT_ZERO);
			rightBoundX += temp.getBoundingRectangle().width;
			shapes.add(temp);
		}
		totalRightBound = rightBoundX;
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
		
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();
		
		debugRenderer.render(world, screenCamera.combined);
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		// move Polygons
		i = delta * speed;
		for (Pattern pattern : shapes) {
			pattern.translateX(-i);
		}
		totalRightBound -= i;
		
		managePatterns();
		
		world.step(delta, 6, 2);
		
		// check loose condition
		if(Math.abs(player.getX() - player.getPhysicalBody().getPosition().x) > 0.1) {
			System.out.println("you lost");
		}
		
		player.updatePosition();
	}

	private void managePatterns() {
		temp = shapes.peek();
		while(temp != null && temp.getBoundingRectangle().getWidth() + temp.getX() < -150) {
			temp = shapes.poll();
			temp.dispose();
		}
		
		while(totalRightBound < screenCamera.viewportWidth + 150) {
			temp = currentLevel.getRandomPattern(totalRightBound, GROUND_HEIGHT_ZERO);
			totalRightBound += temp.getBoundingRectangle().width;
			shapes.add(temp);
		}
	}
	
	@Override
	public void dispose() {
		currentLevel.dispose();
		polyBatch.dispose();
		super.dispose();
	}
	
	public void pause() {
		new OpenPauseScreenCommand(game).execute();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode) 
		{
		case Input.Keys.P:
		case Input.Keys.ESCAPE:
			pause();
			break;
		case Input.Keys.S:{ player.duck();break;}
		case Input.Keys.W:{ player.jump();break;}
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		switch(keycode)
		{
		case Input.Keys.S:
		case Input.Keys.W:{player.normal();break;}
		}
	
		return false;	
	}
}
