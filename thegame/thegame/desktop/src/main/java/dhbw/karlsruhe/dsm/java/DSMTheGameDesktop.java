package dhbw.karlsruhe.dsm.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;

public final class DSMTheGameDesktop {
	
	
	private DSMTheGameDesktop() {
		
	}
	
	public static void main (String[] args) {
		new LwjglApplication(new DSM(), createConfig());
	}
	
	public static LwjglApplicationConfiguration createConfig() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		config.width = ConfigurationConstants.SCREENWIDTH;
		config.height = ConfigurationConstants.SCREENHEIGHT;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		return config;
	}
}
