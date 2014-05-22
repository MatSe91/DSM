package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;


public class Pattern extends PolygonSprite{

	public Pattern(PolygonRegion region) {
		super(region);
	}
		
	public Pattern(PolygonRegion region, float worldPositionX, float worldPositionY) {
		super(region);
		super.setPosition(worldPositionX, worldPositionY);
	}
}
