package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.core.DSM;


public class ScreenHelper {
	
	public static final int TABLE_PAD_LEFT = 50;
	public static final int TABLE_POSITION_X = 0;
	public static final int TABLE_POSITION_Y = 0;
	
	public static final String RETURN_BUTTON_TEXT = "Back";
	public static final int RETURN_BUTTON_WIDTH = 125;
	public static final int	RETURN_BUTTON_POSITION_DELTA_X = 100;
	public static final int	RETURN_BUTTON_POSITION_Y = 5;
	
	public static final int HEADLINE_POSITION_X = 0;
	public static final int HEADLINE_POSITION_Y = 0;
	public static final int HEADLINE_HEIGHT = -80;
	public static final int HEADLINE_ALIGNMENT = Align.center;

	private final DSM game;
	
	public ScreenHelper(DSM game) {
		this.game = game;
	}
	
	/**
	 * Creates a new stage headline with the given String and default parameters
	 * @param text to display
	 * @return Label: stage headline
	 */
	public Label createHeadline(String text) {
		Label l = createLabel(text, HEADLINE_POSITION_X, game.getHeight() + HEADLINE_POSITION_Y, HEADLINE_HEIGHT, HEADLINE_ALIGNMENT);
		l.setWidth(game.getWidth());
		return l;
	}
	
	/**
	 * Updates the headline Label's position accordingly
	 * @param headline
	 */
	public void updateHeadlineAfterResize(Label headline) {
		headline.setPosition(HEADLINE_POSITION_X, game.getHeight() + HEADLINE_POSITION_Y);
		headline.setWidth(game.getWidth());
	}
	

	/**
	 * Creates a new Label
	 * @param text
	 * @param x position from the left of the screen
	 * @param y position from the bottom of the screen
	 * @param width
	 * @param height
	 * @return Label
	 */
	public Label createLabel(String text, int x, int y, int height) {
		Label newLabel = new Label(text, game.labelStyle);
		newLabel.setPosition(x, y);
		newLabel.setHeight(height);
		
		return newLabel;
	}
	
	/**
	 * Creates a new Label
	 * @param text
	 * @param x position from the left of the screen
	 * @param y position from the bottom of the screen
	 * @return Label
	 */
	public Label createLabel(String text, int x, int y) {
		Label newLabel = new Label(text, game.labelStyle);
		newLabel.setPosition(x, y);
		return newLabel;
	}
	
	/**
	 * Creates a new Label
	 * @param text
	 * @param x position from the left of the screen
	 * @param y position from the bottom of the screen
	 * @param height
	 * @param align Align.yourFavAlignment
	 * @return Label
	 */
	public Label createLabel(String text, int x, int y, int height, int align) {
		Label l = createLabel(text, x, y);
		l.setHeight(height);
		l.setAlignment(align);
		return l;
	}
	
	
	/**
	 * Creates a new TextButton
	 * @param text
	 * @param x position from the left of the screen
	 * @param y position from the bottom of the screen
	 * @param width 
	 * @return TextButton
	 */
	public TextButton createTextButton(String text, int x, int y, int width) {
		TextButton newButton = new TextButton(text, game.textButtonStyle);
		newButton.setWidth(width);
		newButton.setPosition(x, y);
		return newButton;
	}
	
	/**
	 * Creates a new TextButton
	 * @param text
	 * @param width 
	 * @return TextButton
	 */
	public TextButton createTextButton(String text, int width) {
		TextButton newButton = new TextButton(text, game.textButtonStyle);
		newButton.setWidth(width);
		return newButton;
	}
	
	/**
	 * Creates a new TextButton
	 * @param text
	 * @param tooltip text 
	 * @param label to show tooltip's text
	 * @return TextButton
	 */
	public TextButton createTextButton(String text, final String tooltip, final Label label) {
		TextButton newButton = new TextButton(text, game.textButtonStyle);
		newButton.addListener(new InputListener() {
			public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
				label.setText(tooltip);
			}
			public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
				label.setText("");
			}
		});
		return newButton;
	}
	
	
	/** 
	 * Creates a new Return Button with the default values.
	 * @param Screen to return to
	 * @return TextButton Return Button
	 */
	public TextButton createReturnButton(final Class screen) {
		TextButton tB = createTextButton(RETURN_BUTTON_TEXT, game.getWidth() - RETURN_BUTTON_POSITION_DELTA_X, RETURN_BUTTON_POSITION_Y, RETURN_BUTTON_WIDTH);
		
		tB.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setScreen(screen);
				return false;
			}
		});
		
		return tB;
	}
	
	/**
	 * Creates a new Return Button with the default values.<br> 
	 * This button is for Screens with a stored previous screen
	 * @param previousScreen to return to
	 * @return TextButton Return Button
	 */
	public TextButton createReturnToPreviousButton(final Screen previousScreen) {
		final Screen currentScreen = game.getScreen();
		TextButton tB = createTextButton(RETURN_BUTTON_TEXT, game.getWidth() - RETURN_BUTTON_POSITION_DELTA_X, RETURN_BUTTON_POSITION_Y, RETURN_BUTTON_WIDTH);
		
		tB.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(previousScreen);
				currentScreen.dispose();
				return false;
			}
		});
		
		return tB;
	}
	
	/**
	 * Updates the Return Button's position accordingly.
	 * @param returnButton
	 */
	public void updateReturnButtonAfterResize(TextButton returnButton) {
		returnButton.setPosition(game.getWidth() - RETURN_BUTTON_POSITION_DELTA_X, RETURN_BUTTON_POSITION_Y);
	}
	
	
	/**
	 * Creates a new Table with the following default values:<br>
	 * Size 		= gameWidth	; gameHeight<br>
	 * Position 	= 0			; 0<br>
	 * FillParent	= true<br>
	 * left.padLeft = 50<br>
	 * 
	 * @return Table
	 */
	public Table createTable() {
		Table table = new Table();

		table.setSize(game.getWidth(), game.getHeight());
		table.setPosition(TABLE_POSITION_X, TABLE_POSITION_Y);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(TABLE_PAD_LEFT);
		
		return table;
	}
	
	
	/**
	 * Updates the default Table's position accordingly.
	 * @param table
	 */
	public void updateTableAfterResize(Table table) {
		table.setSize(game.getWidth(), game.getHeight());
	}
	
	/**
	 * Sets the game's screen to a new instance of screen.<br>
	 * <b>The current screen instance is disposed!</b>
	 * @param screen to set the game to
	 */
	public void setScreen(Class screen) {
		Screen previousScreen = game.getScreen();
		if(screen == MainMenuScreen.class) {
			game.setScreen(new MainMenuScreen(game));
		} else if(screen == GameLevelSelectionScreen.class) {
			game.setScreen(new GameLevelSelectionScreen(game));
		} else if(screen == HighScoreLevelSelectionScreen.class) {
			game.setScreen(new HighScoreLevelSelectionScreen(game));
		} else if(screen == InstructionsScreen.class) {
			game.setScreen(new InstructionsScreen(game));
		} else if(screen == ExitGameScreen.class) {
			game.setScreen(new ExitGameScreen(game, previousScreen));
		} else if(screen== CreditScreen.class){
			game.setScreen(new CreditScreen(game));
		}
		previousScreen.dispose();
	}
	
	/**
	 * Adds an onClick Listener to the given actor. On clicking the button, the games screen will be changed to a new instance of screen.<br>
	 * <b>The current screen instance is disposed!</b>
	 * @param actor
	 * @param targetScreen
	 */
	public void addSetScreenListener(Actor actor, final Class targetScreen) {
		actor.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setScreen(targetScreen);
				return false;
			}
		});
	}

}
