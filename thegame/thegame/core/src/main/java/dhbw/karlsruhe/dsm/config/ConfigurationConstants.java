package dhbw.karlsruhe.dsm.config;

import com.badlogic.gdx.math.Vector2;

public final class ConfigurationConstants {
	
	private ConfigurationConstants() {
		
	}
	
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;
	
	
	

	
	public static final String PATH_LEVEL_DIRECTORY = "data/levels";
	public static final String PATH_SCORE_DIRECTORY = "data/scores";
	public static final String FONT_NAME = "fonts/electrolize-";
	public static final String FONT_NAME_SUFFIX = ".fnt";
	
	public static final String BACKGROUND_TEXTURE_NAME = "textures/background_tile.png";
	
	// ############## //
	// Game Constants //
	// ############## //
	
	public static final Vector2 GRAVITY_VECTOR = new Vector2(0, -555);
}
