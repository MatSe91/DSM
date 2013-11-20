package dhbw.karlsruhe.dsm.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dhbw.karlsruhe.dsm.core.DSMTheGame;

public class DSMTheGameDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		new LwjglApplication(new DSMTheGame(), config);
	}
}
