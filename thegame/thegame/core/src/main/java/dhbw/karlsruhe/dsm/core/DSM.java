package dhbw.karlsruhe.dsm.core;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;
import dhbw.karlsruhe.dsm.core.screens.ScreenHelper;

public class DSM extends Game {

	private int width = ConfigurationConstants.SCREENWIDTH;
	private int height = ConfigurationConstants.SCREENHEIGHT;
	
	public BitmapFont buttonFont;
	public BitmapFont gameFont;
	public BitmapFont headlineFont;
	
	public TextButtonStyle textButtonStyle;
	public LabelStyle labelStyle;
	public LabelStyle labelHeadingStyle;
	
	public SpriteBatch batch;
	
	public ScreenHelper screenHelper;
	
	@Override
	public void create() {
		screenHelper = new ScreenHelper(this);
		
		buttonFont = new BitmapFont(Gdx.files.internal("fonts/electrolize-22.fnt"), Gdx.files.internal("fonts/electrolize-22.png"), false);
		gameFont = new BitmapFont(Gdx.files.internal("fonts/electrolize-16.fnt"), Gdx.files.internal("fonts/electrolize-16.png"), false);
		headlineFont = new BitmapFont(Gdx.files.internal("fonts/electrolize-32.fnt"), Gdx.files.internal("fonts/electrolize-32.png"), false);
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = buttonFont;
		
		labelStyle = new LabelStyle();
		labelStyle.font = buttonFont;
		
		labelHeadingStyle = new LabelStyle();
		labelHeadingStyle.font = headlineFont;
		
		batch = new SpriteBatch();
	
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
		gameFont.dispose();
		batch.dispose();
	}
	
	public void exit() {
		if (Gdx.app.getType() != ApplicationType.Applet) {
			Gdx.app.exit();
		} else {
			dispose();
			Gdx.net.openURI("http://dsm-thegame.it.dh-karlsruhe.de/");
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}


}
