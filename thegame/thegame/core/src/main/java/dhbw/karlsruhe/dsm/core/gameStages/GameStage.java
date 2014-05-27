package dhbw.karlsruhe.dsm.core.gameStages;

import java.util.concurrent.ArrayBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screenCommands.GamePauseScreenCommand;

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
	protected DSM game;
	protected OrthographicCamera screenCamera;
	
	protected ArrayBlockingQueue<PolygonSprite> shapes;
	protected PolygonSpriteBatch polyBatch;
	protected float speed;
	
	// WORKING VARIABLES
	private float i = 0;
	private float totalRightBound;
	private PolygonSprite temp;
	
	public GameStage(OrthographicCamera camera, Level currentLevel) {
		super();
		screenCamera = camera;
		game = (DSM) Gdx.app.getApplicationListener();
		
		polyBatch = new PolygonSpriteBatch();
		polyBatch.setProjectionMatrix(screenCamera.combined);
		shapes = new ArrayBlockingQueue<PolygonSprite>(MAX_PATTERNS);
		setLevel(currentLevel);
		initLevel();
	}
	
	public void setLevel(Level level) {
		currentLevel = level;
		speed = 500;
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
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		// move Polygons
		i = delta * speed;
		for (PolygonSprite shape : shapes) {
			shape.translate(-i, 0);
		}
		totalRightBound -= i;
		managePatterns();
	}

	private void managePatterns() {
		temp = shapes.peek();
		while(temp != null && temp.getBoundingRectangle().getWidth() + temp.getX() < -150) {
			temp = shapes.poll();
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
		if(speed > 0f) 
		{
			GamePauseScreenCommand gamePauseScreenCommand = new GamePauseScreenCommand();
			gamePauseScreenCommand.execute();
			speed = 0f;
		}
		else
		{
			speed = 500;
		}
	}
}
