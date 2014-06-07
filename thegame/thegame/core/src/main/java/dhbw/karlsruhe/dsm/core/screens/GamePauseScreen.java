package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.actions.ChangeScreenInputListener;
import dhbw.karlsruhe.dsm.core.screenCommands.AbortGameLevelSelectionScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.AbortGameScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.ClosePauseScreenCommand;


public class GamePauseScreen implements Screen {

	private static final String HEADLINE_TEXT = "GAME PAUSED";
	private static final String SAVE_GAME_TEXT = "Save Game";
	private static final String RESTART_GAME_TEXT = "Restart Game";
	private static final String CLOSE_GAME_TEXT = "Close Game";

	private DSM game;
	private GameScreen gameScreen;
	private Stage stage;

	private TextButton backButton;
	private TextButton saveGame;
	private TextButton restartGame;
	private TextButton closeGame;
	private Label headline;
	
	public GamePauseScreen(DSM game, GameScreen gameScreen) {
		
		this.game = game;
		this.gameScreen = gameScreen;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage
		stage.addActor(headline);
		stage.addActor(saveGame);
		stage.addActor(closeGame);
		stage.addActor(restartGame);
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
		// default positioning values
		int x=100, y=400, space=50, width=120;

		// TODO: implement save function
		saveGame	= game.screenHelper.createTextButton(SAVE_GAME_TEXT, x, y-1*space, width);
		closeGame	= game.screenHelper.createTextButton(CLOSE_GAME_TEXT, x, y-2*space, width);
		restartGame	= game.screenHelper.createTextButton(RESTART_GAME_TEXT, x, y-3*space, width);
		backButton 	= game.screenHelper.createReturnButton(new ClosePauseScreenCommand(game, gameScreen));
		
		// Button Listeners
		closeGame.addListener(returnToLevelSelectionScreenListener());
		restartGame.addListener(restartLevelScreenListener());
	}
	
	private ChangeScreenInputListener returnToLevelSelectionScreenListener() {
		return new ChangeScreenInputListener(new AbortGameLevelSelectionScreenChangeCommand(gameScreen));
	}
	
	private ChangeScreenInputListener restartLevelScreenListener() {
		return new ChangeScreenInputListener(new AbortGameScreenChangeCommand(gameScreen.level, gameScreen));
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
