package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.core.DSM;


public class ScreenHelper {
	
	private DSM game;
	private final static int HEADLINE_POSITION_X = 0;
	private final static int HEADLINE_POSITION_Y = 0;
	private final static int HEADLINE_HEIGHT = -80;
	private final static int HEADLINE_ALIGNMENT = Align.center;
	
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
}
