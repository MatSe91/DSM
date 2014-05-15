package dhbw.karlsruhe.dsm.core.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.LevelDAO;

public class LevelSelectionScreen implements Screen {


	private static final int TABLE_CELL_MIN_WIDTH = 150;
	private static final int TABLE_CELL_MIN_HEIGHT = 50;
	private static final int NUMBER_OF_ROWS = 5;
	private static final String HEADLINE_TEXT = "LEVEL SELECTION";
	private static final int BUTTON_WIDTH = 75;
	private static final int CELL_PADDING = 20;
	
	private DSM						game;
	private Stage					stage;

	private List<TextButton>	buttonList;

	private List<Level>		levelList;
	private TextButton				returnButton;
	private Table					table;
	private Label 					headline;

	public LevelSelectionScreen(DSM game) {
		this.game = game;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		levelList = LevelDAO.loadLevels();

		// Initialize actors
		initActors();

		// Add them to the stage
		stage.addActor(table);
		stage.addActor(returnButton);
		stage.addActor(headline);
	}

	private void initActors() {
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT);
		
		table = game.screenHelper.createTable();
		createButtons();
		
		fillTable();
	}

	private void fillTable() {
		int listSize  = buttonList.size();
		int doubleRowLength = NUMBER_OF_ROWS * 2;
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			if(i < listSize) {
				table.add(buttonList.get(i)).fill().minWidth(TABLE_CELL_MIN_WIDTH).minHeight(TABLE_CELL_MIN_HEIGHT).pad(CELL_PADDING);
			}
			if(i+NUMBER_OF_ROWS < listSize) {
				table.add(buttonList.get(i+NUMBER_OF_ROWS)).fill().minWidth(TABLE_CELL_MIN_WIDTH).minHeight(TABLE_CELL_MIN_HEIGHT).pad(CELL_PADDING);
			}
			if(i+doubleRowLength < listSize) {
				table.add(buttonList.get(i+doubleRowLength)).fill().minWidth(TABLE_CELL_MIN_WIDTH).minHeight(TABLE_CELL_MIN_HEIGHT).pad(CELL_PADDING);
			}
			table.row();
		}

	}

	private void createButtons() {
		createLevelButtons();
		returnButton = game.screenHelper.createReturnButton(MainMenuScreen.class);
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
		TextButton newButton = game.screenHelper.createTextButton(level.getName(), BUTTON_WIDTH);

		newButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				showHighscoreScreen(level);
				return false;
			}
		});
		
		return newButton;
	}

	protected void showHighscoreScreen(Level level) {
		game.setScreen(new HighscoreScreen(game, level));
		this.dispose();
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		// Debug lines for Tables
		//Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height);
		game.screenHelper.updateReturnButtonAfterResize(returnButton);
		game.screenHelper.updateHeadlineAfterResize(headline);
		game.screenHelper.updateTableAfterResize(table);
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

}
