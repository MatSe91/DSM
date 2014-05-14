/**
 * 
 */
package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;

public class InstructionsScreen implements Screen {

	//private static final String CONTROLS_JUMP_ICON_TEXT = "W, Arrow UP, Space";
	//private static final String CONTROLS_DUCK_ICON_TEXT = "S, Arrow DOWN, Left Shift";
	//private static final String CONTROLS_PAUSE_ICON_TEXT = "Esc, P";
	private static final String CONTROLS_JUMP_DESCRIPTION_TEXT = "Jump";
	private static final String CONTROLS_DUCK_DESCRIPTION_TEXT = "Duck";
	private static final String CONTROLS_PAUSE_DESCRIPTION_TEXT = "Pause";
	private static final String HEADLINE_TEXT = "INSTRUCTIONS";
	private static final String GAME_DESCRIPTION = "Try to avoid obstacles with ducking and jumping.";
	
	private Stage stage;
	private DSM game;
	private Screen previous;
	
	// Button
	private TextButton backButton;
	//Tables
	private Table table;
	private Table tableControlsJumpIcons;
	private Table tableControlsDuckIcons;
	private Table tableControlsPauseIcons;
	//Labels
	private Label headline;
	private Label gameDescription;
	// TODO @MO: Texture Maps
	private Texture controlsJumpIconW;
	private Texture controlsJumpIconArrowUP;
	private Texture controlsJumpIconSpace;
	private Texture controlsDuckIconS;
	private Texture controlsDuckIconArrowDOWN;
	private Texture controlsDuckIconShift;
	private Texture controlsPauseIconEsc;
	private Texture controlsPauseIconP;
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
		// Initialize our Actors
		initButton();
		initLabels();
		initTextures();
		initTable();
	}
	
	private void initButton() {
		backButton = game.screenHelper.createReturnButton(MainMenuScreen.class);
		
		// Event Listeners
		backButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				exitInstructionsScreen();
				return false;
			}
		});
	}
	
	private void initTable() {

		int rowSpace = 15;
		int colSpace = 20;
		int iconSize = 75;
		
		table = game.screenHelper.createTable();
		
		table.setSize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		table.setPosition(0, 0);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(50);
		table.right().padRight(50);
		
		// Controls Jump Icons
		tableControlsJumpIcons = new Table();
		tableControlsJumpIcons.debug();
		tableControlsJumpIcons.add(new Image(controlsJumpIconW)).left().height(iconSize).width(iconSize);
		tableControlsJumpIcons.add(new Image(controlsJumpIconArrowUP)).left().height(iconSize).width(iconSize);
		tableControlsJumpIcons.row();
		tableControlsJumpIcons.add(new Image(controlsJumpIconSpace)).colspan(2).center().expandX().height(iconSize);
		// Controls Duck Icons
		tableControlsDuckIcons = new Table();
		tableControlsDuckIcons.add(new Image(controlsDuckIconS)).left().height(iconSize).width(iconSize);
		tableControlsDuckIcons.add(new Image(controlsDuckIconArrowDOWN)).left().height(iconSize).width(iconSize);
		tableControlsDuckIcons.add(new Image(controlsDuckIconShift)).left().height(iconSize).width(iconSize);
		// Controls Pause Icons
		tableControlsPauseIcons = new Table();
		tableControlsPauseIcons.add(new Image(controlsPauseIconEsc)).left().height(iconSize).width(iconSize);
		tableControlsPauseIcons.add(new Image(controlsPauseIconP)).left().height(iconSize).width(iconSize);
		
		// Game Description
		table.row().spaceBottom(rowSpace);
		table.add(gameDescription).colspan(2).expandX();
		table.row().spaceBottom(rowSpace);
		// Jump
		table.add(tableControlsJumpIcons).left();
		table.add(controlsJumpDescription).padLeft(colSpace).left().top().expandX();
		table.row().spaceBottom(rowSpace);
		// Duck
		table.add(tableControlsDuckIcons).align(Align.left);
		table.add(controlsDuckDescription).padLeft(colSpace).left().top().expandX();
		table.row().spaceBottom(rowSpace);
		// Pause
		table.add(tableControlsPauseIcons).align(Align.left);
		table.add(controlsPauseDescription).padLeft(colSpace).left().top().expandX();
		table.row().spaceBottom(rowSpace);
		// Button Back
		table.add(backButton).colspan(2).right().expandX();
	}
	
	private void initLabels() {
		// Controls Descriptions
		controlsJumpDescription = game.screenHelper.createLabel(CONTROLS_JUMP_DESCRIPTION_TEXT, 0, 0);
		controlsDuckDescription = game.screenHelper.createLabel(CONTROLS_DUCK_DESCRIPTION_TEXT, 0, 0);
		controlsPauseDescription = game.screenHelper.createLabel(CONTROLS_PAUSE_DESCRIPTION_TEXT, 0, 0);
		
		gameDescription = game.screenHelper.createLabel(GAME_DESCRIPTION, 0, 0);
		
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT);
	}
	
	private void initTextures() {
		// Controls Jump Icons
		controlsJumpIconW = new Texture(Gdx.files.internal("keyIcons/W.png"));
		controlsJumpIconArrowUP = new Texture(Gdx.files.internal("keyIcons/haut.png"));
		controlsJumpIconSpace = new Texture(Gdx.files.internal("keyIcons/espace.png"));
		// Controls Duck Icons
		controlsDuckIconS = new Texture(Gdx.files.internal("keyIcons/S.png"));
		controlsDuckIconArrowDOWN = new Texture(Gdx.files.internal("keyIcons/bas.png"));
		controlsDuckIconShift = new Texture(Gdx.files.internal("keyIcons/S.png"));
		//Controls Pause Icons
		controlsPauseIconEsc = new Texture(Gdx.files.internal("keyIcons/esc.png"));
		controlsPauseIconP = new Texture(Gdx.files.internal("keyIcons/P.png"));
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
		
		controlsJumpIconW.dispose();
		controlsJumpIconArrowUP.dispose();
		controlsJumpIconSpace.dispose();
		
		controlsDuckIconS.dispose();
		controlsDuckIconArrowDOWN.dispose();
		controlsDuckIconShift.dispose();
		
		controlsPauseIconEsc.dispose();
		controlsPauseIconP.dispose();
	}

	/**
	 * Destroys the current Screen and redirects to the previous one.
	 */
	private void exitInstructionsScreen() {
		game.setScreen(previous);
		dispose();
	}
}
