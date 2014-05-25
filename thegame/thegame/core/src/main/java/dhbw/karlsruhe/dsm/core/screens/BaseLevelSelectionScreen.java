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

public abstract class BaseLevelSelectionScreen implements Screen {


	protected static final int TABLE_CELL_MIN_WIDTH = 150;
	protected static final int TABLE_CELL_MIN_HEIGHT = 50;
	protected static final int NUMBER_OF_ROWS = 5;
	protected static final String HEADLINE_TEXT = "LEVEL SELECTION";
	protected static final int BUTTON_WIDTH = 75;
	protected static final int CELL_PADDING = 20;
	
	protected DSM						game;
	protected Stage					stage;

	protected List<TextButton>	buttonList;

	protected List<Level>		levelList;
	protected TextButton				returnButton;
	protected Table					table;
	protected Label 					headline;

	public BaseLevelSelectionScreen(DSM game) {
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

	protected void initActors() {
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT);
		
		table = game.screenHelper.createTable();
		createButtons();
		
		fillTable();
	}

	protected void fillTable() {
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

	protected void createButtons() {
		createLevelButtons();
		returnButton = game.screenHelper.createReturnButton(MainMenuScreen.class);
	}

	protected void createLevelButtons() {
		buttonList = new ArrayList<TextButton>();
		TextButton b;

		for (Level level : levelList) {
			b = createLevelButton(level);
			buttonList.add(b);
		}
	}

	protected TextButton createLevelButton(final Level level) {
		TextButton newButton = game.screenHelper.createTextButton(level.getName(), BUTTON_WIDTH);

		newButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				showNextScreen(level);
				return false;
			}
		});
		
		return newButton;
	}

	protected abstract void showNextScreen(Level level);

	@Override
	public void render(float delta) {
		// Clear the screen
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
