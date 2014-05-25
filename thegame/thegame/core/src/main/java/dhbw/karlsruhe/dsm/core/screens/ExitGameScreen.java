package dhbw.karlsruhe.dsm.core.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.actions.ChangeScreenInputListener;
import dhbw.karlsruhe.dsm.core.screenCommands.MenuScreenChangeCommand;

public final class ExitGameScreen implements Screen {
	// Button Label Strings
	private static final String BUTTON_EXIT_GAME_TEXT = "Exit Game";
	private static final String BUTTON_CANCEL_EXIT_TEXT = "Cancel";

	// Button Mouse-Over Event Strings
	private static final String BUTTON_EXIT_GAME_MOUSEOVER_TEXT = "Exit Game";
	private static final String BUTTON_CANCEL_EXIT_MOUSEOVER_TEXT = "Go back to the previous Screen!";
	
	private Stage stage;
	private DSM game;
	private final Class previous;
	
	// Buttons
	private TextButton exitGameButton;
	private TextButton cancelExitButton;
	// Labels
	private Label menuText;
	private Label headline;
	// Tables
	private Table table;
	
	public ExitGameScreen(DSM game, Screen previous) {
		this.game = game;
		this.previous = previous.getClass();
		this.stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage

		stage.addActor(headline);
		stage.addActor(table);
		stage.addActor(menuText);
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		

		// Table.drawDebug(stage); // Debuglines for Tables

	}

	@Override
	public void resize(int width, int height) {
		
		stage.setViewport(width, height, true);

	}

	@Override
	public void resume() {

	}
	
	public boolean needsGL20() {
		return true;
	}

	@Override
	public void show() {

	}
	
	
	/**
	 * Initializes all Actors (delegates)
	 */
	private void initActors() {
		// Prepare everything
		initLabels();
		initButtons();
		// Initialize our Actors
		initTable();
	}
	
	/**
	 * Initializes the table 
	 */
	private void initTable() {
		table = game.screenHelper.createTable();
		table.padLeft(0);

		table.add(cancelExitButton).expandX();
		table.add(exitGameButton).expandX(); 
			
	}
	
	/**
	 * Initializes all the buttons, including their InputListeners
	 */
	private void initButtons() {
		exitGameButton		= game.screenHelper.createTextButton(BUTTON_EXIT_GAME_TEXT, BUTTON_EXIT_GAME_MOUSEOVER_TEXT, menuText);
		cancelExitButton	= game.screenHelper.createTextButton(BUTTON_CANCEL_EXIT_TEXT, BUTTON_CANCEL_EXIT_MOUSEOVER_TEXT, menuText);
		cancelExitButton.addListener(new ChangeScreenInputListener(new MenuScreenChangeCommand()));
		// Event Listeners 
		// TODO: clean up this giant piece of shitty code.
		
		exitGameButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				exitGame();
				return false;
			}
		});
	}
	
	/**
	 * Initializes the Label
	 */
	private void initLabels() {	
		menuText = game.screenHelper.createLabel("", 0, 160, 50, 0);
		menuText.setWidth(stage.getWidth());
		menuText.setZIndex(10);
		
		headline = game.screenHelper.createHeadline("Are you sure you want to quit?");
	}
	
	/**
	 * Exits the Application
	 */
	private void exitGame() {
			
		pause();
		this.dispose();
		game.exit();
			
	}
}
