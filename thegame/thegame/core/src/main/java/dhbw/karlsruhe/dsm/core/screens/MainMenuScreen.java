package dhbw.karlsruhe.dsm.core.screens;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;

public class MainMenuScreen implements Screen {
	// Button Label Strings
	private static final String BUTTON_START_GAME_TEXT = "Start Game!";
	private static final String BUTTON_SHOW_HIGHSCORE_TEXT = "Show Highscore";
	private static final String BUTTON_SHOW_INSTRUCTIONS_TEXT = "Show Instructions";
	private static final String BUTTON_CREDITS_TEXT = "Credits";
	private static final String BUTTON_EXIT_TEXT = "Exit";
	// Button Mouse-Over Event Strings
	private static final String BUTTON_START_GAME_MOUSEOVER_TEXT = "Let's get it on!";
	private static final String BUTTON_SHOW_HIGHSCORE_MOUSEOVER_TEXT = "Get to know who the fuck pwnd you, and who got pwnd by you";
	private static final String BUTTON_SHOW_INSTRUCTIONS_MOUSEOVER_TEXT = "Learn2Play!";
	private static final String BUTTON_CREDITS_MOUSEOVER_TEXT = "Click here to see who made this game.\nOtherwise you can't blame us.";
	private static final String BUTTON_EXIT_MOUSEOVER_TEXT = "If you really want to quit playing, just click it :'(";
	
	private Stage stage;
	private DSM game;
	
	// Buttons
	private TextButton startGameButton;
	private TextButton showHighscoreButton;
	private TextButton showInstructionsButton;
	private TextButton creditButton;
	private TextButton leaveGameButton;
	// Labels
	private Label menuText;
	private Label headline;
	// Tables
	private Table table;
	
	public MainMenuScreen(DSM game) {
		this.game = game;

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
		
		// DEBUG @Denis: probiers aus! =P
		 Table.drawDebug(stage); // Debuglines for Tables

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
		Gdx.input.setInputProcessor(stage);
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
		
		int rowheight = 50;
		
			table.add(startGameButton).height(rowheight).fillX(); 
		table.row();
			table.add(showHighscoreButton).height(rowheight).fillX(); 
		table.row();
			table.add(showInstructionsButton).height(rowheight).fillX(); 
		table.row();
			table.add(creditButton).height(rowheight).fillX(); 
		table.row();
			table.add(leaveGameButton).height(rowheight).fillX();
	}
	
	/**
	 * Initializes all the buttons, including their InputListeners
	 */
	private void initButtons() {
		
		startGameButton 		= new TextButton(BUTTON_START_GAME_TEXT, game.textButtonStyle);
		showHighscoreButton 	= new TextButton(BUTTON_SHOW_HIGHSCORE_TEXT, game.textButtonStyle);
		showInstructionsButton 	= new TextButton(BUTTON_SHOW_INSTRUCTIONS_TEXT, game.textButtonStyle);
		creditButton 			= new TextButton(BUTTON_CREDITS_TEXT, game.textButtonStyle);
		leaveGameButton 		= new TextButton(BUTTON_EXIT_TEXT, game.textButtonStyle);
		
		// Event Listeners 
		// TODO: clean up this giant piece of shitty code.
		
		startGameButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				// TODO: Implement Start Game Button click event
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_START_GAME_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		
		showHighscoreButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				showLevelSelectionScreen();
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_SHOW_HIGHSCORE_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		
		showInstructionsButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				// TODO @Mo: Implement show Instructions Button click event
				showInstructionsScreen();
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_SHOW_INSTRUCTIONS_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		
		creditButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				// TODO: Implement show Credits Button click event
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_CREDITS_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		
		leaveGameButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				//Methode wird in exitGame verlagert
				showExitScreen();
				return false;
			}
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText(BUTTON_EXIT_MOUSEOVER_TEXT);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				menuText.setText("");
			}
		});
		// TODO: Fix Layout for Web
		if (Gdx.app.getType() == ApplicationType.WebGL) {
			leaveGameButton.clear();
			leaveGameButton.setVisible(false);
		}
	}
	

	/**
	 * Initializes the Label
	 */
	private void initLabels() {		
		// note: ScreenHelper cannot be used for menuText, because it's to specific.
		menuText = new Label("", game.labelStyle);
		menuText.setHeight(50);
		menuText.setPosition(210, stage.getHeight() / 2 - menuText.getHeight() / 2);
		menuText.setZIndex(10);
		
		headline = game.stageHelper.createHeadline("DSM - MAIN MENU");
	}
	
	private void showExitScreen() {
		game.setScreen(new ExitGameScreen(game,this));
	}
	
	private void showInstructionsScreen() {
		game.setScreen(new InstructionsScreen(game, this));
	}
	
	protected void showLevelSelectionScreen() {
		game.setScreen(new LevelSelectionScreen(game, this));
	}
}
