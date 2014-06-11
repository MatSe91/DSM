package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Pattern extends PolygonSprite{

	private Body physicalBody;
	private World world;
	
	public Pattern(PolygonRegion region, World world) {
		super(region);
		makePhysical(world, region);
	}
		

	public Pattern(PolygonRegion region, float worldPositionX, float worldPositionY, World world) {
		super(region);
		super.setPosition(worldPositionX, worldPositionY);
		makePhysical(world, region);
	}
	
	private void makePhysical(World world, PolygonRegion region) {
		this.world = world;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(getBoundingRectangle().x, getBoundingRectangle().y);
		
		physicalBody = world.createBody(bodydef);
		
		PolygonShape physicalShape = new PolygonShape();
		physicalShape.set(region.getVertices());
		
		physicalBody.createFixture(physicalShape, 100f);
		physicalBody.setUserData(this);
		
		physicalShape.dispose(); // We created a copy of it, no need to keep it
	}
	
	@Override
	public void translateX(float x) {
		super.translateX(x);
		physicalBody.setTransform(new Vector2(this.getBoundingRectangle().x, this.getBoundingRectangle().y), 0);
	}
	
	public Body getBody() {
		return physicalBody;
	}
	
	public void dispose() {
		world.destroyBody(physicalBody);
	}
}
