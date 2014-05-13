package dhbw.karlsruhe.dsm.core.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;

public final class ExitGameScreen implements Screen {
	// Button Label Strings
	private static final String BUTTON_EXIT_GAME_TEXT = "Exit Game";
	private static final String BUTTON_CANCEL_EXIT_TEXT = "Cancel";

	// Button Mouse-Over Event Strings
	private static final String BUTTON_EXIT_GAME_MOUSEOVER_TEXT = "Exit Game";
	private static final String BUTTON_CANCEL_EXIT_MOUSEOVER_TEXT = "Go back to the previous Screen!";
	
	private Stage stage;
	private DSM game;
	private Screen previous;
	
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
		this.previous = previous;
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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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
		initButtons();
		// Initialize our Actors
		initTable();
		initLabels();
	}
	
	/**
	 * Initializes the table 
	 */
	private void initTable() {
		table = new Table();
		
		table.setSize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		table.setPosition(0, 0);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(50);

		table.add(cancelExitButton).expandX();
		table.add(exitGameButton).expandX(); 
			
	}
	
	/**
	 * Initializes all the buttons, including their InputListeners
	 */
	private void initButtons() {
		
		exitGameButton 		= new TextButton(BUTTON_EXIT_GAME_TEXT, game.textButtonStyle);
		cancelExitButton 	= new TextButton(BUTTON_CANCEL_EXIT_TEXT, game.textButtonStyle);

		
		// Event Listeners 
		// TODO: clean up this giant piece of shitty code.
		
		exitGameButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				exitGame();
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_EXIT_GAME_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		
		cancelExitButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				exitGameScreen();
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_CANCEL_EXIT_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
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
	 * Destroys the current Screen and redirects to the previous one.
	 */
	private void exitGameScreen() {
		game.setScreen(previous);
		dispose();
	}
	
	/**
	 * Exits the Application
	 */
	private void exitGame() {
			
		pause();
		
		previous.dispose();
		this.dispose();
		
		game.dispose();
			
	}
}