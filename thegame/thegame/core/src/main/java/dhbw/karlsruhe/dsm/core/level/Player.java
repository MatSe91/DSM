package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public class Player extends Sprite {
	private Texture playerTexture;
	private Body physicalBody;
	private PolygonShape physicalShape;
	private float posX;
	private float posY;
	
	private static final Texture JUMPING_TEXTURE = new Texture(Gdx.files.internal("textures/Runner_jump.png"));
	private static final Texture DUCKED_TEXTURE = new Texture(Gdx.files.internal("textures/Runner_duck.png"));
	private static final Texture STANDING_TEXTURE = new Texture(Gdx.files.internal("textures/Runner.png"));
	
	private static final float STANDING_HEIGHT = 1.8f;
	private static final float DUCKED_HEIGHT = 0.9f;
	private static final float WIDTH = 1.125f;
	
	private boolean isPhysical = false;
	private Fixture fixture;
	private Vector2 jumpImpulse;
	private World world;
	

	public Player (float posX, float posY ){
		super(STANDING_TEXTURE);
		playerTexture = STANDING_TEXTURE;
		this.posX = posX;
		this.posY = posY;
		float width = WIDTH;
		float height = STANDING_HEIGHT;
		updateBounds(width, height);
		setOrigin(0,0);
	}
	
	public void jump(){
		changeTexture(STANDING_TEXTURE, STANDING_HEIGHT);
		if(isPhysical) {
			physicalBody.applyLinearImpulse(jumpImpulse, physicalBody.getWorldCenter(), true);
		}
	}
	public void duck(){
		changeTexture(JUMPING_TEXTURE, DUCKED_HEIGHT);
	}
	public void normal(){
		changeTexture(STANDING_TEXTURE, STANDING_HEIGHT);
	}
	
	private void changeTexture(Texture newTexture, float newHeight) {
		playerTexture = newTexture;
		updateBounds(this.getWidth(), newHeight);
	}
	
	private void updateBounds(float width, float height) {
		this.setBounds(posX, posY, width, height);
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
		this.posX = posX;
		super.setX(posX);
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
		super.setY(posY);
	}
	
	@Override
	public void setPosition(float x, float y) {
		posX = x; posY = y;
		super.setPosition(x, y);
	}
	
	public void makePhysical(World world) {
		this.world = world;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(new Vector2(posX, posY));
		
		setPhysicalBody(world.createBody(bodydef));
		
		physicalShape = new PolygonShape();
		physicalShape.setAsBox(this.getWidth()/2, this.getHeight()/2, new Vector2(getWidth()/2, getHeight()/2), 0);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = physicalShape;
		fixtureDef.density = .9f;
		fixtureDef.friction = 1f;
		fixtureDef.restitution = 0.0f;
		fixture = getPhysicalBody().createFixture(fixtureDef);
		physicalShape.dispose();
		
		physicalShape = (PolygonShape) (fixture.getShape());
		
		getPhysicalBody().setUserData(this);
		getPhysicalBody().setFixedRotation(true);
		isPhysical = true;
		
		float gravityY = world.getGravity().y;
		float mass = getPhysicalBody().getMass();
		float impulseY = (float) Math.sqrt(-2*gravityY*mass*mass*ConfigurationConstants.JUMP_HEIHGT);
		jumpImpulse = new Vector2(0, impulseY);
	}
	
	public Body getPhysicalBody() {
		return physicalBody;
	}
	public void setPhysicalBody(Body physicalBody) {
		this.physicalBody = physicalBody;
	}

	public void updatePosition() {
		setPosition(physicalBody.getPosition().x, physicalBody.getPosition().y);
	}
	
	public void dispose() {
		world.destroyBody(physicalBody);
	}
	
	
}
