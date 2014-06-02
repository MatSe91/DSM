package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;

public abstract class OpenScreenCommand {
	protected final DSM game = (DSM) Gdx.app.getApplicationListener();
	
	public OpenScreenCommand() {
	}
	
	public abstract void execute();
	
	protected void baseExecute(Screen next) {
		game.setScreen(next);
	}
}
