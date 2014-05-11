package dhbw.karlsruhe.dsm.core;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.core.screens.ScreenHelper;

public class DSM extends Game {

	private int width = ConfigurationConstants.SCREENWIDTH;
	private int height = ConfigurationConstants.SCREENHEIGHT;
	
	private Screen previousScreen;
	
	public BitmapFont buttonFont;
	public TextButtonStyle textButtonStyle;
	public LabelStyle labelStyle;
	public LabelStyle labelHeadingStyle;
	
	public ScreenHelper screenHelper;
	
	@Override
	public void create() {
		screenHelper = new ScreenHelper(this);
		
		buttonFont = new BitmapFont();
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = buttonFont;
		
		labelStyle = new LabelStyle();
		labelStyle.font = buttonFont;
	
		// -> Main Menu
		this.setScreen(new MainMenuScreen(this));

	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		super.resize(width, height);
	}
	
	@Override
	public void dispose() {
		// Clean up 
		buttonFont.dispose();
		// sudo alt f4
		if (Gdx.app.getType() != ApplicationType.Applet) {
			Gdx.app.exit();
		} else {
			Gdx.net.openURI("http://dsm-thegame.it.dh-karlsruhe.de/");
		}
	}
	
	@Override
	public void setScreen(Screen screen) {
		previousScreen = this.getScreen();
		super.setScreen(screen);
	}
	
	public void returnToPreviousScreen() {
		this.setScreen(this.getPreviousScreen());
		this.previousScreen.dispose();
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}

	public Screen getPreviousScreen() {
		return previousScreen;
	}

}
