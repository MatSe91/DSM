package dhbw.karlsruhe.dsm.core.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.Score;
import dhbw.karlsruhe.dsm.core.level.ScoreDAO;

public class HighscoreScreen implements Screen {


	private static final String	ROWHEADER_SCORE_LABEL	= "SCORE";
	private static final String	ROWHEADER_PLAYER_LABEL	= "PLAYER";
	private static final String	ROWHEADER_RANK_LABEL	= "#";
	private static final int TABLE_CELL_MIN_WIDTH = 150;
	private static final int TABLE_CELL_MIN_HEIGHT = 45;
	private static final String HEADLINE_TEXT = "HIGHSCORES - ";
	private static final int CELL_PADDING = 15;
	
	private DSM						game;
	private Stage					stage;

	private List<Score>	scoreList;

	private Level					level;
	private TextButton				returnButton;
	private Table					table;
	private Label 					headline;

	public HighscoreScreen(DSM game, Level level) {
		this.game = game;
		this.stage = new Stage();
		this.level = level;

		// TODO: remove next line
		ScoreDAO.createTestScores(level);
		this.scoreList = ScoreDAO.loadScores(level);
		Gdx.input.setInputProcessor(stage);

		// Initialize actors
		initActors();

		// Add them to the stage
		stage.addActor(table);
		stage.addActor(returnButton);
		stage.addActor(headline);
	}

	private void initActors() {
		headline = game.screenHelper.createHeadline(HEADLINE_TEXT + level.getName());
		table = game.screenHelper.createTable();
		returnButton = game.screenHelper.createReturnButton(LevelSelectionScreen.class);
		
		fillTable();
	}

	private void fillTable() {

		Label label = game.screenHelper.createLabel(ROWHEADER_RANK_LABEL, 0, 0, TABLE_CELL_MIN_HEIGHT, Align.center);
		addLabelToTable(label, TABLE_CELL_MIN_HEIGHT);
		label = game.screenHelper.createLabel(ROWHEADER_PLAYER_LABEL, 0, 0, TABLE_CELL_MIN_HEIGHT, Align.left);
		addLabelToTable(label, TABLE_CELL_MIN_HEIGHT);
		label = game.screenHelper.createLabel(ROWHEADER_SCORE_LABEL, 0, 0, TABLE_CELL_MIN_HEIGHT, Align.left);
		addLabelToTable(label, TABLE_CELL_MIN_HEIGHT);
		table.row();
		
		int i = 1;
		for (Score score : scoreList) {
			label = game.screenHelper.createLabel(Integer.toString(i), 0, 0,TABLE_CELL_MIN_HEIGHT-10 , Align.center);
			addLabelToTable(label, TABLE_CELL_MIN_HEIGHT-10, 2);
			label = game.screenHelper.createLabel(score.getPlayerName(), 0, 0,TABLE_CELL_MIN_HEIGHT-10 , Align.left);
			addLabelToTable(label, TABLE_CELL_MIN_HEIGHT-10, 2, TABLE_CELL_MIN_WIDTH);
			label = game.screenHelper.createLabel(Integer.toString(score.getScore()), 0, 0,TABLE_CELL_MIN_HEIGHT-10 , Align.right);
			addLabelToTable(label, TABLE_CELL_MIN_HEIGHT-10, 2);
			table.row();
			i++;
		}

	}
	
	private void addLabelToTable(Label label, int cellHeight) {
		table.add(label).fill().minHeight(cellHeight).pad(0, CELL_PADDING, CELL_PADDING - 10, CELL_PADDING);
	}
	
	private void addLabelToTable(Label label, int cellHeight, int cellPadding) {
		table.add(label).fill().minHeight(cellHeight).pad(cellPadding, CELL_PADDING, cellPadding, CELL_PADDING);
	}

	private void addLabelToTable(Label label, int cellHeight, int cellPadding, int cellMinWidth) {
		table.add(label).fill().minWidth(cellMinWidth).minHeight(cellHeight).pad(cellPadding, CELL_PADDING, cellPadding, CELL_PADDING);
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
