package dhbw.karlsruhe.dsm.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.level.LevelLoader;

public class LevelSelectionScreen implements Screen {


	private static final String		RETURN_BUTTON_TEXT	= "Back";
	private DSM						game;
	private Screen					previous;
	private Stage					stage;

	private ArrayList<TextButton>	buttonList;

	private ArrayList<Level>		levelList;
	private TextButton				returnButton;
	private Table	table;

	public LevelSelectionScreen(DSM game, Screen previous) {
		this.game = game;
		this.previous = previous;
		this.stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		levelList = LevelLoader.loadLevels();
		levelList.addAll(LevelLoader.loadLevels());
		levelList.addAll(LevelLoader.loadLevels());
		// Initialize actors
		initActors();

		// Add them to the stage
		stage.addActor(table);
		stage.addActor(returnButton);
	}

	private void initActors() {
		createTable();
		createButtons();
		
		fillTable();
	}

	private void fillTable() {
		int listSize  = buttonList.size();
		int rowLength = 5;
		int doubleRowLength = rowLength * 2;
		for (int i = 0; i < rowLength; i++) {
			table.add(buttonList.get(i)).expandX();
			if(i+rowLength < listSize)
				table.add(buttonList.get(i+rowLength)).expandX();
			if(i+doubleRowLength < listSize)
				table.add(buttonList.get(i+doubleRowLength)).expandX();
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
		returnButton.setPosition(ConfigurationConstants.RETURN_BUTTON_POSITION_X, ConfigurationConstants.RETURN_BUTTON_POSITION_Y);
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
		return b;
	}

	protected void showHighscoreScreen(Level level) {
		System.out.println(level.getName());
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
