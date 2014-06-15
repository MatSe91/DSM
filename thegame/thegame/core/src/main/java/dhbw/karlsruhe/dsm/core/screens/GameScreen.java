package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.gameStages.GameStage;
import dhbw.karlsruhe.dsm.core.gameStages.GuiStage;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screenCommands.GameLevelSelectionScreenChangeCommand;

public class GameScreen implements Screen {

	protected final DSM game;
	protected final GameStage gameStage;
	protected final GuiStage guiStage;
	protected final Level level;
	protected SpriteBatch gameBatch;
	
	protected OrthographicCamera camera;
	
	public GameScreen(DSM game, Level level) {
		this.game = game;
		this.level = level;
		this.guiStage = new GuiStage();
		this.gameBatch = new SpriteBatch();
		camera = new OrthographicCamera();
//		camera.setToOrtho(false, ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		camera.setToOrtho(false, 21.6f, 28.8f);
		
		gameStage = new GameStage(camera, level, gameBatch);
	//	Gdx.input.setInputProcessor(gameStage);
		
		gameBatch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.begin();
		drawFps();
		gameStage.getCurrentScore().draw(game.batch);
		game.batch.end();
		
		
		// Execute game logic
		gameStage.act(Gdx.graphics.getDeltaTime());
		// Draw game
		gameStage.draw();
		// Execute GUI logic
	//	guiStage.act(Gdx.graphics.getDeltaTime());
		// Draw GUI
	//	guiStage.draw();
	}
	

	private void drawFps() {
		game.gameFont.draw(game.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 5, game.getHeight());
	}

	@Override
	public void resize(int width, int height) {
		//gameStage.setViewport(width, height, true);
		camera.setToOrtho(false, width, height);
		camera.viewportHeight = 21.6f;
		camera.viewportWidth = 28.8f;
	}
	
	public boolean needsGL20() {
		return true;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gameStage);
		camera.viewportHeight = 21.6f;
		camera.viewportWidth = 28.8f;
	}

	@Override
	public void hide() {
		camera.viewportHeight = game.getHeight();
		camera.viewportWidth = game.getWidth();
		game.batch.dispose();
		game.batch = new SpriteBatch();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		gameStage.dispose();
	}
	
	protected void returnToLevelSelectionScreen() {
		new GameLevelSelectionScreenChangeCommand().execute();
	}

}
