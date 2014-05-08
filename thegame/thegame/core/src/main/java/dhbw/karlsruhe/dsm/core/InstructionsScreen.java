/**
 * 
 */
package dhbw.karlsruhe.dsm.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class InstructionsScreen implements Screen {

	private static final String BUTTON_EXIT_GAME_TEXT = "Back";
	private static final String CONTROLS_JUMP_ICON_TEXT = "W, Arrow UP, Space";
	private static final String CONTROLS_DUCK_ICON_TEXT = "S, Arrow DOWN, Left Shift";
	private static final String CONTROLS_PAUSE_ICON_TEXT = "Esc, P";
	private static final String CONTROLS_JUMP_DESCRIPTION_TEXT = "Jump";
	private static final String CONTROLS_DUCK_DESCRIPTION_TEXT = "Duck";
	private static final String CONTROLS_PAUSE_DESCRIPTION_TEXT = "Pause";
	private static final String HEADLINE_TEXT = "INSTRUCTIONS";
	
	private Stage stage;
	private DSM game;
	private Screen previous;
	
	// Button
	private TextButton backButton;
	//Table
	private Table table;
	//Labels
	private Label headline;
	// TODO @MO: icons statt labels
	private Label controlsJumpIcon;
	private Label controlsDuckIcon;
	private Label controlsPauseIcon;
	private Label controlsJumpDescription;
	private Label controlsDuckDescription;
	private Label controlsPauseDescription;
	
	public InstructionsScreen(DSM game, Screen previous) {
		this.game = game;
		this.previous = previous;
		this.stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage

		stage.addActor(headline);
		stage.addActor(table);

	}
	
	/**
	 * Initializes all Actors (delegates)
	 */
	private void initActors() {
		// Prepare everything
		initButton();
		// Initialize our Actors
		initLabels();
		initTable();
	}
	
	private void initButton() {
		backButton = new TextButton(BUTTON_EXIT_GAME_TEXT, game.textButtonStyle);
		
		// Event Listeners
		backButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				exitInstructionsScreen();
				return false;
			}
		});
	}
	
	private void initTable() {

		int rowSpace = 50;
		
		table = new Table();
		
		table.setSize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		table.setPosition(0, 0);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(50);
		table.right().padRight(50);

		table.row().spaceBottom(rowSpace);
		table.add(controlsJumpIcon).align(Align.left);
		table.add(controlsJumpDescription).align(Align.left).expandX();
		table.row().spaceBottom(rowSpace);
		table.add(controlsDuckIcon).align(Align.left);
		table.add(controlsDuckDescription).align(Align.left).expandX();
		table.row().spaceBottom(rowSpace);
		table.add(controlsPauseIcon).align(Align.left);
		table.add(controlsPauseDescription).align(Align.left).expandX();
		table.row().spaceBottom(rowSpace);
		table.add(backButton).colspan(2).expandX().align(Align.right);
	}
	
	private void initLabels() {
		controlsJumpIcon = new Label(CONTROLS_JUMP_ICON_TEXT, game.labelStyle);
		controlsDuckIcon = new Label(CONTROLS_DUCK_ICON_TEXT, game.labelStyle);
		controlsPauseIcon = new Label(CONTROLS_PAUSE_ICON_TEXT, game.labelStyle);
		controlsJumpDescription = new Label(CONTROLS_JUMP_DESCRIPTION_TEXT, game.labelStyle);
		controlsDuckDescription = new Label(CONTROLS_DUCK_DESCRIPTION_TEXT, game.labelStyle);
		controlsPauseDescription = new Label(CONTROLS_PAUSE_DESCRIPTION_TEXT, game.labelStyle);
		
		headline = new Label(HEADLINE_TEXT, game.labelStyle);
		headline.setWidth(stage.getWidth());
		headline.setHeight(80);
		headline.setPosition(0, stage.getHeight() - 80);
		headline.setAlignment(Align.center);
	}
	
	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		

		Table.drawDebug(stage); // Debuglines for Tables
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	/**
	 * Destroys the current Screen and redirects to the previous one.
	 */
	private void exitInstructionsScreen() {
		game.setScreen(previous);
		dispose();
	}
}
