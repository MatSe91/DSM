package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;

import dhbw.karlsruhe.dsm.core.DSM;


public class Pattern extends PolygonSprite{

	private Body physicalBody;
	
	public Pattern(PolygonRegion region, World world) {
		super(region);
		makePhysical(world);
	}
		

	public Pattern(PolygonRegion region, float worldPositionX, float worldPositionY, World world) {
		super(region);
		super.setPosition(worldPositionX, worldPositionY);
		makePhysical(world);
	}
	
	private void makePhysical(World world) {
		DSM game = (DSM) Gdx.app.getApplicationListener();
		
		float width = getBoundingRectangle().width;
		float height = getBoundingRectangle().height;
		float x = getBoundingRectangle().x;
		float y = getBoundingRectangle().y;
		float centerX = width / 2;
		float centerY = height / 2;
		
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(x, y);
		
		physicalBody = world.createBody(bodydef);
		
		PolygonShape physicalShape = new PolygonShape();
		physicalShape.setAsBox(width/2, height/2 ,new Vector2(centerX, centerY), 0);
		
		Fixture fixture = physicalBody.createFixture(physicalShape, 100f);
		
		physicalBody.setUserData(this);
	}
	
	@Override
	public void translateX(float x) {
		super.translateX(x);
		physicalBody.setTransform(new Vector2(this.getBoundingRectangle().x, this.getBoundingRectangle().y), 0);
	}
	
	public Body getBody() {
		return physicalBody;
	}
}
