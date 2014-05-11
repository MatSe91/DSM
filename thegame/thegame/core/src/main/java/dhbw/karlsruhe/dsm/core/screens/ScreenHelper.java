package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.core.DSM;


public class ScreenHelper {
	
	private static final String RETURN_BUTTON_TEXT = "Back";
	private static final int RETURN_BUTTON_WIDTH = 125;
	public static final int	RETURN_BUTTON_POSITION_DELTA_X = 100;
	public static final int	RETURN_BUTTON_POSITION_Y = 5;
	
	private final static int HEADLINE_POSITION_X = 0;
	private final static int HEADLINE_POSITION_Y = 0;
	private final static int HEADLINE_HEIGHT = -80;
	private final static int HEADLINE_ALIGNMENT = Align.center;

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
	 * Creates a new Return Button with the default values.
	 * @return TextButton Return Button
	 */
	public TextButton createReturnButton() {
		TextButton tB = createTextButton(RETURN_BUTTON_TEXT, game.getWidth() - RETURN_BUTTON_POSITION_DELTA_X, RETURN_BUTTON_POSITION_Y, RETURN_BUTTON_WIDTH);
		
		tB.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.returnToPreviousScreen();
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
	 * Creates a new Table with the following default values:
	 * Size 		= gameWidth	; gameHeight
	 * Position 	= 0			; 0
	 * FillParent	= true
	 * left.padLeft = 50
	 * 
	 * @return Table
	 */
	public Table createTable() {
		Table table = new Table();

		table.setSize(game.getWidth(), game.getHeight());
		table.setPosition(0, 0);
		table.setFillParent(true);
		table.debug();
		table.left().padLeft(50);
		
		return table;
	}
	
	
	/**
	 * Updates the default Table's position accordingly.
	 * @param table
	 */
	public void updateTableAfterResize(Table table) {
		table.setSize(game.getWidth(), game.getHeight());
	}
	

}
