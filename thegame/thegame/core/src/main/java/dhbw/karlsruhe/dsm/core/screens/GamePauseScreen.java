package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screenCommands.ClosePauseScreenCommand;


public class GamePauseScreen implements Screen {

	private static final String HEADLINE_TEXT = "GAME PAUSED";
	private static final String SAVE_GAME_TEXT = "Save Game";
	private static final String CLOSE_GAME_TEXT = "Close Game";

	private DSM game;
	private Stage stage;

	private TextButton backButton;
	private TextButton saveGame;
	private TextButton closeGame;
	private Label headline;
	
	public GamePauseScreen(DSM game) {
		
		this.game = game;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage
		stage.addActor(headline);
		stage.addActor(saveGame);
		stage.addActor(closeGame);
		stage.addActor(backButton);
	}
	
	private void initActors() {
		initLabels();
		initButtons();
	}
	
	private void initLabels() {
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT);
	}
	
	private void initButtons() {
		// TODO: implement save and close functions
		saveGame = game.screenHelper.createTextButton(SAVE_GAME_TEXT, 100, 350, 120);
		closeGame = game.screenHelper.createTextButton(CLOSE_GAME_TEXT, 100, 300, 120);
		
		backButton = game.screenHelper.createReturnButton(new ClosePauseScreenCommand(game));
	}
	
	@Override
	public void render(float delta) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		
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
		stage.dispose();
	}

}
