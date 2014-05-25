package dhbw.karlsruhe.dsm.core.screens;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.actions.ChangeScreenInputListener;
import dhbw.karlsruhe.dsm.core.screenCommands.CreditScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.ExitScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.GameLevelSelectionScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.HighscoreLevelSelectionScreenChangeCommand;
import dhbw.karlsruhe.dsm.core.screenCommands.InstructionsScreenChangeCommand;

public class MainMenuScreen implements Screen {
	// Button Label Strings
	private static final String BUTTON_START_GAME_TEXT = "Start Game!";
	private static final String BUTTON_SHOW_HIGHSCORE_TEXT = "Show Highscore";
	private static final String BUTTON_SHOW_INSTRUCTIONS_TEXT = "Show Instructions";
	private static final String BUTTON_CREDITS_TEXT = "Credits";
	private static final String BUTTON_EXIT_TEXT = "Exit";
	// Button Mouse-Over Event Strings
	private static final String BUTTON_START_GAME_MOUSEOVER_TEXT = "Let's get it on!";
	private static final String BUTTON_SHOW_HIGHSCORE_MOUSEOVER_TEXT = "Get to know who the fuck pwnd you, \nand who got pwnd by you";
	private static final String BUTTON_SHOW_INSTRUCTIONS_MOUSEOVER_TEXT = "Learn2Play!";
	private static final String BUTTON_CREDITS_MOUSEOVER_TEXT = "Click here to see who made this game.\nOtherwise you can't blame us.";
	private static final String BUTTON_EXIT_MOUSEOVER_TEXT = "If you really want to quit playing, just click it :'(";
	
	private static final int ROW_HEIGHT = 50;
	
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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		// Debuglines for Tables
		 //Table.drawDebug(stage);

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
		initLabels();
		initButtons();
		// Initialize our Actors
		initTable();
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
		
		addButtonToTable(startGameButton);
		addButtonToTable(showHighscoreButton);
		addButtonToTable(showInstructionsButton);
		addButtonToTable(creditButton);
		addButtonToTable(leaveGameButton);
	}
	
	private void addButtonToTable(TextButton button) {
		table.add(button).height(ROW_HEIGHT).align(Align.left); 
		table.row();
	}
	
	/**
	 * Initializes all the buttons, including their InputListeners
	 */
	private void initButtons() {
		startGameButton 		= game.screenHelper.createTextButton(BUTTON_START_GAME_TEXT, BUTTON_START_GAME_MOUSEOVER_TEXT, menuText);
		startGameButton.addListener(new ChangeScreenInputListener(new GameLevelSelectionScreenChangeCommand()));
		
		showHighscoreButton 	= game.screenHelper.createTextButton(BUTTON_SHOW_HIGHSCORE_TEXT, BUTTON_SHOW_HIGHSCORE_MOUSEOVER_TEXT, menuText);
		showHighscoreButton.addListener(new ChangeScreenInputListener(new HighscoreLevelSelectionScreenChangeCommand()));
		
		showInstructionsButton 	= game.screenHelper.createTextButton(BUTTON_SHOW_INSTRUCTIONS_TEXT, BUTTON_SHOW_INSTRUCTIONS_MOUSEOVER_TEXT, menuText);
		showInstructionsButton.addListener(new ChangeScreenInputListener(new InstructionsScreenChangeCommand()));
		
		creditButton 			= game.screenHelper.createTextButton(BUTTON_CREDITS_TEXT, BUTTON_CREDITS_MOUSEOVER_TEXT, menuText);
		creditButton.addListener(new ChangeScreenInputListener(new CreditScreenChangeCommand()));
		
		leaveGameButton 		= game.screenHelper.createTextButton(BUTTON_EXIT_TEXT, BUTTON_EXIT_MOUSEOVER_TEXT, menuText);
		leaveGameButton.addListener(new ChangeScreenInputListener(new ExitScreenChangeCommand()));
		
				
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
		menuText.setPosition(275, stage.getHeight() / 2 - menuText.getHeight() / 2);
		menuText.setZIndex(10);
		
		headline = game.screenHelper.createHeadline("DSM - MAIN MENU");
	}

}
