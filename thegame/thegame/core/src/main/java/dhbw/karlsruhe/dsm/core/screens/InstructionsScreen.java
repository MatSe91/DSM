/**
 * 
 */
package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

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
	
	private static final int ICON_SIZE = 75;
	private static final int ROW_SPACE = 15;
	private static final int COL_SPACE = 20;
	
	private Stage stage;
	private DSM game;
	
	// Button
	private TextButton backButton;
	//Tables
	private Table table;
	//Labels
	private Label headline;
	private Label gameDescription;
	// TODO @MO: Texture Maps
	private Texture controlsJumpIconW;
	private Texture controlsJumpIconArrowUP;
	private Texture controlsJumpIconSpace;
	private Texture controlsDuckIconS;
	private Texture controlsDuckIconArrowDown;
	private Texture controlsDuckIconShift;
	private Texture controlsPauseIconEsc;
	private Texture controlsPauseIconP;
	private Label controlsJumpDescription;
	private Label controlsDuckDescription;
	private Label controlsPauseDescription;

	
	public InstructionsScreen(DSM game) {
		this.game = game;
		this.stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage

		stage.addActor(headline);
		stage.addActor(table);
		stage.addActor(backButton);
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
	}
	

	
	private void initTable() {
		
		table = game.screenHelper.createTable();
		table.right().padRight(50);
		
		// Controls Jump Icons
		Table tableControlsJumpIcons = new Table();
		tableControlsJumpIcons.debug();
		addImageToSubTable(tableControlsJumpIcons, controlsJumpIconW);
		addImageToSubTable(tableControlsJumpIcons, controlsJumpIconArrowUP);
		tableControlsJumpIcons.row();
		tableControlsJumpIcons.add(new Image(controlsJumpIconSpace)).colspan(2).center().expandX().height(ICON_SIZE);
		
		// Controls Duck Icons
		Table tableControlsDuckIcons = new Table();
		addImageToSubTable(tableControlsDuckIcons, controlsDuckIconS);
		addImageToSubTable(tableControlsDuckIcons, controlsDuckIconArrowDown);
		addImageToSubTable(tableControlsDuckIcons, controlsDuckIconShift);
		
		// Controls Pause Icons
		Table tableControlsPauseIcons = new Table();
		addImageToSubTable(tableControlsPauseIcons, controlsPauseIconEsc);
		addImageToSubTable(tableControlsPauseIcons, controlsPauseIconP);
		
		// Game Description
		table.row().spaceBottom(ROW_SPACE);
		table.add(gameDescription).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		// Control Descriptions
		constructTableRow(tableControlsJumpIcons, controlsJumpDescription);
		constructTableRow(tableControlsDuckIcons, controlsDuckDescription);
		constructTableRow(tableControlsPauseIcons, controlsPauseDescription);
	}
	
	private void addImageToSubTable(Table table, Texture icon) {
		table.add(new Image(icon)).left().height(ICON_SIZE).width(ICON_SIZE);
	}
	
	private void constructTableRow(Table cild, Label description) {
		table.add(cild).align(Align.left);
		table.add(description).padLeft(COL_SPACE).left().top().expandX();
		table.row().spaceBottom(ROW_SPACE);
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
		controlsDuckIconArrowDown = new Texture(Gdx.files.internal("keyIcons/bas.png"));
		controlsDuckIconShift = new Texture(Gdx.files.internal("keyIcons/S.png"));
		// Controls Pause Icons
		controlsPauseIconEsc = new Texture(Gdx.files.internal("keyIcons/esc.png"));
		controlsPauseIconP = new Texture(Gdx.files.internal("keyIcons/P.png"));
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
		controlsDuckIconArrowDown.dispose();
		controlsDuckIconShift.dispose();
		
		controlsPauseIconEsc.dispose();
		controlsPauseIconP.dispose();
	}

}
