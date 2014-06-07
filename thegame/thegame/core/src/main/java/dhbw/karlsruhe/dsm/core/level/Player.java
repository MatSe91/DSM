package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;

public class Player extends Sprite {
	private Texture playerTexture;
	private Body physicalBody;
	private PolygonShape physicalShape;
	private float xPos;
	private float yPos;
	
	private boolean isPhysical;
	private Fixture fixture;
	

	public Player ( ){
		playerTexture = new Texture(Gdx.files.internal("textures/Runner.png"));
		xPos = ConfigurationConstants.SCREENWIDTH/2-15;
		yPos = 275;
		updateBounds(playerTexture.getWidth(), playerTexture.getHeight());
	}
	public void jump(){
		changeTexture(new Texture(Gdx.files.internal("textures/Runner_jump.png")));
	}
	public void duck(){
		changeTexture(new Texture(Gdx.files.internal("textures/Runner_duck.png")));
	}
	public void normal(){
		changeTexture(new Texture(Gdx.files.internal("textures/Runner.png")));
	}
	
	private void changeTexture(Texture newTexture) {
		Texture temp = playerTexture;
		playerTexture = newTexture;
		temp.dispose();
		updateBounds(newTexture.getWidth(), newTexture.getHeight());
	}
	
	private void updateBounds(int width, int height) {
		this.setBounds(xPos, yPos, width, height);
		if(isPhysical) 
			updatePhysicalDimensions(width/2, height/2);
	}
	/**
	 * Updates the physical Body to fit the new dimensions
	 * @param hx: half width
	 * @param hy: half height
	 */
	private void updatePhysicalDimensions(float hx, float hy) {
		physicalShape.setAsBox(hx, hy);
	}
	public void dispose() {
		playerTexture.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch){
		batch.draw(playerTexture, xPos, yPos);
	}	
	
	public void setPosX(float posX) {
		xPos = posX;
		super.setX(posX);
	}
	
	public void setPosY(float posY) {
		yPos = posY;
		super.setY(posY);
	}
	
	@Override
	public void setPosition(float x, float y) {
		xPos = x; yPos = y;
		super.setPosition(x, y);
	}
	
	public void makePhysical(World world) {
		DSM game = (DSM) Gdx.app.getApplicationListener();
		
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(new Vector2(xPos, yPos));
		
		setPhysicalBody(world.createBody(bodydef));
		
		physicalShape = new PolygonShape();
		physicalShape.setAsBox(this.getWidth()/2, this.getHeight()/2, new Vector2(getWidth()/2, getHeight()/2), 0);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = physicalShape;
		fixtureDef.density = .9f;
		fixtureDef.friction = 1f;
		fixtureDef.restitution = 0.8f;
		
		fixture = getPhysicalBody().createFixture(fixtureDef);
		physicalShape = (PolygonShape) (fixture.getShape());
		
		getPhysicalBody().setUserData(this);
	}
	
	public Body getPhysicalBody() {
		return physicalBody;
	}
	public void setPhysicalBody(Body physicalBody) {
		this.physicalBody = physicalBody;
	}
	
	
}
