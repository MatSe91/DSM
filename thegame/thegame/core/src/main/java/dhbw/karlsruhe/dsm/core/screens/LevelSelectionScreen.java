package dhbw.karlsruhe.dsm.core.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.LevelLoader;

public class LevelSelectionScreen implements Screen {


	private static final String HEADLINE_TEXT = "LEVEL SELECTION";
	private static final int BUTTON_WIDTH = 75;
	private static final int CELL_PADDING = 20;
	private static final String		RETURN_BUTTON_TEXT	= "Back";
	private DSM						game;
	private Screen					previous;
	private Stage					stage;

	private ArrayList<TextButton>	buttonList;

	private ArrayList<Level>		levelList;
	private TextButton				returnButton;
	private Table					table;
	private Label 					headline;

	public LevelSelectionScreen(DSM game, Screen previous) {
		this.game = game;
		this.previous = previous;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		levelList = LevelLoader.loadLevels();
		levelList.addAll(LevelLoader.loadLevels());
		levelList.addAll(LevelLoader.loadLevels());
		levelList.addAll(LevelLoader.loadLevels());
		levelList.addAll(LevelLoader.loadLevels());
		// Initialize actors
		initActors();

		// Add them to the stage
		stage.addActor(table);
		stage.addActor(returnButton);
		stage.addActor(headline);
	}

	private void initActors() {
		headline = game.stageHelper.createHeadline(HEADLINE_TEXT);
		
		createTable();
		createButtons();
		
		fillTable();
	}

	private void fillTable() {
		int listSize  = buttonList.size();
		int rowLength = 5;
		int doubleRowLength = rowLength * 2;
		for (int i = 0; i < rowLength; i++) {
			table.add(buttonList.get(i)).fill().minWidth(150).minHeight(50).pad(CELL_PADDING);
			if(i+rowLength < listSize)
				table.add(buttonList.get(i+rowLength)).fill().minWidth(150).minHeight(50).pad(CELL_PADDING);
			if(i+doubleRowLength < listSize)
				table.add(buttonList.get(i+doubleRowLength)).fill().minWidth(150).minHeight(50).pad(CELL_PADDING);
			table.row();
		}

	}

	private void createButtons() {
		createLevelButtons();
		
		returnButton = new TextButton(RETURN_BUTTON_TEXT, game.textButtonStyle);
		returnButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				returnToPreviousScreen();
				return false;
			}

			public void enter(InputEvent event, float x, float y, int pointer,
					Actor actor) {
				// highlight
			}

			public void exit(InputEvent event, float x, float y, int pointer,
					Actor actor) {
				// downlight
			}
		});
		returnButton.setWidth(BUTTON_WIDTH);
		returnButton.setPosition(stage.getWidth() - ConfigurationConstants.RETURN_BUTTON_POSITION_DELTA_X, ConfigurationConstants.RETURN_BUTTON_POSITION_Y);
	}

	protected void returnToPreviousScreen() {
		game.setScreen(previous);
		dispose();
	}

	private void createTable() {
		table = new Table();

		table.setSize(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT);
		table.setPosition(0, 0);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(50);
	}

	private void createLevelButtons() {
		buttonList = new ArrayList<TextButton>();
		TextButton b;

		for (Level level : levelList) {
			b = createLevelButton(level);
			buttonList.add(b);
		}
	}

	private TextButton createLevelButton(final Level level) {
		TextButton b = new TextButton(level.getName(), game.textButtonStyle);
		b.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				showHighscoreScreen(level);
				return false;
			}

			public void enter(InputEvent event, float x, float y,
					int pointer, Actor actor) {
				// highlight
			}

			public void exit(InputEvent event, float x, float y,
					int pointer, Actor actor) {
				// downlight
			}

		});
		b.setWidth(BUTTON_WIDTH);
		return b;
	}

	protected void showHighscoreScreen(Level level) {
		game.setScreen(previous);
		dispose();
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
		// TODO Auto-generated method stub
		stage.setViewport(width, height);
		returnButton.setPosition(stage.getWidth() - ConfigurationConstants.RETURN_BUTTON_POSITION_DELTA_X, ConfigurationConstants.RETURN_BUTTON_POSITION_Y);
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
		// TODO Auto-generated method stub

	}

}
