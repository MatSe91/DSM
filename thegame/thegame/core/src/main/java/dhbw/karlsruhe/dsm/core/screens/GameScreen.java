package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.gameStages.GameStage;
import dhbw.karlsruhe.dsm.core.gameStages.GuiStage;
import dhbw.karlsruhe.dsm.core.level.Level;

public class GameScreen implements Screen {

	protected final DSM game;
	protected final GameStage gameStage;
	protected final GuiStage guiStage;
	protected final Level level;
	
	public GameScreen(DSM game, Level level) {
		this.game = game;
		this.level = level;
		this.gameStage = new GameStage();
		this.guiStage = new GuiStage();
		Gdx.input.setInputProcessor(gameStage);
	}
	
	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Execute game logic
		gameStage.act(Gdx.graphics.getDeltaTime());
		// Draw game
		gameStage.draw();
		// Execute GUI logic
		guiStage.act(Gdx.graphics.getDeltaTime());
		// Draw GUI
		guiStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gameStage.setViewport(width, height, true);
	}
	
	public boolean needsGL20() {
		return true;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gameStage);
	}

	@Override
	public void hide() {

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

}
