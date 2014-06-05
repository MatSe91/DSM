package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class Player extends Sprite {
	private Texture player;
	@SuppressWarnings("unused")
	private int xPos;
	@SuppressWarnings("unused")
	private int yPos;
	

	public Player ( ){
		player = new Texture(Gdx.files.internal("textures/Runner.png"));
	}
	public void jump(){
		player = new Texture(Gdx.files.internal("textures/Runner_jump.png"));
	}
	public void duck(){
		player = new Texture(Gdx.files.internal("textures/Runner_duck.png"));
	}
	public void normal(){
		player = new Texture(Gdx.files.internal("textures/Runner.png"));
	}
	@Override
	public void draw(SpriteBatch batch){
		batch.draw(player,ConfigurationConstants.SCREENWIDTH/2-15,275);
	}	
	
}
