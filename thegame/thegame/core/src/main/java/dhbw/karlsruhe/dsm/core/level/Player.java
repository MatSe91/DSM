package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class Player extends Sprite {
	private Texture playerTexture;
	private Body physicalBody;
	private PolygonShape physicalShape;
	private float xPos;
	private float yPos;
	
	private static final Texture JUMPING_TEXTURE = new Texture(Gdx.files.internal("textures/Runner_jump.png"));
	private static final Texture DUCKED_TEXTURE = new Texture(Gdx.files.internal("textures/Runner_duck.png"));
	private static final Texture STANDING_TEXTURE = new Texture(Gdx.files.internal("textures/Runner.png"));
	
	private boolean isPhysical = false;
	private Fixture fixture;
	
	// Working variable
	private float rotation;
	

	public Player ( ){
		super(STANDING_TEXTURE);
		playerTexture = STANDING_TEXTURE;
		xPos = ConfigurationConstants.SCREENWIDTH/2-15;
		yPos = 275;
		float width = STANDING_TEXTURE.getWidth();
		float height = STANDING_TEXTURE.getHeight();
		updateBounds(width, height);
		setOrigin(0,0);
	}
	
	public void jump(){
		changeTexture(JUMPING_TEXTURE);
	}
	public void duck(){
		changeTexture(DUCKED_TEXTURE);
	}
	public void normal(){
		changeTexture(STANDING_TEXTURE);
	}
	
	private void changeTexture(Texture newTexture) {
		playerTexture = newTexture;
		updateBounds(newTexture.getWidth(), newTexture.getHeight());
	}
	
	private void updateBounds(float width, float height) {
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
//		physicalShape.setAsBox(hx, hy);
		physicalShape.setAsBox(hx, hy, new Vector2(hx, hy), 0);
	}
	
	@Override
	public void draw(SpriteBatch batch){
//		batch.draw(playerTexture, xPos, yPos);
		batch.draw(playerTexture, getVertices(), 0, getVertices().length);
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
		isPhysical = true;
	}
	
	public Body getPhysicalBody() {
		return physicalBody;
	}
	public void setPhysicalBody(Body physicalBody) {
		this.physicalBody = physicalBody;
	}

	public void updateRotation() {
		rotation = physicalBody.getAngle();
		if(Math.abs(rotation) > .1f)
			this.setRotation(MathUtils.radiansToDegrees * rotation);
	}

	public void updatePosition() {
		setPosition(physicalBody.getPosition().x, physicalBody.getPosition().y);
	}
	
	
}
