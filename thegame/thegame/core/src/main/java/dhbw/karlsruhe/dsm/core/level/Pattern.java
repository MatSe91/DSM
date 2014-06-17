package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Pattern extends PolygonSprite {

	private List<Body> physicalBodies = new ArrayList<Body>();
	private World world;
	
	public Pattern(PolygonRegion region, World world, float[][] shapeVertices) {
		super(region);
		makePhysical(world, shapeVertices);
	}
		

	public Pattern(PolygonRegion region, float worldPositionX, float worldPositionY, World world, float[][] shapeVertices) {
		super(region);
		super.setPosition(worldPositionX, worldPositionY);
		makePhysical(world, shapeVertices);
	}
	
	private void makePhysical(World world, float[][] shapeVertices) {
		this.world = world;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(getBoundingRectangle().x, getBoundingRectangle().y);
		
		for(float[] singleShapeVertices: shapeVertices) {
			Body physicalBody;
			physicalBody = world.createBody(bodydef);
			
			PolygonShape physicalShape = new PolygonShape();
			physicalShape.set(singleShapeVertices);
			
			physicalBody.createFixture(physicalShape, 100f);
			physicalBody.setUserData(this);
			
			physicalShape.dispose(); // We created a copy of it, no need to keep it
			this.physicalBodies.add(physicalBody);
		}
	}
	
	@Override
	public void translateX(float x) {
		super.translateX(x);
		for(Body body : physicalBodies) {
			body.setTransform(new Vector2(this.getBoundingRectangle().x, this.getBoundingRectangle().y), 0);
		}
	}
	
	public void dispose() {
		for(Body body : physicalBodies) {
			world.destroyBody(body);
		}
	}
	
}
