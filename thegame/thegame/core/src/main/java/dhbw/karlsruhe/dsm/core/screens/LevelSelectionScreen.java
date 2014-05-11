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

	public LevelSelectionScreen(DSM game, Screen previous) {
		this.game = game;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		levelList = LevelDAO.loadLevels();
		levelList.addAll(LevelDAO.loadLevels());
		levelList.addAll(LevelDAO.loadLevels());
		levelList.addAll(LevelDAO.loadLevels());
		levelList.addAll(LevelDAO.loadLevels());
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
		returnButton = game.screenHelper.createReturnButton();
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
		TextButton b = game.screenHelper.createTextButton(level.getName(), BUTTON_WIDTH);
		
		b.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				showHighscoreScreen(level);
				return false;
			}
		});
		
		return b;
	}

	protected void showHighscoreScreen(Level level) {
		// TODO: hook HighscoreScreen here
		new Exception("Not yet implemented");
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		 Table.drawDebug(stage); // Debug lines for Tables
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
