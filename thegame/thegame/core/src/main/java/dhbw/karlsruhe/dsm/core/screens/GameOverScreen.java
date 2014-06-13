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
import dhbw.karlsruhe.dsm.core.screenCommands.GameLevelSelectionScreenChangeCommand;

public class GameOverScreen implements Screen {

	private static final String BUTTON_RESTART_TEXT = "Restart Level";
	private static final String HEADLINE_TEXT = "GAME OVER";
	private static final String SCORE_TEXT = "You reach: " /* TODO: GET REACHED SCORE !! */ + "POINTS";
	
	private DSM game;
	private GameScreen gameScreen;
	private Stage stage;
	
	private TextButton restartButton;
	private TextButton backButton;
	
	private Label headline;
	private Label score;
	
	public GameOverScreen(DSM game, GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
		this.stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage;
		stage.addActor(headline);
		stage.addActor(score);
		stage.addActor(restartButton);
		stage.addActor(backButton);
	}
	
	private void initActors() {
		initLabels();
		initButtons();
	}

	private void initLabels() {
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT);
		score = game.screenHelper.createLabel(SCORE_TEXT, 100, 500);
	}

	private void initButtons() {
		// default positioning values
		int x=100, y=400/*, space=50*/, width=120;
		
		restartButton = game.screenHelper.createTextButton(BUTTON_RESTART_TEXT, x, y, width);
		backButton = game.screenHelper.createReturnButton(new GameLevelSelectionScreenChangeCommand());
		
		// Button listeners
		backButton.addListener(returnToLevelSelectionScreenListener());
		restartButton.addListener(restartLevelScreenListener());
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
	}
}
