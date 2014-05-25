package dhbw.karlsruhe.dsm.core;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	
	public Texture background;
	public Sprite backgroundSprite;
	
	public TextButtonStyle textButtonStyle;
	public LabelStyle labelStyle;
	public LabelStyle labelHeadingStyle;
	
	public SpriteBatch batch;
	
	public ScreenHelper screenHelper;
	
	@Override
	public void create() {
		screenHelper = new ScreenHelper(this);
		
		buttonFont 		= new BitmapFont(Gdx.files.internal(ConfigurationConstants.FONT_NAME + "22" + ConfigurationConstants.FONT_NAME_SUFFIX), Gdx.files.internal(ConfigurationConstants.FONT_NAME + "22.png"), false);
		gameFont 		= new BitmapFont(Gdx.files.internal(ConfigurationConstants.FONT_NAME + "16" + ConfigurationConstants.FONT_NAME_SUFFIX), Gdx.files.internal(ConfigurationConstants.FONT_NAME + "16.png"), false);
		headlineFont 	= new BitmapFont(Gdx.files.internal(ConfigurationConstants.FONT_NAME + "32" + ConfigurationConstants.FONT_NAME_SUFFIX), Gdx.files.internal(ConfigurationConstants.FONT_NAME + "32.png"), false);
		
		background = new Texture(Gdx.files.internal(ConfigurationConstants.BACKGROUND_TEXTURE_NAME));
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundSprite = new Sprite(background);
		backgroundSprite.setRegion(0, 0, width, height);
		backgroundSprite.setBounds(0, 0, width, height);
		
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
		batch.begin();
		backgroundSprite.draw(batch);
		batch.end();
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		backgroundSprite.setRegion(0, 0, width, height);
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
