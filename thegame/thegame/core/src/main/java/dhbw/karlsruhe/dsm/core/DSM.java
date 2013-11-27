package dhbw.karlsruhe.dsm.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class DSM extends Game {

	BitmapFont buttonFont;
	TextButtonStyle textButtonStyle;
	LabelStyle labelStyle;
	LabelStyle labelHeadingStyle;
	
	@Override
	public void create() {
		
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
	public void dispose() {
		// Clean up 
		buttonFont.dispose();
		// sudo alt f4
		Gdx.app.exit();
	}

}
